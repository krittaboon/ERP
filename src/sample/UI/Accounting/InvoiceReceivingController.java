package sample.UI.Accounting;

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
import sample.ViewModel.IRViewModel;
import sample.procurement.GoodsReceiving;
import sample.procurement.GoodsReceivingItem;
import sample.procurement.ProcurementSystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvoiceReceivingController implements Initializable {
    @FXML private TableView<IRViewModel> irtable;
    @FXML private TableColumn<IRViewModel, String> productID;
    @FXML private TableColumn<IRViewModel, String> productName;
    @FXML private TableColumn<IRViewModel, Integer> quantity;
    @FXML private TableColumn<IRViewModel, String> price;
    @FXML private TextField po_number;
    @FXML private TextField addID;
    @FXML private TextField addPrice;

    @FXML private Button logout;
    @FXML private Label alert;

    public ObservableList<IRViewModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productID.setCellValueFactory(new PropertyValueFactory<IRViewModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<IRViewModel, String>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<IRViewModel, Integer>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<IRViewModel, String>("price"));

        irtable.setItems(list);
        irtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            addID.setText(newSelection.getProductID());
        });
    }

    public void search(ActionEvent actionEvent) throws IOException {

        ProcurementSystem.makeInvoiceReceiving(Integer.parseInt(po_number.getText()));
        list = FXCollections.observableArrayList(mapIRItoViewModel());
        irtable.setItems(list);


    }


    public void add(ActionEvent actionEvent) throws IOException {

        ArrayList<String> findSkuNo = new ArrayList<>();
        ProcurementSystem.getInvoiceReceiving().getGoodsReceiving().getGoodsReceivingItems().forEach(goodsReceivingItem -> findSkuNo.add(goodsReceivingItem.getSku().getNo()));


        ProcurementSystem.editGoodsReceivingItemPurchasePrice(addID.getText(),Integer.parseInt(addPrice.getText()));


        list = FXCollections.observableArrayList(mapIRItoViewModel());
        irtable.setItems(list);


    }

    public void submit(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.makeInvoiceReceipt();
        alert.setText("Create IR success.");
    }


    public ArrayList<IRViewModel> mapIRItoViewModel(){

        GoodsReceiving goodsReceiving = ProcurementSystem.getInvoiceReceiving().getGoodsReceiving();

        ArrayList<IRViewModel> irViewModels = new ArrayList<>();

        for(GoodsReceivingItem gri : goodsReceiving.getGoodsReceivingItems()){
            IRViewModel irViewModel = new IRViewModel("0","0","0",0);
            irViewModel.setProductID(gri.getSku().getNo());
            irViewModel.setProductName(gri.getSku().getProduct().getName());
            irViewModel.setQuantity(gri.getQuantity());
            irViewModel.setPrice(Double.toString(gri.getPurchasePrice()));
            irViewModels.add(irViewModel);
        }


        return irViewModels;
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("log in");
        primaryStage.show();


    }
}
