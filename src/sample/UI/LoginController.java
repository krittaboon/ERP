package sample.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.procurement.ProcurementSystem;

import java.io.IOException;


public class LoginController {

    @FXML
    Button signin;
    @FXML
    TextField no;
    @FXML
    PasswordField password;


    public void signin(ActionEvent actionEvent) throws IOException {

        String userID = no.getText();
        String passwordText = password.getText();

        switch (ProcurementSystem.signIn(userID,passwordText)){
            case "Purchasing" :
                purchasing_login();
                break;
            case "Inventory" :
                inventory_login();
                break;
            case "Accounting" :
                accounting_login();
                break;
            case "Finance" :
                finance_login();
                break;
            default:
                break;
        }

    }

    public void inventory_login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Inventory/goodsReceiving.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) signin.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Good receiving");
        primaryStage.show();
    }

    public void purchasing_login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Purchasing/PurchasingMenu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) signin.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Purchasing");
        primaryStage.show();
    }

    public void accounting_login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accounting/InvoiceReceiving.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) signin.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Accounting");
        primaryStage.show();
    }

    public void finance_login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Finance/Paying.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) signin.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Finance");
        primaryStage.show();
    }
}
