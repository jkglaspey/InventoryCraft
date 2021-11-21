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
import java.util.*;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ImportController {
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
    private final AudioClip buttonSoundPlayer = new AudioClip(Objects.requireNonNull(getClass().getResource("sound/buttonClick.mp3")).toExternalForm());

    // Grab the previous inventory
    private List<Item> inventory;

    // Create the scene and copy the list of items from the main screen
    public ImportController(List<Item> inventory, Stage stage) {
        // save the list of items in case this action is cancelled
        this.inventory = inventory;

        // load the correct fxml file
        // Declare a fxml loader
        FXMLLoader root;
        // Declare the loaded scene
        Parent scene;
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

    // Create default controller (for testing)
    public ImportController() {
        inventory = new ArrayList<>();
    }

    // Initialize specific FXML values
    public void initialize() {
        // change error text color
        errorLabel.setStyle("-fx-text-fill: red");

        // disable import button
        disableLoadButton(true);

        // add a listener for the path label
        filePathLabel.textProperty().addListener(t -> disableLoadButton(false));
    }

    // Cancel the importing action
    @FXML
    void cancel(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(loadItemsButton.getScene().getWindow()));
    }

    // Enable or disable the load button
    private void disableLoadButton(boolean value) {
        loadItemsButton.setDisable(value);
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

        // if string is empty, leave method
        if(filePath.isBlank()) {
            // leave method
            return;
        }

        // determine if the file extension is valid
        if(isValidFileExtension(getFileExtension(filePath))) {
            // hide the error label
            errorLabelVisible(false);

            // set the path label to the file path
            assert file != null;
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
    protected String getFileExtension(String path) {
        // get the last index of '.'
        int extension = path.lastIndexOf('.');

        // if the extension is > 0, return the extension
        if(extension > 0) return path.substring(extension + 1);

        // fail case
        else return "";
    }

    // Determine if a string is one of the 3 valid file extensions
    protected boolean isValidFileExtension(String extension) {
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
        if(!isValidFileExtension(getFileExtension(filePathLabel.getText()))) {
            // display error
            errorLabelVisible(true);

            // disable load button
            disableLoadButton(true);

            // return from method
            return;
        }

        // attempt to open the file using the selected string path
        File importFile = openFile(filePathLabel.getText());

        // null case
        if(importFile == null) {
            // display error
            errorLabelVisible(true);

            // disable load button
            disableLoadButton(true);

            // return from method
            return;
        }

        // override the current inventory to the new list imported by file
        inventory = importItems(importFile);

        // return to old screen
        new MainSceneController(inventory,(Stage)(loadItemsButton.getScene().getWindow()));
    }

    // Open a file given a string
    protected File openFile(String path) {
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
    protected List<Item> importItems(File file) {
        // get the file extension
        String fileExtension = file.getPath();
        String fileType = getFileExtension(fileExtension);

        // determine which file type to parse
        return switch (fileType) {
            // if txt, call parseTSVFile
            case "txt" -> parseTSVFile(file);

            // if JSON, call parseJSONFile
            case "json" -> parseJSONFile(file);

            // if html, call parseHTMLFile
            case "html" -> parseHTMLFile(file);

            // fail case
            default -> null;
        };
    }

    // Parse a TSV file
    protected List<Item> parseTSVFile(File file) {
        // create new list to be returned
        List<Item> newList = new ArrayList<>();

        // create new Scanner
        Scanner stream = createScanner(file);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // break method
            return Collections.emptyList();
        }

        // loop through the file
        while(stream.hasNextLine()) {
            // read next line
            String nextLine = stream.nextLine();

            // create new array of String
            String[] values = nextLine.split("\t",3);

            // add new Item
            newList.add(new Item(values[1],values[0],values[2].substring(1)));
        }

        // close the input stream
        stream.close();

        // return the Array List
        return newList;
    }

    // Parse a JSON file
    protected List<Item> parseJSONFile(File file) {
        // create new ArrayList<Item>
        List<Item> newList = new ArrayList<>();

        // create closeable stream
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            // display error
            errorLabelVisible(true);

            // return from method
            return Collections.emptyList();
        }

        // create new JsonStreamParser from the file
        JsonStreamParser stream = createJsonStream(fileInputStream);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // return from method
            return Collections.emptyList();
        }
        // loop while the array still has items
        while(stream.hasNext()) {
            // create new JSONObject for item
            JsonObject item = stream.next().getAsJsonObject();

            // add new Item to ArrayList using the 3 strings retrieved above
            newList.add(new Item(item.get("name").getAsString(),
                    item.get("serialNumber").getAsString(),
                    item.get("cost").getAsString().substring(1)));
        }

        // close stream
        try {
            fileInputStream.close();
        }
        catch (IOException e) {
            // display error
            errorLabelVisible(true);

            // return from method
            return Collections.emptyList();
        }

        // return the Array List
        return newList;
    }

    // Parse a HTML file
    protected List<Item> parseHTMLFile(File file) {
        // create new ArrayList<Item>
        List<Item> newList = new ArrayList<>();

        // create new Scanner
        Scanner stream = createScanner(file);

        // null check
        if(stream == null) {
            // display error
            errorLabelVisible(true);

            // break method
            return Collections.emptyList();
        }

        // read entire file into 1 string
        StringBuilder all = new StringBuilder();
        while(stream.hasNext()) {
            all.append(stream.nextLine());
        }

        // create array of strings broken by "<td>"
        String[] split = all.toString().split("</tr>",Integer.MAX_VALUE);

        // remove first and last value (html tags)
        List<String> items = new ArrayList<>(Arrays.asList(split));
        items.remove(items.size() - 1);
        items.remove(0);

        // separate values in string and create new item
        for(String s: items) {
            // if value is null, terminate
            if(s == null) break;

            // split item values
            String[] values = s.split("</td>",3);

            // remove tags from each item
            for(int i = 0; i < 3; i++) {
                values[i] = values[i].replace("</td>","");
                values[i] = values[i].replace("<td>","");
                values[i] = values[i].replace("<tr>","");
                values[i] = values[i].replace("</tr>","");
            }

            // add item to list
            newList.add(new Item(values[1],values[0],values[2].substring(1)));
        }

        // close the input stream
        stream.close();

        // return the Array List
        return newList;
    }

    // Method which creates a JSON file stream
    protected JsonStreamParser createJsonStream(FileInputStream stream) {
        if(stream != null) return new JsonStreamParser(new InputStreamReader(stream,StandardCharsets.UTF_8));
        else return null;
    }

    // Method which tries to create a Scanner stream
    protected Scanner createScanner(File file) {
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
