/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to open a scene which will prompt the user if they want to delete the current inventory.

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

    // Button for cancelling the current action
    @FXML
    private Button cancelButton;

    // Button for deleting all items from the current inventory
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

    // Initialize the scene
    public DeleteAllItemsController(List<Item> inventory, Stage stage) {
        // copy the inventory
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

        // empty inventory
        inventory.clear();

        // return to old screen
        new MainSceneController(inventory,(Stage)(cancelButton.getScene().getWindow()));
    }
}
