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
import sample.procurement.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdatePurchasingController implements Initializable {
    @FXML private TableView<POViewModel> grtable;
    @FXML private TableColumn<POViewModel, String> productID;
    @FXML private TableColumn<POViewModel, String> productName;
    @FXML private TableColumn<POViewModel, String> price;
    @FXML private TableColumn<POViewModel, Integer> quantity;
    @FXML private TextField po_number;
    @FXML private TextField addID;
    @FXML private TextField addQty;
    @FXML private DatePicker datePicker;

    @FXML private Button logOut;
    @FXML private Label alert;

    public ObservableList<POViewModel> list = FXCollections.observableArrayList();
    Purchasing purchasing=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productID.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("productName"));
        price.setCellValueFactory(new PropertyValueFactory<POViewModel, String>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<POViewModel, Integer>("quantity"));

        grtable.setItems(list);
        grtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            addID.setText(newSelection.getProductID());
        });
    }

    public void search(ActionEvent actionEvent) throws IOException {


        ProcurementSystem.openPurchasing(Integer.parseInt(po_number.getText()));
        list = FXCollections.observableArrayList(mapPOItoViewModel());
        grtable.setItems(list);


    }


    public void add(ActionEvent actionEvent) throws IOException {


        ArrayList<String> findSkuNo = new ArrayList<>();
        ProcurementSystem.getPurchasing().getPurchasingItems().forEach(item -> findSkuNo.add(item.getSku().getNo()));

        if(findSkuNo.contains(addID.getText())){
            ProcurementSystem.editPurchasingItem(addID.getText(),Integer.parseInt(addQty.getText()));

        }else{
            ProcurementSystem.addPurchasingItem(addID.getText());
            ProcurementSystem.editPurchasingItem(addID.getText(),Integer.parseInt(addQty.getText()));

        }


        list = FXCollections.observableArrayList(mapPOItoViewModel());
        grtable.setItems(list);


    }

    public void submit(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.saveDeliveryDate(Integer.parseInt(po_number.getText()),datePicker.getValue());
        alert.setText("Update PO Success");
    }


    public ArrayList<POViewModel> mapPOItoViewModel(){

        Purchasing purchasing = ProcurementSystem.getPurchasing();

        ArrayList<POViewModel> pViewModels = new ArrayList<>();

        for(PurchasingItem pi : purchasing.getPurchasingItems()){
            POViewModel pViewModel = new POViewModel("0","0","0","0",0);
            pViewModel.setProductID(pi.getSku().getNo());
            pViewModel.setProductName(pi.getSku().getProduct().getName());
            pViewModel.setPrice(Double.toString(pi.getSku().getPurchasePrice()));
            pViewModel.setQuantity(pi.getQuantity());
            pViewModels.add(pViewModel);
        }


        return pViewModels;
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
