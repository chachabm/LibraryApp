package org.project.libraryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Instantiate the library and the BooksUI
        Library library = new Library();
        BooksUI booksUI = new BooksUI(library);

        // Create the scene and set it on the stage
        Scene scene = booksUI.createScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage Books");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}