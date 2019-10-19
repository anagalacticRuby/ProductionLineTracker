// Programmer Name: Nicholas Hansen Date: 9/21/2019 File Desc: The Controller file, which houses
// methods and fields for the GUI. You can find more detailed code about them in the
// ProductionTabs.fxml
// file.

package io.github.anagalacticRuby;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the controller class which houses the methods and fields of the GUI
 *
 * <p>This class also houses code regarding the database for this program.
 *
 * <p>The main 'structure' of this code can be found in ProductionTabs.fxml
 *
 * @author Nicholas Hansen
 */
public class ProductionTabController implements Initializable {

  @FXML private TabPane tbpTabPane;

  @FXML private Tab tabProductLine;

  @FXML private Label lblProductName;

  @FXML private Label lblManufacturer;

  @FXML private Label lblItemType;

  @FXML private TextField txtProductName;

  @FXML private TextField txtManufacturer;

  @FXML private ChoiceBox<String> chbItemType;

  @FXML private Button btnAddProduct;

  @FXML private TableView<?> tblExistingProducts;

  @FXML private Label lblExistingProducts;

  @FXML private Tab tabProduce;

  @FXML private Label lblChooseProduct;

  @FXML private ListView<?> listChooseProduct;

  @FXML private Label lblChooseQuant;

  @FXML private ComboBox<String> comboChooseQuant;

  @FXML private Button btnRecordProduction;

  @FXML private Tab tabProductionLine;

  @FXML private TextArea txaProductLog;

  private Connection conn;
  private Statement stmt;

  void initializeDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./prod/PRODUCT";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    // Don't publish database password to Github
    // Modify before you push for security reasons

    // Connection is a class and conn is an instance of the class (AKA object)

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method deals with the Add Product button.
   *
   * @param event When this button is clicked, perform the following (code).
   */
  @FXML
  void addProduct(MouseEvent event) {
    try {
      // Tells the program to use the product type currently selected in the product type choicebox
      String productType = chbItemType.getValue();

      // Tells the program to use the product name currently input in the product name text box
      String productName = txtProductName.getText();

      // Tells the program to use the manufacturer's name currently input in the product
      // manufacturer
      // text box
      String productManufact = txtManufacturer.getText();

      initializeDB();
      System.out.println("Adding");

      String sqlAddProducts = "INSERT INTO Product(type, manufacturer, name) " + "VALUES ( ?,?,? )";
      PreparedStatement prepareProducts = conn.prepareStatement(sqlAddProducts);
      prepareProducts.setString(1, productType);
      prepareProducts.setString(2, productManufact);
      prepareProducts.setString(3, productName);
      prepareProducts.executeUpdate();
      String sqlViewProducts = "Select * FROM Product";
      ResultSet result = stmt.executeQuery(sqlViewProducts);
      while (result.next()) {
        String type = result.getString("type");
        String manufacturer = result.getString("manufacturer");
        String name = result.getString("name");
        System.out.println(type + " " + manufacturer + " " + name);
      }

      System.out.println("Added!");

      // STEP 4: Clean-up environment

      prepareProducts.close();
      result.close();
      stmt.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  /**
   * This method is tied to the Record Product button.
   *
   * @param event When an action occurs to this button, perform the following code.
   */
  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("RECORDED");
    int numProduced = Integer.parseInt(comboChooseQuant.getValue());
    System.out.println(numProduced);
    ProductionRecord pr = new ProductionRecord(numProduced);
    txaProductLog.insertText(0, pr.toString());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    comboChooseQuant.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    comboChooseQuant.setEditable(true);
    comboChooseQuant.getSelectionModel().selectFirst();
    chbItemType.getItems().addAll("Audio", "Visual", "AudioMobile", "VisualMobile");
    TestDriver.testMultimedia();
  }
}
