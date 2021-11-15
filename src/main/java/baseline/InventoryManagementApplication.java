/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Joshua Glaspey
 */

// The purpose of this class is to initialize the GUI and run the application.

package baseline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        // declare parent FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loadScene.fxml")));

        // set the scene and apply style
        Scene scene = new Scene(root,1280,720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());

        // initialize the GUI
        stage.setTitle("InventoryCraft");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icon.png"))));
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    // Launch the GUI
    public static void main(String[] args) {
        // load custom fonts
        Font.loadFont(InventoryManagementApplication.class.getResourceAsStream("font/MinecraftRegular-Bmg3.otf"), 24);
        Font.loadFont(InventoryManagementApplication.class.getResourceAsStream("font/MinecraftBold-nMK1.otf"), 24);
        Font.loadFont(InventoryManagementApplication.class.getResourceAsStream("font/MinecraftBoldItalic-1y1e.otf"), 24);
        Font.loadFont(InventoryManagementApplication.class.getResourceAsStream("font/MinecraftItalic-R8Mo.otf"), 24);

        // run the application
        launch(args);
    }
}