/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to allow the user to either create a new item or edit a preexisting item.

package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemController {
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

    // Declare text field for modifying the name
    @FXML
    private TextField nameTextField;

    // Declare button for either creating or editing a new item
    @FXML
    private Button confirmButton;

    // Declare parent pane
    @FXML
    private StackPane pane;

    // Declare text field for modifying the serial number
    @FXML
    private TextField serialTextField;

    // Declare index of item being inserted
    private final int index;

    // Declare sound for clicking button
    private final AudioClip buttonSoundPlayer = new AudioClip(Objects.requireNonNull(getClass().getResource("sound/buttonClick.mp3")).toExternalForm());

    // Grab the previous inventory
    private final List<Item> inventory;

    // Define a constant for fx effects
    private static final String RED = "-fx-text-fill: red";

    // Call scene to create a new item
    public ItemController(List<Item> inventory, Stage stage) {
        // copy existing inventory
        this.inventory = inventory;

        // set the insert index to the end of the list (append a new item)
        this.index = inventory.size();

        // load the correct fxml file
        loadScene(stage);
    }

    // Create a no-parameter controller for testing
    public ItemController() {
        inventory = new ArrayList<>();
        index = 0;
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
        FXMLLoader root;
        // Declare the loaded scene
        Parent scene;
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
        errorNameLabel.setStyle(RED);
        errorSerialLabel.setStyle(RED);
        errorCostLabel.setStyle(RED);
        errorBlankLabel.setStyle(RED);

        // right justify error text
        errorNameLabel.setAlignment(Pos.BASELINE_RIGHT);
        errorSerialLabel.setAlignment(Pos.BASELINE_RIGHT);
        errorCostLabel.setAlignment(Pos.BASELINE_RIGHT);

        // determine which button was pressed
        if(index < inventory.size()) {
            // copy the item fields to text fields
            nameTextField.setText(inventory.get(index).getName());
            serialTextField.setText(inventory.get(index).getSerialNumber());

            // remove the '$' from the cost
            costTextField.setText(String.valueOf(inventory.get(index).getCost()).substring(1));

            // change the confirm button text to "Edit Item"
            confirmButton.setText("Edit Item");
        }
        // "create new item" was selected
        else {
            confirmButton.setText("Create New Item");
        }
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
        if(isNoErrors(name,serialNumber,cost)) {
            // if creating a new item, add a new item to the end of the list
            if(index == inventory.size()) inventory.add(new Item(name,serialNumber,cost));
            // otherwise, set the item at index to a new item using the declared strings
            else inventory.set(index, new Item(name, serialNumber, cost));

            // reload other scene
            new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
        }
        // if there are errors, display / hide the appropriate error labels
        // run each test again using declared strings
        setBlankErrorLabelInvisible(isNotEmpty(name,serialNumber,cost));
        setNameErrorLabelInvisible(isNameValid(name));
        boolean serialNumberUnique = isSerialNumberUnique(serialNumber,index,inventory);
        changeSerialNumberText(serialNumberUnique);
        setSerialNumberErrorLabelInvisible(serialNumberUnique && isSerialNumberValid(serialNumber));
        setCostErrorLabelInvisible(isCostValid(cost));
    }

    // Test if all displayed text fields are filled and valid
    protected boolean isNoErrors(String name, String serialNumber, String cost) {
        // verify each string is not empty
        // verify the name input is valid
        // verify the serial number input is valid
        // verify the cost input is valid
        return (isNotEmpty(name,serialNumber,cost) && isNameValid(name) && isCostValid(cost)) &&
                isSerialNumberValid(serialNumber) && isSerialNumberUnique(serialNumber,index,inventory);
    }

    // Method for testing if any of the text fields are not filled
    protected boolean isNotEmpty(String name, String serialNumber, String cost) {
        // if all three strings are not empty, return true
        return (!name.isBlank()) && (!serialNumber.isBlank()) && (!cost.isBlank());
    }

    // Method for testing the validity of the name
    protected boolean isNameValid(String name) {
        return (name.length() >= 2) && (name.length() <= 256);
    }

    // Method for testing if the serial number input is valid
    // Note: the format is A-XXX-XXX-XXX
    protected boolean isSerialNumberValid(String serialNumber) {
        // verify there are 3 hyphens
        if(serialNumber.chars().filter(ch -> ch == '-').count() != 3) return false;

        // split the string into a size 4 array divided by '-'
        String[] values = serialNumber.split("-",4);

        // verify there are 4 strings in the array
        if(values.length != 4) return false;

        // check if the first string is a single letter
        if((values[0].length() != 1) || !(values[0].matches("^[a-zA-Z]*$"))) return false;

        // check if the second, third, and fourth strings are comprised of 3 letters or digits
        for(int i = 1; i < 4; i++) {
            if ((values[i].length() != 3) || !(values[i].matches("^[a-zA-Z0-9]+$"))) return false;
        }

        // serial number must be valid
        return true;
    }

    // Method for testing if the cost is valid
    protected boolean isCostValid(String cost) {
        // determine if string can be parsed
        try {
            Float.parseFloat(cost);
            return true;
        }
        // catch non-numeric string
        catch(NumberFormatException ex) {
            return false;
        }
    }

    // Method for changing the name error label visibility
    private void setNameErrorLabelInvisible(boolean value) {
        // set label visibility
        if(value) errorNameLabel.setOpacity(0.0);
        else errorNameLabel.setOpacity(1.0);
    }

    // Method for changing the serial number error label visibility
    private void setSerialNumberErrorLabelInvisible(boolean value) {
        // set label visibility
        if(value) errorSerialLabel.setOpacity(0.0);
        else errorSerialLabel.setOpacity(1.0);
    }

    // Method for changing the cost error label visibility
    private void setCostErrorLabelInvisible(boolean value) {
        // set label visibility
        if(value) errorCostLabel.setOpacity(0.0);
        else errorCostLabel.setOpacity(1.0);
    }

    // Method for testing if a serial number is unique
    protected boolean isSerialNumberUnique(String serialNumber, int index, List<Item> list) {
        // loop through the list
        for(int i = 0; i < list.size(); i++) {
            // if index value ever equals loop index, skip current check
            if(i == index) continue;

            // if serial numbers match, return false
            if(serialNumber.equals(list.get(i).getSerialNumber())) return false;
        }
        // otherwise, return true
        return true;
    }

    // Method for changing the empty text fields error label visibility
    private void setBlankErrorLabelInvisible(boolean value) {
        // set label visibility
        if(value) errorBlankLabel.setOpacity(0.0);
        else errorBlankLabel.setOpacity(1.0);
    }

    // Method for changing the text of the serial number error label
    private void changeSerialNumberText(boolean value) {
        // if serial number is unique, change to invalid error
        if(value) errorSerialLabel.setText("Must be in the correct format!");

        // else change to not-unique error
        else errorSerialLabel.setText("Must be unique!");
    }
}
