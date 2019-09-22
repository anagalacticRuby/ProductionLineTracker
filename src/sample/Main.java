// Programmer Name: Nicholas Hansen
// Date: 9/21/2019
// File Desc: This is the main file of the ProductionLineTracker program.
// It houses the main method, as well as some code to access the
// PRODUCT database.

package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main file of the ProductionLineTracker program.
 *
 * <p>It contains the main method and code to access the PRODUCT database.
 *
 * @author Nicholas Hansen
 */
public class Main extends Application {

  /**
   * The starting point of a JavaFX program.
   *
   * @param primaryStage the first thing the user sees when the program is run
   * @throws Exception exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("WOW man WELCOME to the PRODUCTION LINE TRACKER PROGRAM");
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
    // ctrl+alt+L is the google java formatter
    System.out.println("IM SUPER RAD");
  }

  /**
   * This is the main method, where things happen.
   *
   * @param args default parameters for a main method.
   */
  public static void main(String[] args) {
    launch(args);
    // make sure IntelliJ isn't connected to database when running this program
    // click the red square before running program if it's light up.
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./prod/PRODUCT";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    // Don't publish database password to Github
    // Modify before you push for security reasons
    Connection conn;
    // Connection is a class and conn is an instance of the class (AKA object)
    Statement stmt;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();

      String sqlSelectAllProducts = "SELECT * FROM Product";
      ResultSet rs = stmt.executeQuery(sqlSelectAllProducts);
      // STEP 4: Clean-up environment

      rs.close();
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
