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

  @FXML
  void addProduct(MouseEvent event) {
    System.out.println("ADDED");
  }

  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("RECORDED");
  }
}
