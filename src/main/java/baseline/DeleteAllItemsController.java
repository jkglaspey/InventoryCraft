package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteAllItemsController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    // Declare sound for clicking button
    private final AudioClip buttonSoundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());

    // Grab the previous inventory
    private List<Item> inventory;

    // Declare a fxml loader
    private FXMLLoader root;

    // Declare the loaded scene
    private Parent scene;

    public DeleteAllItemsController(List<Item> inventory, Stage stage) {
        this.inventory = inventory;

        // load the correct fxml file
        root = null;
        scene = null;
        try {
            root = new FXMLLoader(Objects.requireNonNull(getClass().getResource("deleteAllItemsScene.fxml")));
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

    @FXML
    void cancel(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // return to old screen
        new MainSceneController(inventory,(Stage)(cancelButton.getScene().getWindow()));
    }

    @FXML
    void deleteItems(ActionEvent event) {
        // play button sound
        buttonSoundPlayer.play();

        // clear inventory

        // return to old screen
        new MainSceneController(inventory,(Stage)(cancelButton.getScene().getWindow()));
    }
}
