package sample.UI.Finance;

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
import sample.ViewModel.PayViewModel;
import sample.procurement.GoodsReceiving;
import sample.procurement.GoodsReceivingItem;
import sample.procurement.InvoiceReceiving;
import sample.procurement.ProcurementSystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PayingController implements Initializable {
    @FXML private TableView<PayViewModel> irtable;
    @FXML private TableColumn<PayViewModel, String> productID;
    @FXML private TableColumn<PayViewModel, String> productName;
    @FXML private TableColumn<PayViewModel, Integer> quantity;
    @FXML private TableColumn<PayViewModel, Double> price;
    @FXML private TableColumn<PayViewModel, Double> total;
    @FXML private TextField gr_number;
    @FXML private Label totalLb;

    @FXML private Button logout;
    @FXML private Label alert;

    double sum =0;



    public ObservableList<PayViewModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productID.setCellValueFactory(new PropertyValueFactory<PayViewModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<PayViewModel, String>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<PayViewModel, Integer>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<PayViewModel, Double>("price"));
        total.setCellValueFactory(new PropertyValueFactory<PayViewModel, Double>("total"));

        irtable.setItems(list);

    }

    public void search(ActionEvent actionEvent) throws IOException {

        ProcurementSystem.makePaying(Integer.parseInt(gr_number.getText()));


        list = FXCollections.observableArrayList(mapPRItoViewModel());
        totalLb.setText(Double.toString(sum));
        irtable.setItems(list);

    }


    public void submit(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.makePaymentVoucher();
        alert.setText("create payment success.");
    }


    public ArrayList<PayViewModel> mapPRItoViewModel(){

        GoodsReceiving goodsReceiving = ProcurementSystem.getInvoiceReceiving().getGoodsReceiving();

        ArrayList<PayViewModel> payViewModels = new ArrayList<>();

        for(GoodsReceivingItem gri : goodsReceiving.getGoodsReceivingItems()){
            PayViewModel payViewModel = new PayViewModel("0","0",0,0,0);
            payViewModel.setProductID(gri.getSku().getNo());
            payViewModel.setProductName(gri.getSku().getProduct().getName());
            payViewModel.setQuantity(gri.getQuantity());
            payViewModel.setPrice(gri.getPurchasePrice());
            payViewModel.setTotal(gri.getQuantity()*gri.getPurchasePrice());
            payViewModels.add(payViewModel);
            sum+=gri.getQuantity()*gri.getPurchasePrice();
        }


        return payViewModels;
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
