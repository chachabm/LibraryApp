package org.project.libraryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class UsersUI {
    private ObservableList<StringWrapper> data;
    private Label userFeedback;
    private TableView<StringWrapper> tableView;

    public UsersUI() {
        data = FXCollections.observableArrayList();
        userFeedback = new Label();
        tableView = new TableView<>();

        // Initialize the table view
        initializeTableView();
    }

    public Scene createScene() {
        // User interface for adding users
        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Button addUserButton = new Button("Add User");
        GetUsers(data, userFeedback);
        //Add callback action to button
        addUserButton.setOnAction(actionEvent -> {
            String user = userTextField.getText();
            try {
                LibraryDatabaseManager.createUser(user);
                userFeedback.setText("User created");
                // Refresh the TableView here
                refreshTableView();
            } catch (Exception e) {
                userFeedback.setText("Error adding user.");
                e.printStackTrace();
            }
        });

        //Setting the Vertical box for the Interface
        VBox vBox = new VBox();
        vBox.getChildren().addAll(userLabel, userTextField, addUserButton, userFeedback, tableView);

        // Create a scene with the TableView
        return new Scene(vBox, 800, 600);
    }

    private void initializeTableView() {
        // Create a column for the TableView
        TableColumn<StringWrapper, String> stringColumn = new TableColumn<>("Username");
        stringColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Add the column to the TableView
        tableView.getColumns().add(stringColumn);

        // Set the data in the TableView
        tableView.setItems(data);
    }

    private void refreshTableView() {
        data.clear(); // Clear the current data
        GetUsers(data, userFeedback); // Reload the updated data
        tableView.refresh(); // Refresh the TableView
    }

    private void GetUsers(ObservableList<StringWrapper> data, Label userFeedback) {
        try {
            ObservableList<User> users = LibraryDatabaseManager.getAllUsers();
            for (User user : users) {
                data.add(new StringWrapper(user.getUsername()));
            }
        } catch (SQLException e) {
            userFeedback.setText("Error loading users.");
            e.printStackTrace();
        }
    }

    // Wrapper class for strings to use in TableView
    public static class StringWrapper {
        private final String value;

        public StringWrapper(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
