package sample.UI.Purchasing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.ViewModel.GrViewModel;
import sample.ViewModel.POViewModel;
import sample.procurement.ProcurementSystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class CreatePurchasingController implements Initializable {

    @FXML private TableView<POViewModel> potable;
    @FXML private TableColumn<POViewModel, String> vendor;
    @FXML private TableColumn<POViewModel, String> productID;
    @FXML private TableColumn<POViewModel, String> productName;
    @FXML private TableColumn<POViewModel, String> price;
    @FXML private TableColumn<POViewModel, Integer> quantity;

    @FXML private ComboBox vendorComboBox;
    @FXML private TextField productIDText;
    @FXML private TextField qtyText;

    @FXML private Button logOut;
    @FXML private Label alert;

    public ObservableList<POViewModel> list = FXCollections.observableArrayList();
    public POViewModel poViewModel = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vendor.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("vendor"));
        productID.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("productName"));
        price.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<POViewModel, Integer>("quantity"));


        ObservableList<String> vendorlist = FXCollections.observableArrayList("CONVERSE CO. LTD");
        vendorComboBox.setItems(vendorlist);

        potable.setItems(list);
        ProcurementSystem.makePurchasing();

    }

    public void add(ActionEvent actionEvent) throws IOException {


        ProcurementSystem.chooseVendor((String) vendorComboBox.getValue());
        ProcurementSystem.addPurchasingItem(productIDText.getText());
        ProcurementSystem.editPurchasingItem(productIDText.getText(), Integer.parseInt(qtyText.getText()));

        poViewModel = new POViewModel((String) vendorComboBox.getValue(),productIDText.getText(),ProcurementSystem.findSku(productIDText.getText()).getProduct().getName(),Double.toString(ProcurementSystem.findSku(productIDText.getText()).getPurchasePrice()),Integer.parseInt(qtyText.getText()));
        list.add(poViewModel);
        potable.setItems(list);

        productIDText.clear();
        qtyText.clear();

    }

    public void create(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.makePurchaseOrder();
        alert.setText("Create PO success.");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("log in");
        primaryStage.show();


    }

}
