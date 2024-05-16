package org.project.libraryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Library libraryUI;
    public static BooksUI booksUI;
    public static LoginUI loginUI;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Instantiate the library and the BooksUI
        libraryUI = new Library();
        booksUI = new BooksUI(libraryUI);
        loginUI = new LoginUI(primaryStage);

        // Create the scene and set it on the stage
        Scene scene = loginUI.createScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}