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
import java.util.List;
import java.util.Objects;

public class SaveController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button chooseFileButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ToggleGroup fileButtons;

    @FXML
    private TextField fileNameField;

    @FXML
    private Label filePathLabel;

    @FXML
    private HBox hBox;

    @FXML
    private RadioButton htmlButton;

    @FXML
    private RadioButton jsonButton;

    @FXML
    private StackPane pane;

    @FXML
    private Pane positionPane1;

    @FXML
    private Button saveButton;

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

    @FXML
    void cancel(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

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

    @FXML
    void clickHTMLButton(ActionEvent event) {
        // play small click sound
        smallButtonSoundPlayer.play();
    }

    @FXML
    void clickJSONButton(ActionEvent event) {
        // play small click sound
        smallButtonSoundPlayer.play();

    }

    @FXML
    void clickTSVButton(ActionEvent event) {
        // play small click sound
        smallButtonSoundPlayer.play();

    }

    @FXML
    void enterName(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    @FXML
    void saveItems(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // determine which file to save inventory as
        // save inventory

        // return to old screen
        new MainSceneController(inventory,(Stage)(pane.getScene().getWindow()));
    }

}
