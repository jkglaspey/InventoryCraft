package baseline;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class LoadSceneController {

    // Define the borders of the window
    @FXML
    private StackPane pane;

    // Define panes for holding relative button positions
    @FXML
    private Pane pane1;

    @FXML
    private Pane pane4;

    // Show the load bar media
    @FXML
    private MediaView loadView;

    // Show the menu media
    @FXML
    private MediaView menuView;

    // Declare pane for having white background with fade effect
    @FXML
    private Pane paneColor;

    // Declare button for changing scenes
    @FXML
    private Button sceneButton;

    // Declare button for leaving first window (stylistic)
    @FXML
    private Button exitButton;

    // Declare button for opening up the GitHub repository
    @FXML
    private Button userGuideButton;

    // Declare label for displaying current application version
    @FXML
    private Label versionLabel;

    // Declare pane for holding y-positions of controls
    @FXML
    private VBox vBox;

    // Declare pane for holding x-positions of controls
    @FXML
    private HBox hBox;

    // Initialize button-click sound
    AudioClip soundPlayer = new AudioClip(getClass().getResource("sound/buttonClick.mp3").toExternalForm());

    // Initialize values
    public void initialize() {
        // initialize media
        MediaPlayer loadBarPlayer = new MediaPlayer(new Media(Objects.requireNonNull(this.getClass().getResource("video/loadbar.mp4")).toExternalForm()));
        MediaPlayer menuPlayer = new MediaPlayer(new Media(Objects.requireNonNull(this.getClass().getResource("video/menu.mp4")).toExternalForm()));
        loadBarPlayer.setAutoPlay(true);

        // allow media players to expand to the dimensions of stack pane
        loadView.fitWidthProperty().bind(pane.widthProperty());
        loadView.fitHeightProperty().bind(pane.heightProperty());
        menuView.fitWidthProperty().bind(pane.widthProperty());
        menuView.fitHeightProperty().bind(pane.heightProperty());

        // prevent "dirt" screen from interfering with transitions
        paneColor.prefWidthProperty().bind(pane.widthProperty());
        paneColor.prefHeightProperty().bind(pane.heightProperty());

        // hold button positions
        vBox.setVgrow(pane1,Priority.ALWAYS);
        vBox.setVgrow(pane4,Priority.ALWAYS);

        // play first media
        loadView.setMediaPlayer(loadBarPlayer);

        // add listener to switch from loading screen to menu screen
        loadBarPlayer.setOnEndOfMedia(() -> {
            // change borderpane background color
            paneColor.setStyle("-fx-background-color: #FFFFFF");

            // fade out the old scene
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), loadView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.play();

            // initialize the new menu scene
            menuPlayer.setAutoPlay(true);
            menuView.setMediaPlayer(menuPlayer);
            menuPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // fade in the new scene
            FadeTransition fadeInBackground = new FadeTransition(Duration.seconds(1), menuView);
            fadeInBackground.setFromValue(0.0);
            fadeInBackground.setToValue(1.0);
            fadeInBackground.play();

            // fade in bottom text
            FadeTransition fadeInLabel = new FadeTransition(Duration.seconds(1),versionLabel);
            fadeInLabel.setFromValue(0.0);
            fadeInLabel.setToValue(1.0);
            fadeInLabel.play();

            // listen for fade to end, then appear buttons
            fadeInBackground.setOnFinished((e) -> {
                // change user guide button to have a gif background
                userGuideButton.setStyle("-fx-background-image: url('/baseline/image/buttonuserguide.gif')");

                // initialize button fade-ins
                FadeTransition fadeInSceneButton = new FadeTransition(Duration.seconds(1), sceneButton);
                fadeInSceneButton.setFromValue(0.0);
                fadeInSceneButton.setToValue(1.0);
                fadeInSceneButton.play();

                FadeTransition fadeInExitButton = new FadeTransition(Duration.seconds(1), exitButton);
                fadeInExitButton.setFromValue(0.0);
                fadeInExitButton.setToValue(1.0);
                fadeInExitButton.play();

                FadeTransition fadeInGuideButton = new FadeTransition(Duration.seconds(1), userGuideButton);
                fadeInGuideButton.setFromValue(0.0);
                fadeInGuideButton.setToValue(1.0);
                fadeInGuideButton.play();
            });
        });
    }

    // Method which changes the scene
    @FXML
    void changeSceneToMenu(ActionEvent event) {
        soundPlayer.play();

        // set the scene to a new window
        MainSceneController scene = new MainSceneController(new ArrayList<>());
    }

    // Method which closes the system
    @FXML
    void closeWindow(ActionEvent event) {
        soundPlayer.play();
        Platform.exit();
    }

    // Method which opens the GitHub repository
    @FXML
    void openUserGuide(ActionEvent event) {
        soundPlayer.play();

        // try to open link to site
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/jkglaspey/glaspey-app2"));
        }
        // could not open browser
        catch (IOException e) {
            e.printStackTrace();
        }
        // could not recognize link address
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}