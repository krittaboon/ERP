package sample.UI.Purchasing;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import sample.procurement.ProcurementSystem;

import java.awt.*;
import java.io.IOException;

public class PurchasingMenuController {

    @FXML
    Button createBtn;
    @FXML
    Button updateBtn;

    public void create(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreatePurchasing.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("create purchasing");
        primaryStage.show();


    }

    public void update(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePurchasing.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) updateBtn.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("update purchasing");
        primaryStage.show();


    }
}
