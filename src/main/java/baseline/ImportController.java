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
import java.util.List;
import java.util.Objects;

import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

    // Declare boolean representing if a selected file is valid
    private boolean isValidFile;

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

        // if the extension is not one of the valid 3 options:
            // display the error label
            // set isFileValid to false
        // if the extension is valid:
            // hide the error label
            // set isFileValid to true
            // set the path label to the file path
    }

    // Extract the file path from a String as long as it is .txt., .html, or .json
    private String getFileExtension(String path) {
        // get the last index of a '.' in the string
        // grab the substring of values following that '.'
        // if '.' could be found, return the substring
        // otherwise return null
    }

    // Determine if a string is one of the 3 valid file extensions
    private boolean isValidFileExtension(String extension) {
        // if the extension is .txt, .html, or .json, return true
        // otherwise return false
    }

    // Determine the visibility of the error label
    private void errorLabelVisible(boolean value) {
        // if value is true, set label to opacity of 1
        // if value is false, set label to opacity of 0
    }

    // Attempt to import a list of items to the application
    @FXML
    void loadItems(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // if the file path is not selected:
            // display error
            // return from method

        // attempt to open the file using the selected string path
        File importFile = openFile(filePathLabel.getText());

        // if file could not be opened (importFile == null):
            // display error
            // return from method
        // if file was found:
            // it's guaranteed file extension is one of the three, so we proceed:
            // change the saved inventory to the new list imported by the file
            inventory = importItems(importFile);

        // return to old screen
        new MainSceneController(inventory,(Stage)(loadItemsButton.getScene().getWindow()));
    }

    // Open a file given a string
    private File openFile(String path) {
        // try to return a new File from the file path
        // catch exception:
            // return null
    }

    // Given a file, determine a method of parsing, and return the parsed list created by another method
    // Precondition: file is not null, and is one of three extensions
    private List<Item> importItems(File file) {
        // get the file extension
        String fileExtension = file.getPath();

        // if the extension is txt, parse and return the TSV file
        // if the extension is .json, parse and return the JSON file
        // if the extension is .html, parse  and return the HTML file
    }

    // Parse a TSV file
    private List<Item> parseTSVFile(File file) {
        // create new ArrayList<Item>
        // create new Scanner for file
        // while Scanner has input:
            // read next line
            // create new array of String
            // split the line by "\t" character and populate String array
            // add new Item to ArrayList using the 3 strings in the string array
        // close the input stream
        // return the Array List
    }

    // Parse a JSON file
    private List<Item> parseJSONFile(File file) {
        // create new ArrayList<Item>
        // create new JsonStreamParser from the file
        // create new JsonArray from JsonStreamParser
        // while the JsonArray has a next "item":
            // get the next position "serial" JsonObject as a string
            // get the next position "name" JsonObject as a string
            // get the next position "cost" JsonObject as a string
            // add new Item to ArrayList using the 3 strings retrieved above
        // close the input stream
        // return the Array List
    }

    // Parse a HTML file
    private List<Item> parseHTMLFile(File file) {
        // create new ArrayList<Item>
        // create new Scanner for file
        // while Scanner has input of "<td>"
            // find next "<td>"
            // save line as string
            // split the string by "</td>" and populate String array
            // remove "<td>" from each string
            // add new Item to ArrayList using the 3 strings in the string array
        // close the input stream
        // return the Array List
    }
}
