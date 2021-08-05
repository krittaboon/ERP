package sample.UI.Inventory;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import sample.ViewModel.GrViewModel;
import sample.procurement.*;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GoodsReceivingController implements Initializable {
    @FXML private TableView<GrViewModel> grtable;
    @FXML private TableColumn<GrViewModel, String> productID;
    @FXML private TableColumn<GrViewModel, String> productName;
    @FXML private TableColumn<GrViewModel, Integer> quantity;
    @FXML private TextField po_number;
    @FXML private TextField addID;
    @FXML private TextField addQty;

    @FXML private Button logout;
    @FXML private Label alert;


    public ObservableList<GrViewModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productID.setCellValueFactory(new PropertyValueFactory<GrViewModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<GrViewModel, String>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<GrViewModel, Integer>("quantity"));

        grtable.setItems(list);
        grtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            addID.setText(newSelection.getProductID());
        });
    }

    public void search(ActionEvent actionEvent) throws IOException {


        ProcurementSystem.makeReceiving(Integer.parseInt(po_number.getText()));
        list = FXCollections.observableArrayList(mapGRItoViewModel());
        grtable.setItems(list);


    }


    public void add(ActionEvent actionEvent) throws IOException {

        ArrayList<String> findSkuNo = new ArrayList<>();
        ProcurementSystem.getGoodsReceiving().getGoodsReceivingItems().forEach(goodsReceivingItem -> findSkuNo.add(goodsReceivingItem.getSku().getNo()));

        if(findSkuNo.contains(addID.getText())){
            ProcurementSystem.editGoodsReceivingItemQuantity(addID.getText(),Integer.parseInt(addQty.getText()));

        }else{
            ProcurementSystem.addGoodsReceivingItem(addID.getText());
            ProcurementSystem.editGoodsReceivingItemQuantity(addID.getText(),Integer.parseInt(addQty.getText()));

        }


        list = FXCollections.observableArrayList(mapGRItoViewModel());
        grtable.setItems(list);


    }

    public void problem(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.foundGoodsReceivingProblem();
        alert.setText("Create GR error.");
    }

    public void submit(ActionEvent actionEvent) throws IOException {
        ProcurementSystem.makeGoodReceipt();
        alert.setText("Create GR success.");
    }


    public ArrayList<GrViewModel> mapGRItoViewModel(){

        GoodsReceiving goodsReceiving = ProcurementSystem.getGoodsReceiving();

        ArrayList<GrViewModel> grViewModels = new ArrayList<>();

        for(GoodsReceivingItem gri : goodsReceiving.getGoodsReceivingItems()){
            GrViewModel grViewModel = new GrViewModel("0","0",0);
            grViewModel.setProductID(gri.getSku().getNo());
            grViewModel.setProductName(gri.getSku().getProduct().getName());
            grViewModel.setQuantity(gri.getQuantity());
            grViewModels.add(grViewModel);
        }


        return grViewModels;
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
