/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to allow the user to export a list of items as one of three file types to local storage.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

public class SaveController {

    // Declare button for cancelling the action
    @FXML
    private Button cancelButton;

    // Declare button for opening a directory
    @FXML
    private Button chooseFileButton;

    // Declare a label that will display an error if the file path is invalid or blank
    @FXML
    private Label errorLabel;

    // Declare a radio button group for specifying the file extension
    @FXML
    private ToggleGroup fileButtons;

    // Declare a text field for setting the file name
    @FXML
    private TextField fileNameField;

    // Declare a label for displaying the selected file path
    @FXML
    private Label filePathLabel;

    // Declare a box to horizontally position each of the controls
    @FXML
    private HBox hBox;

    // Declare a radio button to select exporting a file as .html
    @FXML
    private RadioButton htmlButton;

    // Declare a radio button to select exporting a file as .json
    @FXML
    private RadioButton jsonButton;

    // Declare the parent pane
    @FXML
    private StackPane pane;

    // Declare a pane for positioning controls
    @FXML
    private Pane positionPane1;

    // Declare a button to attempt to save an inventory to a file in the local storage
    @FXML
    private Button saveButton;

    // Declare a radio button to select exporting a file as .txt
    @FXML
    private RadioButton tsvButton;

    // Declare sound for clicking button
    private final AudioClip buttonSoundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());
    private final AudioClip smallButtonSoundPlayer = new AudioClip(getClass().getResource("sound/smallButtonClick.mp3").toExternalForm());

    // Grab the previous inventory
    private List<Item> inventory;

    // Declare a fxml loader
    private FXMLLoader root;

    // Declare the loaded scene
    private Parent scene;

    // Declare constructor to create scene
    public SaveController(List<Item> inventory, Stage stage) {
        this.inventory = inventory;

        // load the correct fxml file
        root = null;
        scene = null;
        try {
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("saveScene.fxml")));
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

    // Change the error color
    public void initialize() {
        // change error text color
        errorLabel.setStyle("-fx-text-fill: red");
    }

    // Method which cancels the current action
    @FXML
    void cancel(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

    // Button which opens a directory chooser to specify the new file's saving location
    @FXML
    void chooseFile(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // open a new directory chooser and select the desired file path
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if(file != null) {
            filePathLabel.setText(file.getPath());
        }
    }

    // Methods which play the small button click sound
    @FXML
    void playSmallButtonClick(ActionEvent event) {
        // play small click sound
        smallButtonSoundPlayer.play();
    }

    // Plays a button click sound when the user enters a name
    @FXML
    void enterName(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Method which attempts to save an inventory to the local storage
    @FXML
    void saveItems(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // leave the method if the file path is invalid
        if(isFilePathInvalid(filePathLabel.getText())) {
            // make error label visible
            isErrorLabelVisible(true);

            // leave method
        }

        // set error label invisible
        isErrorLabelVisible(false);

        // get the selected button from the radio group
        RadioButton selectedButton = (RadioButton)fileButtons.getSelectedToggle();
        String fileType = selectedButton.getText();

        // determine which file type the inventory is being exported as
        // if TSV, call saveToTSV()
        // if JSON, call saveToJSON()
        // if html, call saveToHTML()

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

    // Method which determines the validity of the specified file path
    // Note: this is only for if the user decides to delete the file path after specifying it
    private boolean isFilePathInvalid(String path) {
        // use Paths class
        // if Paths.find(path) does not throw an exception, return true
        // if an exception is thrown, return false
    }

    // Method which changes the visibility of the error message
    private void isErrorLabelVisible(boolean value) {
        // if true, set the error label opacity to 1
        // if false, set the error label opacity to 0
    }

    // Save the inventory to a TSV file
    private void saveToTSV(String path, String name) {
        // combine the file path, name, and extension
        String save = combinePath(path, name, ".txt");

        // create a file from the string path
        // try to create a formatter stream to the file
        // if exception is thrown:
            // change error label to visible
            // leave method
        // for each Item in inventory
            // create a new String builder
            // add the serial number
            // add "\t"
            // add the name
            // add "\t"
            // add the cost
            // write to new line in file
        // close Formatter
    }

    // Save the inventory to a JSON file
    private void saveToJSON(String path, String name) {
        // combine the file path, name, and extension
        String save = combinePath(path,name,".json");

        // create a new JsonArray
        // loop through inventory
            // add a new JsonObject
            // add a temp JsonObject "temp"
            // Put serial number into "serial" category of temp
            // Put name into "name" category of temp
            // Put cost into "cost" category of temp
            // Put the temp into the "item" category of the original JsonObject
            // Put the original JsonObject into the JsonArray
        // try:
            // to create a file from the string path
            // to create a new formatter at the file path
            // to write the JSON array to the created file as a string
        // if exception is thrown:
            // change error label to visible
            // leave method
    }

    // Save the inventory to a HTML file
    private void saveToHTML(String path, String name) {
        // combine the file path, name, and extension
        String save = combinePath(path,name,".html");

        // create a file from the string path
        // try to create a formatter stream to the file
        // if exception is thrown:
            // change error label to visible
            // leave method
        // write a basic heading
            // stream.format("<html><head><title>%s</title></head><body>",name);
            // stream.format("<table><tr><th>Serial Number</th><th>Name</th><th>Cost (S)<t/h>);
        // loop through inventory
            // format "<tr>"
            // format "<td>" + serial number of item + "</td>"
            // format "<td>" + name of item + "</td>"
            // format "<td>" + cost of item + "</td>"
            // format "</tr>"
        // write closing tags
            // stream.format("<\table></body></html>");
        // close stream
    }

    // Concatenate parts of a string together
    private String combinePath(String path, String name, String extension) {
        // return the combined string using StringBuilder
    }
}
