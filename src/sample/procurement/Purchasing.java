package sample.procurement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchasing {

    private static int running_no = 1;
    private int no;
    private LocalDateTime datetime;
    private String status;
    private LocalDate deliveredDate;
    private Vendor vendor;
    private ArrayList<PurchasingItem> purchasingItems = new ArrayList<>();
    private PurchaseOrder purchaseOrder;

    public Purchasing() {
        this.no = 0;
        this.datetime = null;
        this.status = "pending";
        this.deliveredDate = null;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public ArrayList<PurchasingItem> getPurchasingItems() {
        return purchasingItems;
    }

    public void setPurchasingItems(ArrayList<PurchasingItem> purchasingItems) {
        this.purchasingItems = purchasingItems;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void chooseVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void addPurchasingItem(Sku sku) {
        this.purchasingItems.add(new PurchasingItem(sku, 0));
    }

    public void editPurchasingItem(String skuNo, int quantity) {
        for (PurchasingItem purchasingItem : this.purchasingItems) {
            if (purchasingItem.getSku().getNo().equals(skuNo)) {
                purchasingItem.setQuantity(quantity);
            }
        }
    }

    public void removePurchasingItem(String skuNo) {
        ArrayList<PurchasingItem> temp = new ArrayList<>();
        for (PurchasingItem purchasingItem : this.purchasingItems) {
            if (!purchasingItem.getSku().getNo().equals(skuNo)) {
                temp.add(purchasingItem);
            }
        }
        this.purchasingItems = temp;
    }

    public void makePurchaseOrder() {
        this.no = Purchasing.running_no++;
        this.datetime = LocalDateTime.now();
        this.status = "not received";
        this.purchaseOrder = new PurchaseOrder();
    }

    public void saveDeliveryDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

}
