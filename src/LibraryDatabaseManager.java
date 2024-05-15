import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryDatabaseManager {
    public static final String URL = "jdbc:mysql://localhost:3306/libraryappdb"; // Replace 'library' with your database name
    public static final String USER = "root"; // Replace with your MySQL username
    public static final String PASSWORD = ""; // Replace with your MySQL password
}