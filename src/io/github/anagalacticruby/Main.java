// Programmer Name: Nicholas Hansen
// Date: 11/2/2019
// File Desc: This is the main file of the ProductionLineTracker program.
// It houses the main method, as well as some code to access the PRODUCT database.

package io.github.anagalacticruby;

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
   * <p>Essentially, where all the magic begins.
   *
   * @param primaryStage The first thing the user sees when the program is run
   * @throws Exception Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductionTabs.fxml"));
    primaryStage.setTitle("Welcome to the Production Tracker v2!");
    Scene scene = new Scene(root, 600, 450);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    scene.getStylesheets().add(getClass().getResource("ProductionStyleSheet.css").toExternalForm());
    primaryStage.show();
    // ctrl+alt+L is the google java formatter
    System.out.println("Bottom Text");
  }

  /**
   * This is the main method, where things happen.
   *
   * @param args Default parameters for a main method.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
