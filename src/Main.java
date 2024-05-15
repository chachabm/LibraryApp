import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // 1. Establish connection
            Connection connection = DriverManager.getConnection(LibraryDatabaseManager.URL, LibraryDatabaseManager.USER, LibraryDatabaseManager.PASSWORD);
            System.out.println("Connection established.");

            // 2. Insert user
            String insertUserSQL = "INSERT INTO Users (username) VALUES (?)";
            try (PreparedStatement userStmt = connection.prepareStatement(insertUserSQL)) {
                userStmt.setString(1, "john_doe");
                userStmt.executeUpdate();
                System.out.println("User inserted.");
            }

            // 3. Insert member
            String insertMemberSQL = "INSERT INTO Members (memberId, username) VALUES (?, ?)";
            try (PreparedStatement memberStmt = connection.prepareStatement(insertMemberSQL)) {
                memberStmt.setInt(1, 1);
                memberStmt.setString(2, "john_doe");
                memberStmt.executeUpdate();
                System.out.println("Member inserted.");
            }

            // 4. Insert media
            String insertMediaSQL = "INSERT INTO Media (title, year) VALUES (?, ?)";
            try (PreparedStatement mediaStmt = connection.prepareStatement(insertMediaSQL)) {
                mediaStmt.setString(1, "The Great Gatsby");
                mediaStmt.setInt(2, 1925);
                mediaStmt.executeUpdate();
                System.out.println("Media inserted.");
            }

            // 5. Insert book
            String insertBookSQL = "INSERT INTO Books (mediaId, author) VALUES (LAST_INSERT_ID(), ?)";
            try (PreparedStatement bookStmt = connection.prepareStatement(insertBookSQL)) {
                bookStmt.setString(1, "F. Scott Fitzgerald");
                bookStmt.executeUpdate();
                System.out.println("Book inserted.");
            }

            // 6. Insert loan
            String insertLoanSQL = "INSERT INTO Loans (username, mediaId, dueDate) VALUES (?, LAST_INSERT_ID(), ?)";
            try (PreparedStatement loanStmt = connection.prepareStatement(insertLoanSQL)) {
                loanStmt.setString(1, "john_doe");
                loanStmt.setDate(2, java.sql.Date.valueOf("2023-12-31"));
                loanStmt.executeUpdate();
                System.out.println("Loan inserted.");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}