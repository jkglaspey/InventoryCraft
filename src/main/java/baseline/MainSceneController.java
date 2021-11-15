package baseline;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainSceneController {

    // Declare pane for keeping vertical order of screen
    @FXML
    private VBox bindPane;

    // Declare container for bottom buttons
    @FXML
    private HBox bottomControls;

    // Declare radio button which searches for an item by name
    @FXML
    private RadioButton buttonName;

    // Declare button which deletes all items when pushed
    @FXML
    private Button deleteAllItemsButton;

    // Declare button which deletes the selected item when pushed
    @FXML
    private Button deleteItemButton;

    // Declare button which allows the user to edit the selected item when pushed
    @FXML
    private Button editItemButton;

    // Declare control for showing all the inventory data to the user
    @FXML
    private TableView<Item> itemView;

    // Declare observable list for holding only the viewable items (either ALL or searched results)
    private ObservableList<Item> listOfItems;

    // Declare list for holding all items in an inventory
    private List<Item> inventory;

    // Declare button for loading in a previously-saved inventory of items
    @FXML
    private Button loadInventoryButton;

    // Declare container for center table view
    @FXML
    private HBox middleControls;

    // Declare column holding the monetary values of each item
    @FXML
    private TableColumn<Item, String> monetaryColumn;

    // Declare column holding the names of each item
    @FXML
    private TableColumn<Item, String> nameColumn;

    // Declare button for adding a new item to the inventory
    @FXML
    private Button newItemButton;

    // Declare parent pane for all controls
    @FXML
    private StackPane pane;

    // Declare pane for orienting the right side of the top controls
    @FXML
    private Pane positionPane1;

    // Declare pane for orienting the left side of the top controls
    @FXML
    private Pane positionPane2;

    // Declare pane for orienting the right side of the middle controls
    @FXML
    private Pane positionPane3;

    // Declare pane for orienting the left side of the middle controls
    @FXML
    private Pane positionPane4;

    // Declare pane for orienting the right side of the bottom controls
    @FXML
    private Pane positionPane5;

    // Declare pane for orienting the left side of the bottom controls
    @FXML
    private Pane positionPane6;

    // Declare button for saving the current inventory as a file
    @FXML
    private Button saveInventoryButton;

    // Declare group for allowing the user to change the search query
    @FXML
    private ToggleGroup searchButtons;

    // Declare radio button which searches for an item by serial number
    @FXML
    private RadioButton serialButton;

    // Declare column holding the serial numbers of each item
    @FXML
    private TableColumn<Item, String> serialColumn;

    // Declare text box which allows the user to search for items
    @FXML
    private TextField textPane;

    // Declare container for top controls
    @FXML
    private HBox topControls;

    // Initialize button-click sounds
    AudioClip buttonSoundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());
    AudioClip smallButtonSoundPlayer = new AudioClip(getClass().getResource("sound/smallButtonClick.mp3").toExternalForm());

    // Call constructor to save inventory
    public MainSceneController(List<Item> inventory) {
        this.inventory = inventory;

        // display the scene
        display();
    }

    // Display the scene
    private void display() {
        try {
            // load fxml file
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainScene.fxml")));

            // set new stage
            Stage newStage = (Stage)(pane.getScene().getWindow());
            Scene newScene = new Scene(newRoot);
            newStage.setScene(newScene);
            newStage.show();
        }
        // file count not be found
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // Initialize values
    @FXML
    public void initialize() {
        // display the scene
        display();

        // change middle background values
        middleControls.setStyle("-fx-background-image: url('/baseline/image/middlebackground.png')");
        itemView.setStyle("-fx-background-image: url('/baseline/image/tablebackground.png')");

        // remove tableview default text
        itemView.setPlaceholder(new Label(""));

        // bind vbox dimensions to stack pane
        bindPane.prefWidthProperty().bind(pane.widthProperty());
        bindPane.prefHeightProperty().bind(pane.heightProperty());

        // keep controls in vertical center
        bindPane.setVgrow(topControls,Priority.ALWAYS);
        bindPane.setVgrow(middleControls,Priority.ALWAYS);
        bindPane.setVgrow(bottomControls,Priority.ALWAYS);

        // keep controls in horizontal center
        topControls.setHgrow(positionPane1,Priority.ALWAYS);
        topControls.setHgrow(positionPane2,Priority.ALWAYS);
        middleControls.setHgrow(positionPane3,Priority.ALWAYS);
        middleControls.setHgrow(positionPane4,Priority.ALWAYS);
        bottomControls.setHgrow(positionPane5,Priority.ALWAYS);
        bottomControls.setHgrow(positionPane6,Priority.ALWAYS);
    }

    // refresh the table view to update values
    private void refreshTable() {
        nameColumn.setVisible(false);
        nameColumn.setVisible(true);
    }

    // Create a new item by opening a new scene
    @FXML
    void createNewItem(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();

        // open fxml for handling new item creation
        new CreateNewItemController(pane.getScene(),inventory);
    }

    // Delete all items, but open new scene for confirmation
    @FXML
    void deleteAllItems(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Delete currently selected item, but open new scene for confirmation
    @FXML
    void deleteItem(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Edit the currently-selected item in a new scene
    @FXML
    void editItem(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Open a new scene where the user can use a file chooser to select a saved inventory
    @FXML
    void loadInventory(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Open a new scene where the user can save the current inventory to the local machine
    @FXML
    void saveInventory(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Search for an item in the list depending on the search query, and display only the results on the list
    @FXML
    void searchForItem(ActionEvent event) {
        // play click sound
        buttonSoundPlayer.play();
    }

    // Change the search query to "Name"
    @FXML
    void setSearchQueryToName(ActionEvent event) {
        // play click sound
        smallButtonSoundPlayer.play();
    }

    // Change the search query to "Serial Number"
    @FXML
    void setSearchQueryToSerial(ActionEvent event) {
        // play click sound
        smallButtonSoundPlayer.play();
    }
}
