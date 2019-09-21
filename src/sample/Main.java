package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Yeah bro this is the main file of the production line program.
 *
 * @author Nicholas H
 */
public class Main extends Application {

  /**
   * The starting point of a JavaFX program.
   *
   * @param primaryStage the first thing the user sees
   * @throws Exception baba
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("WOW man WELCOME to the PRODUCTION LINE TRACKER PROGRAM");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
    // ctrl+alt+L is the google java formatter
    System.out.println("IM SUPER RAD");
  }

  public static void main(String[] args) {
    launch(args);
  }
}
