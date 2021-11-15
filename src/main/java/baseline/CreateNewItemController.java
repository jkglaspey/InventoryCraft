package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CreateNewItemController {

    @FXML
    private HBox buttonsHBox;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField costTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button newItemButton;

    @FXML
    private StackPane pane;

    @FXML
    private Pane positionPane1;

    @FXML
    private Pane positionPane2;

    @FXML
    private TextField serialTextField;

    @FXML
    private HBox textHBox;

    @FXML
    private VBox textVBox;

    @FXML
    private VBox vbox;

    // Grab a reference of the main controller
    private Scene oldScene;

    // Grab the previous inventory
    private List<Item> inventory;

    public CreateNewItemController(Scene scene, List<Item> inventory) {
        oldScene = scene;
        this.inventory = inventory;
        display();
    }

    private void display() {
        // set the scene to the correct fxml scene
        try {
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createNewItem.fxml")));
            Stage stage = (Stage)(newItemButton.getScene().getWindow());
            Scene newScene = new Scene(newRoot);
            stage.setScene(newScene);
            stage.show();
        }
        // file count not be found
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void createNewItem(ActionEvent event) {
        // verify each box is filled
        if(!(costTextField.getText().isEmpty()) && !(nameTextField.getText().isEmpty()) && !(serialTextField.getText().isEmpty())) {
            // return the item to the other scene
            inventory.add(new Item(costTextField.getText(), nameTextField.getText(), serialTextField.getText()));

            // reload other scene
            new MainSceneController(inventory);
        }
    }

    @FXML
    void enterCost(ActionEvent event) {

    }

    @FXML
    void enterName(ActionEvent event) {

    }

    @FXML
    void enterSerialNumber(ActionEvent event) {

    }

}
