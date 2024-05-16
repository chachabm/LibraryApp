package org.project.libraryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class BooksUI {
    private Library library;
    private ObservableList<BookWrapper> data;
    private TableView<BookWrapper> tableView;
    private TextField titleField;
    private TextField yearField;
    private TextField authorField;
    private Label feedbackLabel;

    public BooksUI(Library library) {
        this.library = library;
        data = FXCollections.observableArrayList();
        tableView = new TableView<>();
        titleField = new TextField();
        yearField = new TextField();
        authorField = new TextField();
        feedbackLabel = new Label();

        // Load initial data from the database
        refreshTable();
    }

    public Scene createScene() {
        // Table setup
        TableColumn<BookWrapper, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<BookWrapper, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<BookWrapper, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        tableView.getColumns().addAll(titleColumn, yearColumn, authorColumn);
        tableView.setItems(data);

        // Form setup
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("Year:"), 0, 1);
        form.add(yearField, 1, 1);
        form.add(new Label("Author:"), 0, 2);
        form.add(authorField, 1, 2);

        // Buttons setup
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addBook());
        updateButton.setOnAction(e -> updateBook());
        deleteButton.setOnAction(e -> deleteBook());

        HBox buttons = new HBox(10, addButton, updateButton, deleteButton);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, tableView, form, buttons, feedbackLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 600, 400);
    }

    private void addBook() {
        try {
            String title = titleField.getText();
            int year = Integer.parseInt(yearField.getText());
            String author = authorField.getText();
            Book book = new Book(title, year, author);

            // Add to library and database
            library.addMedia(book);
            LibraryDatabaseManager.createMedia(title, year);
            int mediaId = LibraryDatabaseManager.getMediaIdByTitleAndYear(title, year); // Get media ID for the created media
            LibraryDatabaseManager.createBook(mediaId, author);

            data.add(new BookWrapper(book));
            clearForm();
            feedbackLabel.setText("Book added successfully.");
        } catch (NumberFormatException | SQLException e) {
            feedbackLabel.setText("Error adding book: " + e.getMessage());
        }
    }

    private void updateBook() {
        BookWrapper selectedBookWrapper = tableView.getSelectionModel().getSelectedItem();
        if (selectedBookWrapper != null) {
            try {
                String title = titleField.getText();
                int year = Integer.parseInt(yearField.getText());
                String author = authorField.getText();

                // Find original book in the library
                Book originalBook = library.getMediaList().stream()
                        .filter(media -> media instanceof Book)
                        .map(media -> (Book) media)
                        .filter(book -> book.getTitle().equals(selectedBookWrapper.getTitle()) &&
                                book.getYear() == Integer.parseInt(selectedBookWrapper.getYear()) &&
                                book.getAuthor().equals(selectedBookWrapper.getAuthor()))
                        .findFirst().orElse(null);

                if (originalBook != null) {
                    library.removeMedia(originalBook);

                    int mediaId = LibraryDatabaseManager.getMediaIdByTitleAndYear(originalBook.getTitle(), originalBook.getYear());
                    LibraryDatabaseManager.updateMedia(mediaId, title, year);
                    LibraryDatabaseManager.updateBook(mediaId, author);

                    Book updatedBook = new Book(title, year, author);
                    library.addMedia(updatedBook);

                    refreshTable();
                    clearForm();
                    feedbackLabel.setText("Book updated successfully.");
                } else {
                    feedbackLabel.setText("Original book not found in the library.");
                }
            } catch (NumberFormatException | SQLException e) {
                feedbackLabel.setText("Error updating book: " + e.getMessage());
            }
        } else {
            feedbackLabel.setText("No book selected.");
        }
    }

    private void deleteBook() {
        BookWrapper selectedBookWrapper = tableView.getSelectionModel().getSelectedItem();
        if (selectedBookWrapper != null) {
            try {
                // Find original book in the library
                Book bookToDelete = library.getMediaList().stream()
                        .filter(media -> media instanceof Book)
                        .map(media -> (Book) media)
                        .filter(book -> book.getTitle().equals(selectedBookWrapper.getTitle()) &&
                                book.getYear() == Integer.parseInt(selectedBookWrapper.getYear()) &&
                                book.getAuthor().equals(selectedBookWrapper.getAuthor()))
                        .findFirst().orElse(null);

                if (bookToDelete != null) {
                    int mediaId = LibraryDatabaseManager.getMediaIdByTitleAndYear(bookToDelete.getTitle(), bookToDelete.getYear());
                    LibraryDatabaseManager.deleteBook(mediaId);
                    LibraryDatabaseManager.deleteMedia(mediaId);

                    library.removeMedia(bookToDelete);
                    data.remove(selectedBookWrapper);

                    clearForm();
                    feedbackLabel.setText("Book deleted successfully.");
                } else {
                    feedbackLabel.setText("Book not found in the library.");
                }
            } catch (SQLException e) {
                feedbackLabel.setText("Error deleting book: " + e.getMessage());
            }
        } else {
            feedbackLabel.setText("No book selected.");
        }
    }

    private void clearForm() {
        titleField.clear();
        yearField.clear();
        authorField.clear();
    }

    private void refreshTable() {
        data.clear();
        try {
            data.addAll(LibraryDatabaseManager.getAllBooks());
        } catch (SQLException e) {
            feedbackLabel.setText("Error loading books: " + e.getMessage());
        }
        tableView.refresh();
    }
}
