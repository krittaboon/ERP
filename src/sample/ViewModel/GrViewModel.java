package sample.ViewModel;

public class GrViewModel {
    private String productName;
    private String productID;
    private int quantity;

    public GrViewModel(String productName, String productID, int quantity) {
        this.productName = productName;
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
