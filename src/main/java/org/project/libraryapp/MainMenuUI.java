package org.project.libraryapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuUI {
    private Scene scene;
    private Library library;
    private Stage PrimaryStage;

    public MainMenuUI(Library library, Stage primaryStage) {
        this.library = library;
        this.PrimaryStage = primaryStage;
        setupUI();
    }

    private void setupUI() {
        // Create buttons for each UI
        Button usersButton = new Button("Manage Users");
        Button booksButton = new Button("Manage Books");

        // Set button actions
        usersButton.setOnAction(e -> openUsersUI());
        booksButton.setOnAction(e -> openBooksUI());

        // Setup layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(usersButton, booksButton);

        // Create scene
        scene = new Scene(layout, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }

    private void openUsersUI() {
        UsersUI usersUI = new UsersUI();
        Scene usersScene = usersUI.createScene();
        this.PrimaryStage.setScene(usersScene);
    }

    private void openBooksUI() {
        BooksUI booksUI = new BooksUI(library);
        Scene booksScene = booksUI.createScene();
        this.PrimaryStage.setScene(booksScene);
    }
}