// Programmer Name: Nicholas Hansen
// Date: 11/23/2019
// File Desc: The Controller file, which houses
// methods and fields for the GUI. You can find more detailed code about them in the
// ProductionTabs.fxml
// file.

package io.github.anagalacticruby;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
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

  @FXML private Label lblEmployError;

  @FXML private TextField txtEmployName;

  @FXML private PasswordField passEmployee;

  @FXML private TextArea txaEmployeeDetails;

  @FXML private Button generateButton;

  @FXML private RadioButton starBox;

  @FXML private RadioButton retroBox;

  @FXML private RadioButton noBox;

  private Connection conn;
  private Statement stmt;

  /**
   * This method reverses the order of the string used for the database password.
   *
   * @param pw the password to reverse
   * @return the reverse password
   */
  private String reverseString(String pw) {
    if (pw.isEmpty()) {
      return pw;
    }
    return reverseString(pw.substring(1)) + pw.charAt(0);
  }

  /**
   * A method that is merely used to set up the connection to the JDBC database.
   *
   * <p>This is where all of the products that have been created are stored.
   */
  private void initializeDB() {
    try {
      final String JDBC_DRIVER = "org.h2.Driver";
      final String DB_URL = "jdbc:h2:./prod/PRODUCT";

      Properties prop = new Properties();
      prop.load(new FileInputStream("prod/properties"));

      //  Database credentials
      final String USER = "";

      final String PASS = prop.getProperty("password");
      final String encryptPass = reverseString(prop.getProperty("password"));
      lblEmployError.setText(encryptPass);
      lblEmployError.setText("V Your Details V");
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
    } catch (Exception g) {
      g.printStackTrace();
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

    } catch (Exception e) {
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

    // These reset the styles of the text fields and progress label
    lblProgress.setStyle("");
    txtManufacturer.setStyle("");
    txtProductName.setStyle("");

    // These variables are declared to make the if-else chains easier to read
    String productNameField = txtProductName.getText();
    String productManufactField = txtManufacturer.getText();
    /*The regular expression checks for any words that do not include non-word characters.
    This means that it will not accept characters such as ; or * */
    if (productNameField.matches("\\w+[^\\W]")
        && productManufactField.matches("\\w+[^\\W]")
        && chbItemType.getSelectionModel().isEmpty()) {
      productionProgress.setProgress(.66);
      lblProgress.setText("2/3 There...");
      btnAddProduct.setDisable(true);
    } else if ((productNameField.matches("\\w+[^\\W]")
            || productManufactField.matches("\\w+[^\\W]"))
        && chbItemType.getSelectionModel().isEmpty()) {
      productionProgress.setProgress(.33);
      lblProgress.setText("1/3 There...");
      btnAddProduct.setDisable(true);
    } else if ((productNameField.matches("\\w+[^\\W]")
            && productManufactField.matches("\\w+[^\\W]"))
        && !chbItemType.getSelectionModel().isEmpty()) {
      productionProgress.setProgress(1);
      lblProgress.setText("Ready!");
      btnAddProduct.setDisable(false);
    } else if ((productManufactField.matches("\\w+[^\\W]")
            || productNameField.matches("\\w+[^\\W]"))
        && !chbItemType.getSelectionModel().isEmpty()) {
      productionProgress.setProgress(.66);
      lblProgress.setText("2/3 There...");
      btnAddProduct.setDisable(true);
    }
    if ((productManufactField.matches("\\w+[\\W]") || productNameField.matches("\\w+[\\W]"))) {
      lblProgress.setStyle("-fx-text-fill: coral");
      if (productNameField.matches("\\w+[\\W]")) {
        txtProductName.setStyle("-fx-background-color: maroon");
      }
      if (productManufactField.matches("\\w+[\\W]")) {
        txtManufacturer.setStyle("-fx-background-color: maroon");
      }
      productionProgress.setProgress(0);
      lblProgress.setText("Invalid Characters");
      btnAddProduct.setDisable(true);
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
    // This resets the style of the text fields and progress label
    lblProgress.setStyle("");
    txtManufacturer.setStyle("");
    txtProductName.setStyle("");

    // These variables are declared to make things easier to read
    String productNameField = txtProductName.getText();
    String productManufactField = txtManufacturer.getText();
    if (productNameField.matches("\\w+[^\\W]") && productManufactField.matches("\\w+[^\\W]")) {
      // If the product type has been selected and both text fields are valid the following occurs
      productionProgress.setProgress(1);
      lblProgress.setText("Ready!");
      // Enables the user to add a product
      btnAddProduct.setDisable(false);
    } else if (productNameField.matches("\\w+[^\\W]")
        || productManufactField.matches("\\w+[^\\W]")) {
      // If the product type has been selected and one of the text fields is filled the following
      // occurs
      productionProgress.setProgress(.66);
      lblProgress.setText("2/3 There!");
      btnAddProduct.setDisable(true);
    } else if ((productNameField.matches("\\w+[^\\W]")
            && productManufactField.matches("\\w+[^\\W]"))
        && !chbItemType.getSelectionModel().isEmpty()) {
      lblProgress.setText("Ready!");
      productionProgress.setProgress(1);
      btnAddProduct.setDisable(false);
    } else {
      // If the product type has been specified but none of the fields have been filled the
      // following occurs
      productionProgress.setProgress(.33);
      lblProgress.setText("1/3 There...");
      btnAddProduct.setDisable(true);
    }
    if ((productManufactField.matches("\\w+[\\W]") || productNameField.matches("\\w+[\\W]"))) {
      lblProgress.setStyle("-fx-text-fill: derive(coral, -20%)");
      if (productNameField.matches("\\w+[\\W]")) {
        txtProductName.setStyle("-fx-background-color: maroon");
      }
      if (productManufactField.matches("\\w+[\\W]")) {
        txtManufacturer.setStyle("-fx-background-color: maroon");
      }
      productionProgress.setProgress(0);
      lblProgress.setText("Invalid Characters");
      btnAddProduct.setDisable(true);
    }
  }

  /**
   * This method allows you to change the themes of the program. c:
   *
   * @param event this event cares about the checkboxes on the Settings tab.
   */
  @FXML
  void changeTheme(ActionEvent event) {
    if (starBox.isSelected()) {
      txtManufacturer.getScene().getStylesheets().add("io/github/anagalacticruby/StarStyle.css");
      txtManufacturer
          .getScene()
          .getStylesheets()
          .remove("io/github/anagalacticruby/ProductionStyleSheet.css");
      txtManufacturer
          .getScene()
          .getStylesheets()
          .remove("io/github/anagalacticruby/RetroTheme.css");
    } else if (retroBox.isSelected()) {
      txaEmployeeDetails
          .getScene()
          .getStylesheets()
          .remove("io/github/anagalacticruby/StarStyle.css");
      txaEmployeeDetails
          .getScene()
          .getStylesheets()
          .remove("io/github/anagalacticruby/ProductionStyleSheet.css");
      txaEmployeeDetails
          .getScene()
          .getStylesheets()
          .add("io/github/anagalacticruby/RetroTheme.css");
    } else if (noBox.isSelected()) {
      txaProductLog.getScene().getStylesheets().remove("io/github/anagalacticruby/RetroTheme.css");
      txaProductLog.getScene().getStylesheets().remove("io/github/anagalacticruby/StarStyle.css");
      txaProductLog
          .getScene()
          .getStylesheets()
          .add("io/github/anagalacticruby/ProductionStyleSheet.css");
    } else {
      txtManufacturer.getScene().getStylesheets().remove("io/github/anagalacticruby/StarStyle.css");
      txtManufacturer
          .getScene()
          .getStylesheets()
          .remove("io/github/anagalacticruby/RetroTheme.css");
      txtManufacturer
          .getScene()
          .getStylesheets()
          .add("io/github/anagalacticruby/ProductionStyleSheet.css");
    }
  }

  /**
   * This event generates the employee details for an employee.
   *
   * @param event Takes in the password and full name input by the employee in their respective
   *     fields.
   */
  @FXML
  void generateDetails(MouseEvent event) {
    if (txtEmployName.getText().matches("\\w+[^\\W]")
        && passEmployee.getText().matches("\\w+[^\\W]")) {
      String employeeName = txtEmployName.getText();
      String employeePass = passEmployee.getText();
      Employee newEmployee = new Employee(employeeName, employeePass);
      txaEmployeeDetails.setText(newEmployee.toString());
    } else {
      lblEmployError.setText("Invalid Characters!");
      txtEmployName.setStyle("-fx-base:red");
      passEmployee.setStyle("-fx-base:red");
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
    if (listChooseProduct.getSelectionModel().isEmpty()) {
      lblRecording.setText("A product must be selected.");
      lblRecording.setStyle(
          "-fx-text-fill: red; -fx-font-weight: bold;"
              + "-fx-effect: dropshadow(gaussian, ghostwhite, 2, 1, 0, 0);");
    } else if (comboChooseQuant.getSelectionModel().getSelectedItem().matches("\\d+")) {
      System.out.println("RECORDED");
      int numProduced = Integer.parseInt(comboChooseQuant.getValue());
      System.out.println(numProduced); /*
    ProductionRecord pr = new ProductionRecord(numProduced);
    txaProductLog.insertText(0, pr.toString() + "\n");*/
      Product selectedProduct = listChooseProduct.getSelectionModel().getSelectedItem();
      for (int productionRunProduct = 0;
          productionRunProduct < numProduced;
          productionRunProduct++) {
        ProductionRecord productionRecord = new ProductionRecord(selectedProduct, itemCount++);
        txaProductLog.appendText(productionRecord.toString() + "\n");
        ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      }
      lblRecording.setStyle("-fx-text-fill: white; -fx-font-weight: normal");
      lblRecording.setText("Check the Production Log tab!");
    } else {
      lblRecording.setText("Invalid Characters! >:(");
      lblRecording.setStyle(
          "-fx-text-fill: red; -fx-font-weight: bold;"
              + "-fx-effect: dropshadow(gaussian, azure, 2,1,0,0);");
    }
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
    chbItemType.getSelectionModel().selectFirst();
    TestDriver.testMultimedia();
    // The following line of code adds a "Sample" row to the table to verify it is functional.
    Product sample = new Widget("Sample", "Oracle", ItemType.VISUAL);
    productLine.add(sample);

    setupProductLineTable();
    try {
      loadProductList();

    } catch (SQLException | NullPointerException e) {
      e.printStackTrace();
    }
    listChooseProduct.getItems().addAll(productLine);

    if (!listChooseProduct.getItems().isEmpty()) {
      btnRecordProduction.setDisable(false);
      lblRecording.setText("Select a product to record.");
    }
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

  private void loadProductList() throws SQLException {
    initializeDB();
    String sql = "Select * FROM Product";

    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      int id = rs.getInt(1);
      String name = rs.getString("name");
      String type = rs.getString("type");
      String manufacturer = rs.getString("manufacturer");
      //

      ItemType dbType;
      switch (type) {
        case "VI":
          dbType = ItemType.VISUAL;
          break;
        case "AM":
          dbType = ItemType.AUDIO_MOBILE;
          break;
        case "VM":
          dbType = ItemType.VISUAL_MOBILE;
          break;
        default:
          dbType = ItemType.AUDIO;
      }

      Product productFromDB = new Widget(id, name, manufacturer, dbType);

      productLine.add(productFromDB);
      // listChooseProduct.getItems().add(productFromDB);
    }
    conn.close();
    stmt.close();
  }
}
