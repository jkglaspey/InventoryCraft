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

    @FXML
    private HBox buttonsHBox;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField costTextField;

    @FXML
    private Label errorBlankLabel;

    @FXML
    private Label errorCostLabel;

    @FXML
    private Label errorNameLabel;

    @FXML
    private Label errorSerialLabel;

    @FXML
    private HBox nameLabelBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button confirmButton;

    @FXML
    private StackPane pane;

    @FXML
    private Pane positionPane1;

    @FXML
    private Pane positionPane2;

    @FXML
    private Pane positionPaneSmall1;

    @FXML
    private Pane positionPaneSmall2;

    @FXML
    private Pane positionPaneSmall3;

    @FXML
    private TextField serialTextField;

    @FXML
    private HBox textHBox;

    @FXML
    private VBox textVBox;

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

    public ItemController(List<Item> inventory, Stage stage) {
        this.inventory = inventory;
        this.index = inventory.size();

        // change the button text to "Create New Item"

        // load the correct fxml file
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

    public ItemController(List<Item> inventory, int index, Stage stage) {
        this.inventory = inventory;
        this.index = index;

        // change the button text to "Edit Item"

        // load the correct fxml file
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

    public void initialize() {
        // change error text color
        errorNameLabel.setStyle("-fx-text-fill: red");
        errorSerialLabel.setStyle("-fx-text-fill: red");
        errorCostLabel.setStyle("-fx-text-fill: red");
        errorBlankLabel.setStyle("-fx-text-fill: red");

        // create methods for finding errors
        // when errors are fixed, make labels invisible
        // also, panes don't need to be resized, so initialize may not need to be called lol. Just change label size
    }

    @FXML
    void cancel(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

    @FXML
    void enterCost(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();
    }

    @FXML
    void enterName(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();
    }

    @FXML
    void enterSerial(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();
    }

    @FXML
    void completeScene(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // verify the name text field is valid
        if(isNameInvalid(nameTextField.getText())) {

        }

        // verify each box is filled
        if(!(costTextField.getText().isEmpty()) && !(nameTextField.getText().isEmpty()) && !(serialTextField.getText().isEmpty())) {
            // return the item to the other scene
            inventory.set(index,new Item(nameTextField.getText(), serialTextField.getText(), costTextField.getText()));

            // reload other scene
            new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
        }
    }

    // Method for testing the validity of the name
    private boolean isNameInvalid(String name) {
        return name.length() < 2 || name.length() > 256;
    }
}
