package sample.procurement;

public class GoodsReceivingItem {

    private Sku sku;
    private int quantity;
    private double purchasePrice;

    public GoodsReceivingItem(Sku sku, int quantity, double purchasePrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

}
