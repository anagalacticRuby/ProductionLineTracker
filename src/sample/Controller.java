// Programmer Name: Nicholas Hansen Date: 9/21/2019 File Desc: The Controller file, which houses
// methods and fields for the GUI. You can find more detailed code about them in the sample.fxml
// file.

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/**
 * This is the controller class which houses the methods and fields of the GUI
 *
 * <p>The main 'structure' of this code can be found in sample.fxml
 *
 * @author Nicholas Hansen
 */
public class Controller {

  @FXML private TabPane tbpTabPane;

  @FXML private Tab tabProductLine;

  @FXML private Label lblProductName;

  @FXML private Label lblManufacturer;

  @FXML private Label lblItemType;

  @FXML private TextField txtProductName;

  @FXML private TextField txtManufacturer;

  @FXML private ChoiceBox<?> chbItemType;

  @FXML private Button btnAddProduct;

  @FXML private TableView<?> tblExistingProducts;

  @FXML private Label lblExistingProducts;

  @FXML private Tab tabProduce;

  @FXML private Label lblChooseProduct;

  @FXML private ListView<?> listChooseProduct;

  @FXML private Label lblChooseQuant;

  @FXML private ComboBox<?> comboChooseQuant;

  @FXML private Button btnRecordProduction;

  @FXML private Tab tabProductionLine;

  @FXML private TextArea txaProductLog;

  /**
   * This method deals with the Add Product button.
   *
   * @param event When this button is clicked, perform the following (code).
   */
  @FXML
  void addProduct(MouseEvent event) {
    System.out.println("ADDED");
  }

  /**
   * This method is tied to the Record Product button.
   *
   * @param event When an action occurs to this button, perform the following code.
   */
  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("RECORDED");
  }
}
