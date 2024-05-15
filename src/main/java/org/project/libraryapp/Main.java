package org.project.libraryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
            Connection connection = DriverManager.getConnection(LibraryDatabaseManager.URL, LibraryDatabaseManager.USER, LibraryDatabaseManager.PASSWORD);
            System.out.println("Connection established.");
            //LibraryDatabaseManager.createUser("Chacha");
        }catch(SQLException e){
            System.out.println(e);
        }
        primaryStage.setTitle("Library Management System");

        // User interface for adding users

        // Main layout
        VBox mainLayout = new VBox();

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}