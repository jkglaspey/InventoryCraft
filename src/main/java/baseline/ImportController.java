/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to import a file of items and override the current inventory.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ImportController {

    // Button for cancelling the current action
    @FXML
    private Button cancelButton;

    // Button for opening a file chooser to select a file
    @FXML
    private Button chooseFileButton;

    // Label that is displayed when there is an error with the input file
    @FXML
    private Label errorLabel;

    // Label that displays the selected file path
    @FXML
    private Label filePathLabel;

    // Button for loading in a selected file
    @FXML
    private Button loadItemsButton;

    // Declare sound for clicking button
    private final AudioClip buttonSoundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());

    // Grab the previous inventory
    private List<Item> inventory;

    // Declare a fxml loader
    private FXMLLoader root;

    // Declare the loaded scene
    private Parent scene;

    // Create the scene and copy the list of items from the main screen
    public ImportController(List<Item> inventory, Stage stage) {
        // save the list of items in case this action is cancelled
        this.inventory = inventory;

        // load the correct fxml file
        root = null;
        scene = null;
        try {
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("importScene.fxml")));
            root.setController(this);
            scene = root.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // open the scene
        stage.getScene().setRoot(scene);
        stage.show();
    }

    // Initialize specific FXML values
    public void initialize() {
        // change error text color
        errorLabel.setStyle("-fx-text-fill: red");
    }

    // Cancel the importing action
    @FXML
    void cancel(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(loadItemsButton.getScene().getWindow()));
    }

    // Open a file chooser to select a specific file to be read
    @FXML
    void chooseFile(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // open a new file chooser and select the desired file path
        String filePath = "";
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            filePath = file.getPath();
        }

        // determine the file extension of the selected file
        String extension = getFileExtension(filePath);

        // determine if the file extension is valid
        if(isValidFileExtension(extension)) {
            // hide the error label
            errorLabelVisible(false);

            // set the path label to the file path
            filePathLabel.setText(file.getPath());
        }
        else {
            // show the error label
            errorLabelVisible(true);

            // empty the path label
            filePathLabel.setText("");
        }
    }

    // Extract the file path from a String as long as it is .txt., .html, or .json
    private String getFileExtension(String path) {
        // get the last index of '.'
        int extension = path.lastIndexOf('.');

        // if the extension is > 0, return the extension
        if(extension > 0) return path.substring(extension + 1);

        // fail case
        else return null;
    }

    // Determine if a string is one of the 3 valid file extensions
    private boolean isValidFileExtension(String extension) {
        return (extension.equals("txt")) || (extension.equals("json")) || (extension.equals("html"));
    }

    // Determine the visibility of the error label
    private void errorLabelVisible(boolean value) {
        // set label visibility
        if(value) errorLabel.setOpacity(1.0);
        else errorLabel.setOpacity(0.0);
    }

    // Attempt to import a list of items to the application
    @FXML
    void loadItems(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // test if file path is valid
        if(!isValidFileExtension(filePathLabel.getText())) {
            // display error
            errorLabelVisible(true);

            // return from method
            return;
        }

        // attempt to open the file using the selected string path
        File importFile = openFile(filePathLabel.getText());

        // null case
        if(importFile == null) {
            // display error
            errorLabelVisible(true);

            // return from method
            return;
        }

        // override the current inventory to the new list imported by file
        inventory = importItems(importFile);

        // return to old screen
        new MainSceneController(inventory,(Stage)(loadItemsButton.getScene().getWindow()));
    }

    // Open a file given a string
    private File openFile(String path) {
        // try to return a new File from the file path
        try {
            return new File(path);
        }
        catch(Exception e) {
            // display error
            errorLabelVisible(true);

            // return from method
            return null;
        }
    }

    // Given a file, determine a method of parsing, and return the parsed list created by another method
    // Precondition: file is not null, and is one of three extensions
    private List<Item> importItems(File file) {
        // get the file extension
        String fileExtension = file.getPath();
        String fileType = getFileExtension(fileExtension);

        // if txt, call parseTSVFile
        if(fileType.equals("TSV")) return parseTSVFile(file);

        // if JSON, call parseJSONFile
        else if(fileType.equals("JSON")) return parseJSONFile(file);

        // if html, call parseHTMLFile
        else if(fileType.equals("HTML")) return parseHTMLFile(file);

        // fail case
        else return null;
    }

    // Parse a TSV file
    private List<Item> parseTSVFile(File file) {
        // create new list to be returned
        List<Item> newList = new ArrayList<>();

        // create new Scanner
        Scanner stream = createScanner(file);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // break method
            return null;
        }

        // loop through the file
        while(stream.hasNext()) {
            // read next line
            String nextLine = stream.nextLine();

            // create new array of String
            String[] values = nextLine.split("\t",2);

            // add new Item
            newList.add(new Item(values[1],values[0],values[2]));
        }

        // close the input stream
        stream.close();

        // return the Array List
        return newList;
    }

    // Parse a JSON file
    private List<Item> parseJSONFile(File file) {
        // create new ArrayList<Item>
        List<Item> newList = new ArrayList<>();

        // create new JsonStreamParser from the file
        JsonStreamParser stream = createJsonStream(file);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // return from method
            return null;
        }
        // create new JsonArray from JsonStreamParser
        JsonArray items = stream.next().getAsJsonArray();

        // loop while the array still has items
        for(int i = 0; i < items.size(); i++) {
            // add new Item to ArrayList using the 3 strings retrieved above
            newList.add(new Item(items.get(i).getAsJsonObject().get("name").getAsString(),
                    items.get(i).getAsJsonObject().get("serialNumber").getAsString(),
                    items.get(i).getAsJsonObject().get("cost").getAsString()));
        }

        // return the Array List
        return newList;
    }

    // Parse a HTML file
    private List<Item> parseHTMLFile(File file) {
        // create new ArrayList<Item>
        List<Item> newList = new ArrayList<>();

        // create new Scanner
        Scanner stream = createScanner(file);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // break method
            return null;
        }

        // read entire file into 1 string
        String all = "";
        while(stream.hasNext()) {
            all += stream.nextLine();
        }

        // create array of strings broken by "<td>"
        String items[] = all.split("</tr>",Integer.MAX_VALUE);

        // separate values in string and create new item
        for(String s: items) {
            // split item values
            String values[] = s.split("</td>",3);

            // remove tags from each item
            for(String v : values) {
                v = v.replaceAll("<tr>","");
                v = v.replaceAll("<td>","");
            }

            // add item to list
            newList.add(new Item(values[1],values[0],values[2]));
        }

        // close the input stream
        stream.close();

        // return the Array List
        return newList;
    }

    // Method which tries to create a JSON file stream
    private JsonStreamParser createJsonStream(File file) {
        // try to create stream
        try {
            // successfully created stream
            return new JsonStreamParser(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));
        }
        // file wasn't found
        catch (FileNotFoundException e) {
            return null;
        }
    }

    private Scanner createScanner(File file) {
        // try to create stream
        try {
            // successfully created stream
            return new Scanner(file);
        }
        // file wasn't found
        catch (FileNotFoundException e) {
            return null;
        }
    }
}
