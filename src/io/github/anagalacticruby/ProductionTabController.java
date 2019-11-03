// Programmer Name: Nicholas Hansen
// Date: 11/2/2019
// File Desc: The Controller file, which houses
// methods and fields for the GUI. You can find more detailed code about them in the
// ProductionTabs.fxml
// file.

package io.github.anagalacticruby;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
  private final ObservableList<Product> productLine = FXCollections.observableArrayList();

  private int itemCount = 0;

  @FXML private TextField txtProductName;

  @FXML private TextField txtManufacturer;

  @FXML private ChoiceBox<ItemType> chbItemType;

  @FXML private Button btnAddProduct;

  @FXML private TableView<Product> tblExistingProducts;

  @FXML private TableColumn<?, ?> prodNameCol;

  @FXML private TableColumn<?, ?> manufactCol;

  @FXML private TableColumn<?, ?> itemTypeCol;

  @FXML private ProgressBar productionProgress;

  @FXML private ListView<Product> listChooseProduct;

  @FXML private ComboBox<String> comboChooseQuant;

  @FXML private Button btnRecordProduction;

  @FXML private TextArea txaProductLog;

  @FXML private Label lblProgress;

  @FXML private Label lblRecording;

  private Connection conn;
  private Statement stmt;

  /**
   * A method that is merely used to set up the connection to the JDBC database.
   *
   * <p>This is where all of the products that have been created are stored.
   */
  private void initializeDB() {
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
   * <p>First it will get the information that a user has input in the text fields on the Product
   * Line tab, and insert them into the database of created products. Then, it will print the
   * currently stored products in the database. Afterwards it will update the table and
   * ObservableList that are meant to store the information a user input. Afterwards it will clear
   * all the text fields, and disable the button itself. This is to prevent massive spams to the
   * database, as well as allow the user to process what has been completed.(This method also
   * removes the sample values of the table the first time this method is run)
   *
   * @param event An event listener that in this case, is listening for the user to click on the
   *     button
   */
  @FXML
  void addProduct(MouseEvent event) {
    try {
      // Tells the program to use the product type currently selected in the product type choice box
      ItemType productType = chbItemType.getValue();

      // This variable converts the Product Type selected in the choice box into a string.
      // This is necessary for the database, as you cannot set enum types with statements.
      String productStringType = productType.getCode();

      // Tells the program to use the product name currently input in the product name text box
      final String productName = txtProductName.getText();

      // Tells the program to use the manufacturer's name currently input in the product
      // manufacturer text box
      final String productManufact = txtManufacturer.getText();

      initializeDB();
      System.out.println("Adding");

      String sqlAddProducts = "INSERT INTO Product(type, manufacturer, name) " + "VALUES ( ?,?,? )";
      PreparedStatement prepareProducts = conn.prepareStatement(sqlAddProducts);
      prepareProducts.setString(1, productStringType);
      prepareProducts.setString(2, productManufact);
      prepareProducts.setString(3, productName);
      prepareProducts.executeUpdate();
      String sqlViewProducts = "Select * FROM Product";
      ResultSet result = stmt.executeQuery(sqlViewProducts);
      while (result.next()) {
        String name = result.getString("name");
        String manufacturer = result.getString("manufacturer");
        String type = result.getString("type");
        System.out.println(name + " " + manufacturer + " " + type);
      }

      System.out.println("Added!");

      /*
       *These two lines remove the "Sample" value in the table the first time
       *you press the "Add Product" button.
       */
      Predicate<Product> condition = productLine -> productLine.getName().contains("Sample");
      productLine.removeIf(condition);

      Product newProduct = new Widget(productName, productManufact, productType);
      // Takes the info input by a user and puts it into the productLine observable list.
      // Afterwards the table is updated with the new values displaying.
      productLine.add(newProduct);
      // This updates the list view with the newly added product.
      listChooseProduct.getItems().add(newProduct);
      // This allows users to record production now that there are products to record.
      btnRecordProduction.setDisable(false);
      // STEP 4: Clean-up environment

      // Clearing the text fields and item type choice box.
      txtManufacturer.clear();
      txtProductName.clear();
      chbItemType.setValue(null);
      // Disables the button to prevent a user from inserting massive amounts of products into the
      // database at once.
      btnAddProduct.setDisable(true);
      // Resets the progress bar back to 0
      productionProgress.setProgress(0);
      lblProgress.setText("Check the Produce tab!");

      lblRecording.setText("Select a product to record.");

      prepareProducts.close();
      result.close();
      stmt.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method adds progress to the bar to show to the user how much progress on adding a new
   * product they have made.
   *
   * <p>The user can only add a product if all text fields and the type are filled.
   *
   * @param event This event handler checks for when keys are typed in the text fields
   */
  @FXML
  void addProgress(KeyEvent event) {

    if (txtProductName.getText().isEmpty()
        && txtManufacturer.getText().isEmpty()
        && !chbItemType.getSelectionModel().isEmpty()) {
      // If both text fields are empty and the product type has been selected this happens
      productionProgress.setProgress(.33);
      lblProgress.setText("1/3 There...");
    } else if (!txtProductName.getText().isEmpty()
            && txtManufacturer.getText().isEmpty()
            && !chbItemType.getSelectionModel().isEmpty()
        || !txtManufacturer.getText().isEmpty()
            && txtProductName.getText().isEmpty()
            && !chbItemType.getSelectionModel().isEmpty()) {
      // If one of the fields is empty and the product Type isn't selected, the following occurs
      productionProgress.setProgress(.66);
      btnAddProduct.setDisable(true);
      lblProgress.setText("2/3 There!");
    } else if (!txtProductName.getText().isEmpty() && txtManufacturer.getText().isEmpty()
        || !txtManufacturer.getText().isEmpty() && txtProductName.getText().isEmpty()) {
      // If one of the text fields is empty and the other isn't, the following occurs
      productionProgress.setProgress(.33);
      btnAddProduct.setDisable(true);
      lblProgress.setText("1/3 There...");
    } else if (!txtManufacturer.getText().isEmpty()
        && !txtProductName.getText().isEmpty()
        && chbItemType.getSelectionModel().isEmpty()) {
      // If both text fields are filled but there isn't a product type selected the following occurs
      productionProgress.setProgress(.66);
      btnAddProduct.setDisable(true);
      lblProgress.setText("2/3 There!");
    } else if (!txtManufacturer.getText().isEmpty()
        && !txtProductName.getText().isEmpty()
        && !chbItemType.getSelectionModel().isEmpty()) {
      // If there is a product name, manufacturer and type selected, the following occurs
      productionProgress.setProgress(1);
      lblProgress.setText("Complete!");
      btnAddProduct.setDisable(false);
    } else {
      // If NO fields AND the product type has not been selected, the following occurs
      productionProgress.setProgress(0);
      lblProgress.setText("Please Input More text.");
    }
  }

  /**
   * This method adds progress to the bar to show to the user how much progress on adding a new
   * product they have made.
   *
   * <p>The user can only add a product if all text fields and the type are filled. The way this
   * method differs from the previous is that it is checking the choice box.
   *
   * @param event This event handler checks when the user clicks on the product type choice box
   */
  @FXML
  void checkProgress(MouseEvent event) {

    if (!txtManufacturer.getText().isEmpty() && !txtProductName.getText().isEmpty()) {
      // If the product type has been selected and both text fields are filled the following occurs
      productionProgress.setProgress(1);
      lblProgress.setText("Complete!");
      // Enables the user to add a product
      btnAddProduct.setDisable(false);
    } else if (!txtProductName.getText().isEmpty() || !txtManufacturer.getText().isEmpty()) {
      // If the product type has been selected and one of the text fields is filled the following
      // occurs
      productionProgress.setProgress(.66);
      lblProgress.setText("2/3 There!");
    } else {
      // If the product type has been specified but none of the fields have been filled the
      // following occurs
      productionProgress.setProgress(.33);
      lblProgress.setText("1/3 There...");
    }
  }

  /**
   * This method is tied to the Record Product button.
   *
   * <p>It allow users to select a product that has been created in the Product Line tab, and the
   * amount of this product to record. After the amount and product to record production of have
   * been selected, this method will insert the production record of these products in the
   * Production Log tab's text area.
   *
   * @param event When an action occurs to this button, perform the following code.
   */
  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("RECORDED");
    int numProduced = Integer.parseInt(comboChooseQuant.getValue());
    System.out.println(numProduced); /*
    ProductionRecord pr = new ProductionRecord(numProduced);
    txaProductLog.insertText(0, pr.toString() + "\n");*/
    Product selectedProduct = listChooseProduct.getSelectionModel().getSelectedItem();
    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord productionRecord = new ProductionRecord(selectedProduct, itemCount++);
      txaProductLog.appendText(productionRecord.toString() + "\n");
    }
    lblRecording.setText("Check the Production Log tab!");
  }

  /**
   * This method sets up the program's environment the moment the program starts running.
   *
   * <p>This method will be run every time the program is run, and does the following calls and
   * setup. Most of this method's functionality lies in what items need to be prepared before the
   * user gets to them or so that elements are properly displaying the right details.
   *
   * @param location location
   * @param resources resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    comboChooseQuant.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    comboChooseQuant.setEditable(true);
    comboChooseQuant.getSelectionModel().selectFirst();
    chbItemType.getItems().addAll(ItemType.values());
    TestDriver.testMultimedia();
    // The following line of code adds a "Sample" row to the table to verify it is functional.
    Product sample = new Widget("Sample", "Oracle", ItemType.VISUAL);
    productLine.add(sample);

    setupProductLineTable();
  }

  /**
   * This method sets up the columns of the Product Line table.
   *
   * <p>It also tells the table to read from the productLine ObservableArrayList.
   */
  private void setupProductLineTable() {
    // set up first column of the Product Line TableView to read product Names
    prodNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
    // Set up second column of the Product Line TableView to read product Manufacturers
    manufactCol.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    // Set up third column of the Product Line TableView to read product Types
    itemTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
    // Set the TableView to the productLine ObservableList
    tblExistingProducts.setItems(productLine);
  }
}
