package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.procurement.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/login.fxml"));
        primaryStage.setTitle("Procurement system");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();

    }


    public static void main(String[] args) {

        ProcurementSystem procurementSystem = new ProcurementSystem();
        initialize(procurementSystem);
//        ProcurementSystem.printDetail();

        launch(args);
    }

    public static void initialize(ProcurementSystem procurementSystem){
        Employee samorn = new Employee("P0001", "Samorn", "Purchasing", "1234");
        procurementSystem.getEmployeeArrayList().add(samorn);
        Employee sumruam = new Employee("I0001", "Sumruam", "Inventory", "1234");
        procurementSystem.getEmployeeArrayList().add(sumruam);
        Employee jaruay = new Employee("A0001", "Jaruay", "Accounting", "1234");
        procurementSystem.getEmployeeArrayList().add(jaruay);
        Employee chaimeing = new Employee("F0001", "Chaimeing", "Finance", "1234");
        procurementSystem.getEmployeeArrayList().add(chaimeing);

        Vendor vendorConverse = new Vendor("CONVERSE CO. LTD.", "info@converse.com");
        ProcurementSystem.getVendorArrayList().add(vendorConverse);

        Product product101 = new Product("CONVERSE x BANDULU T-SHIRT", vendorConverse);

        Sku sku101WS = new Sku(product101, "101WS", "S", "WHITE", 200, 100, 0);
        ProcurementSystem.getSkuArrayList().add(sku101WS);
        Sku sku101WM = new Sku(product101, "101WM", "M", "WHITE", 200, 100, 0);
        ProcurementSystem.getSkuArrayList().add(sku101WM);
        Sku sku101WL = new Sku(product101, "101WL", "L", "WHITE", 220, 120, 0);
        ProcurementSystem.getSkuArrayList().add(sku101WL);
        Sku sku101WXL = new Sku(product101, "101WXL", "XL", "WHITE", 220, 120, 0);
        ProcurementSystem.getSkuArrayList().add(sku101WXL);

//        ProcurementSystem.makePurchasing();
//        ProcurementSystem.chooseVendor("CONVERSE CO. LTD.");
//        ProcurementSystem.addPurchasingItem("101WS");
//        ProcurementSystem.editPurchasingItem("101WS", 10);
//        ProcurementSystem.addPurchasingItem("101WM");
//        ProcurementSystem.editPurchasingItem("101WM", 20);
//        ProcurementSystem.addPurchasingItem("101WL");
//        ProcurementSystem.editPurchasingItem("101WL", 20);
//        ProcurementSystem.makePurchaseOrder();
//
//
//        ProcurementSystem.makeReceiving(1);
//        ProcurementSystem.editGoodsReceivingItemQuantity("101WS", 20);
//        ProcurementSystem.addGoodsReceivingItem("101WXL");
//        ProcurementSystem.editGoodsReceivingItemQuantity("101WXL", 20);
//        ProcurementSystem.makeGoodReceipt();
//
//        ProcurementSystem.makeInvoiceReceiving(1);
//        ProcurementSystem.editGoodsReceivingItemPurchasePrice("101WM", 120);
//        ProcurementSystem.makeInvoiceReceipt();
////
////        ProcurementSystem.makePaying(1);
////        ProcurementSystem.makePaymentVoucher();
    }

}
