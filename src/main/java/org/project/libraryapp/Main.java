package org.project.libraryapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        // User interface for adding users
        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Button addUserButton = new Button("Add User");
        Label userFeedback = new Label();

        addUserButton.setOnAction(event -> {
            String username = userTextField.getText();
            try {
                LibraryDatabaseManager.createUser(username);
                userFeedback.setText("User added successfully.");
            } catch (SQLException e) {
                userFeedback.setText("Error adding user.");
                e.printStackTrace();
            }
        });

        GridPane userGrid = new GridPane();
        userGrid.add(userLabel, 0, 0);
        userGrid.add(userTextField, 1, 0);
        userGrid.add(addUserButton, 2, 0);
        userGrid.add(userFeedback, 1, 1);

        // User Table
        TableView<User> userTable = new TableView<>();
        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userTable.getColumns().add(usernameColumn);

        // Load users
        try {
            ObservableList<User> users = LibraryDatabaseManager.getAllUsers();
            userTable.setItems(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Main layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(userGrid, userTable);

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}