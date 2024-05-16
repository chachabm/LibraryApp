package org.project.libraryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // Instantiate UIManager and set up the UI
        UsersUI usersUi = new UsersUI();
        Scene scene = usersUi.createScene();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}