package org.project.libraryapp;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.List;

public class LibraryDatabaseManager {
    public static final String URL = "jdbc:mysql://localhost:3306/libraryappdb";
    public static final String USER = "root"; // Your MySQL username
    public static final String PASSWORD = ""; // Your MySQL password

    // Load MySQL JDBC Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    // Establish connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CRUD operations for User create
    public static void createUser(String username) throws SQLException {
        String sql = "INSERT INTO Users (username) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            System.out.println("User created.");
        }
    }

    public static void readUser(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Username: " + rs.getString("username"));
                }
            }
        }
    }

    public static void updateUser(String oldUsername, String newUsername) throws SQLException {
        String sql = "UPDATE Users SET username = ? WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newUsername);
            stmt.setString(2, oldUsername);
            stmt.executeUpdate();
            System.out.println("User updated.");
        }
    }

    public static void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            System.out.println("User deleted.");
        }
    }

    // CRUD operations for Member
    public static void createMember(int memberId, String username) throws SQLException {
        String sql = "INSERT INTO Members (memberId, username) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("Member created.");
        }
    }

    public static void readMember(int memberId) throws SQLException {
        String sql = "SELECT * FROM Members WHERE memberId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("MemberId: " + rs.getInt("memberId") + ", Username: " + rs.getString("username"));
                }
            }
        }
    }

    public static void updateMember(int memberId, String newUsername) throws SQLException {
        String sql = "UPDATE Members SET username = ? WHERE memberId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newUsername);
            stmt.setInt(2, memberId);
            stmt.executeUpdate();
            System.out.println("Member updated.");
        }
    }

    public static void deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM Members WHERE memberId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
            System.out.println("Member deleted.");
        }
    }

    // CRUD operations for Media
    public static void createMedia(String title, int year) throws SQLException {
        String sql = "INSERT INTO Media (title, year) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.executeUpdate();
            System.out.println("Media created.");
        }
    }

    public static void readMedia(int mediaId) throws SQLException {
        String sql = "SELECT * FROM Media WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("MediaId: " + rs.getInt("mediaId") + ", Title: " + rs.getString("title") + ", Year: " + rs.getInt("year"));
                }
            }
        }
    }

    public static void updateMedia(int mediaId, String newTitle, int newYear) throws SQLException {
        String sql = "UPDATE Media SET title = ?, year = ? WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setInt(2, newYear);
            stmt.setInt(3, mediaId);
            stmt.executeUpdate();
            System.out.println("Media updated.");
        }
    }

    public static void deleteMedia(int mediaId) throws SQLException {
        String sql = "DELETE FROM Media WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            stmt.executeUpdate();
            System.out.println("Media deleted.");
        }
    }

    // CRUD operations for Book
    public static void createBook(int mediaId, String author) throws SQLException {
        String sql = "INSERT INTO Books (mediaId, author) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book created.");
        }
    }

    public static void readBook(int mediaId) throws SQLException {
        String sql = "SELECT * FROM Books WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("MediaId: " + rs.getInt("mediaId") + ", Author: " + rs.getString("author"));
                }
            }
        }
    }

    public static void updateBook(int mediaId, String newAuthor) throws SQLException {
        String sql = "UPDATE Books SET author = ? WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newAuthor);
            stmt.setInt(2, mediaId);
            stmt.executeUpdate();
            System.out.println("Book updated.");
        }
    }

    public static void deleteBook(int mediaId) throws SQLException {
        String sql = "DELETE FROM Books WHERE mediaId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mediaId);
            stmt.executeUpdate();
            System.out.println("Book deleted.");
        }
    }

    // CRUD operations for Loan
    public static void createLoan(String username, int mediaId, String dueDate) throws SQLException {
        String sql = "INSERT INTO Loans (username, mediaId, dueDate) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, mediaId);
            stmt.setString(3, dueDate);
            stmt.executeUpdate();
            System.out.println("Loan created.");
        }
    }

    public static void readLoan(int loanId) throws SQLException {
        String sql = "SELECT * FROM Loans WHERE loanId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("LoanId: " + rs.getInt("loanId") + ", Username: " + rs.getString("username") + ", MediaId: " + rs.getInt("mediaId") + ", DueDate: " + rs.getDate("dueDate"));
                }
            }
        }
    }

    public static void updateLoan(int loanId, String newDueDate) throws SQLException {
        String sql = "UPDATE Loans SET dueDate = ? WHERE loanId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newDueDate);
            stmt.setInt(2, loanId);
            stmt.executeUpdate();
            System.out.println("Loan updated.");
        }
    }

    public static void deleteLoan(int loanId) throws SQLException {
        String sql = "DELETE FROM Loans WHERE loanId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            stmt.executeUpdate();
            System.out.println("Loan deleted.");
        }
    }
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Users";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                User user = new User(username);
                userList.add(user);
            }
        }
        return userList;
    }

    public static int getMediaIdByTitleAndYear(String title, int year) throws SQLException {
        String sql = "SELECT mediaId FROM Media WHERE title = ? AND year = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setInt(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("mediaId");
                } else {
                    throw new SQLException("Media not found.");
                }
            }
        }
    }
    public static ObservableList<BookWrapper> getAllBooks() throws SQLException {
        ObservableList<BookWrapper> bookList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Books b JOIN Media m ON b.mediaId = m.mediaId";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int mediaId = rs.getInt("b.mediaId");
                String title = rs.getString("m.title");
                String year = rs.getString("m.year");
                String author = rs.getString("b.author");

                // Create a BookWrapper object and add it to the list
                bookList.add(new BookWrapper(title, year, author));
            }
        }
        return bookList;
    }
}