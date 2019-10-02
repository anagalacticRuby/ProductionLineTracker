// Programmer Name: Nicholas Hansen
// Date: 9/21/2019
// File Desc: This is the main file of the ProductionLineTracker program.
// It houses the main method, as well as some code to access the PRODUCT database.

package sample;


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
  }
}
