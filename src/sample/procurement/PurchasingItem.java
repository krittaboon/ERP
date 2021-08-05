package sample.procurement;

public class PurchasingItem {
    private Sku sku;
    private int quantity;

    public PurchasingItem(Sku sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
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

}
