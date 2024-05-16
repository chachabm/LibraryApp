package org.project.libraryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginUI {
    private ComboBox<String> userComboBox;
    private Label loginFeedback;
    private Stage primaryStage;

    public LoginUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createScene() {
        Label selectUserLabel = new Label("Select User:");
        userComboBox = new ComboBox<>();
        Button loginButton = new Button("Login");
        loginFeedback = new Label();

        // Load users into the ComboBox
        loadUsers();

        loginButton.setOnAction(actionEvent -> {
            String selectedUser = userComboBox.getValue();
            if (selectedUser != null) {
                loginFeedback.setText("Logged in as " + selectedUser);
                // Open UsersUI Scene
                UsersUI usersUI = new UsersUI();
                Scene userScene = usersUI.createScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("Library Management System");
            } else {
                loginFeedback.setText("Please select a user to login.");
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(selectUserLabel, userComboBox, loginButton, loginFeedback);

        return new Scene(vBox, 800, 600);
    }

    private void loadUsers() {
        try {
            ObservableList<User> users = LibraryDatabaseManager.getAllUsers();
            ObservableList<String> usernames = FXCollections.observableArrayList();
            for (User user : users) {
                usernames.add(user.getUsername());
            }
            userComboBox.setItems(usernames);
        } catch (SQLException e) {
            loginFeedback.setText("Error loading users.");
            e.printStackTrace();
        }
    }
}
