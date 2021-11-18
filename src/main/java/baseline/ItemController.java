/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to allow the user to either create a new item or edit a preexisting item.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ItemController {

    // Declare box for positioning bottom two buttons
    @FXML
    private HBox buttonsHBox;

    // Declare button for cancelling the current action
    @FXML
    private Button cancelButton;

    // Declare text field for modifying the cost
    @FXML
    private TextField costTextField;

    // Declare label for displaying an error if there is a blank text field
    @FXML
    private Label errorBlankLabel;

    // Declare label for displaying an error if the cost cannot be converted to a number
    @FXML
    private Label errorCostLabel;

    // Declare label for displaying an error if there is not a valid range of characters for the name
    @FXML
    private Label errorNameLabel;

    // Declare label for displaying an error if the serial label is not in the proper format
    @FXML
    private Label errorSerialLabel;

    // Declare box for positioning the name label
    @FXML
    private HBox nameLabelBox;

    // Declare text field for modifying the name
    @FXML
    private TextField nameTextField;

    // Declare button for either creating or editing a new item
    @FXML
    private Button confirmButton;

    // Declare parent pane
    @FXML
    private StackPane pane;

    // Declare a pane for holding the controls in a proper position
    @FXML
    private Pane positionPane1;

    // Declare a pane for holding the controls in a proper position
    @FXML
    private Pane positionPane2;

    // Declare a pane for holding the controls in a proper position
    @FXML
    private Pane positionPaneSmall1;

    // Declare a pane for holding the controls in a proper position
    @FXML
    private Pane positionPaneSmall2;

    // Declare a pane for holding the controls in a proper position
    @FXML
    private Pane positionPaneSmall3;

    // Declare text field for modifying the serial number
    @FXML
    private TextField serialTextField;

    // Declare box for horizontally positioning the text fields on the scene
    @FXML
    private HBox textHBox;

    // Declare box for vertically positioning the text fields on the scene
    @FXML
    private VBox textVBox;

    // Declare box for vertically stacking every control on the scene
    @FXML
    private VBox vbox;

    // Declare index of item being inserted
    private final int index;

    // Declare sound for clicking button
    private final AudioClip buttonSoundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());

    // Grab the previous inventory
    private List<Item> inventory;

    // Declare a fxml loader
    private FXMLLoader root;

    // Declare the loaded scene
    private Parent scene;

    // Call scene to create a new item
    public ItemController(List<Item> inventory, Stage stage) {
        // copy existing inventory
        this.inventory = inventory;

        // set the insert index to the end of the list (append a new item)
        this.index = inventory.size();

        // load the correct fxml file
        loadScene(stage);
    }

    // Call scene to edit an existing item
    public ItemController(List<Item> inventory, int index, Stage stage) {
        // copy existing inventory
        this.inventory = inventory;

        // copy the index of the item to be modified
        this.index = index;

        // load the correct fxml file
        loadScene(stage);
    }

    // Load the item scene
    private void loadScene(Stage stage) {
        root = null;
        scene = null;
        try {
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("itemScene.fxml")));
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

    // Initialize fxml values
    public void initialize() {
        // change error text color
        errorNameLabel.setStyle("-fx-text-fill: red");
        errorSerialLabel.setStyle("-fx-text-fill: red");
        errorCostLabel.setStyle("-fx-text-fill: red");
        errorBlankLabel.setStyle("-fx-text-fill: red");

        // if the item index < inventory size (edit item)
            // copy the item name to the name text field
            // copy the item serial number to the serial text field
            // copy the item cost to the cost text field
            // change the confirm button text to "Edit Item"
        // otherwise change the confirm button text to "Create New Item"
    }

    // Cancel the current action
    @FXML
    void cancel(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

    // Trigger button sound for entering a value in a text field
    @FXML
    void playButtonClick(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();
    }

    // Attempt to either add a new item or modify an item in the inventory and return to the old scene
    @FXML
    void completeScene(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // get the strings in each text field
        String name = nameTextField.getText();
        String serialNumber = serialTextField.getText();
        String cost = costTextField.getText();

        // verify there are no errors
        if(isNoErrors()) {
            // if the index == size, add a new item to the inventory using the declared strings
            // otherwise, set the index position in the inventory to a new item using the declared strings

            // reload other scene
            new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
        }
        // if there are errors, display / hide the appropriate error labels
        // first test if there are blank values
        if(isNotEmpty(name,serialNumber,cost)) {
            // hide blank error label
            setBlankErrorLabelInvisible(true);

            // run each test again using declared strings
            setNameErrorLabelInvisible(isNameValid(name));
            setSerialNumberErrorLabelInvisible(isSerialNumberValid(serialNumber));
            setCostErrorLabelInvisible(isCostValid(cost));
        }
        // if there are blank values, make blank error label appear
        setBlankErrorLabelInvisible(false);
    }

    // Test if all displayed text fields are filled and valid
    private boolean isNoErrors() {
        // verify each string is not empty
        // verify the name input is valid
        // verify the serial number input is valid
        // verify the cost input is valid
        // if everything is valid, return true
        // otherwise return false
    }

    // Method for testing if any of the text fields are not filled
    private boolean isNotEmpty(String name, String serialNumber, String cost) {
        // if all three strings are not empty, return true
        // otherwise return false
    }

    // Method for testing the validity of the name
    private boolean isNameValid(String name) {
        return name.length() > 2 && name.length() < 256;
    }

    // Method for testing if the serial number input is valid
    // Note: the format is A-XXX-XXX-XXX
    private boolean isSerialNumberValid(String serialNumber) {
        // split the string into a size 4 array divided by '-'
        // if there is not 4 strings in the array, there is not 4 '-'
        // check if the first string is a single letter
        // check if the second, third, and fourth strings are comprised of 3 letters or digits
        // if everything is true, return true
        // otherwise return false
    }

    // Method for testing if the cost is valid
    private boolean isCostValid(String cost) {
        // try to parse string to double
        // if success, return true
        // if an exception is thrown, return false
    }

    // Method for changing the name error label visibility
    private void setNameErrorLabelInvisible(boolean value) {
        // if true, set the label to visible
        // if false, set the label to invisible
    }

    // Method for changing the serial number error label visibility
    private void setSerialNumberErrorLabelInvisible(boolean value) {
        // if true, set the label to visible
        // if false, set the label to invisible
    }

    // Method for changing the cost error label visibility
    private void setCostErrorLabelInvisible(boolean value) {
        // if true, set the label to visible
        // if false, set the label to invisible
    }

    // Method for changing the empty text fields error label visibility
    private void setBlankErrorLabelInvisible(boolean value) {
        // if true, set the label to visible
        // if false, set the label to invisible
    }

}
