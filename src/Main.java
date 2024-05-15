import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       try{
           Connection connection = DriverManager.getConnection(LibraryDatabaseManager.URL, LibraryDatabaseManager.USER, LibraryDatabaseManager.PASSWORD);
           System.out.println("Connection established.");
           //Execution
           //LibraryDatabaseManager.UnitTest();











       }catch(Exception e)
       {
           System.out.println(e);
       }
    }
}