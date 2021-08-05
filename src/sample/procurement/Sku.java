package sample.procurement;

public class Sku {

    private Product product;
    private String no, size, color;
    private double salesPrice, purchasePrice;
    private int quantityWarehouse;

    public Sku(Product product, String no, String size, String color, double salesPrice, double purchasePrice, int quantityWarehouse) {
        this.product = product;
        this.no = no;
        this.size = size;
        this.color = color;
        this.salesPrice = salesPrice;
        this.purchasePrice = purchasePrice;
        this.quantityWarehouse = quantityWarehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getQuantityWarehouse() {
        return quantityWarehouse;
    }

    public void setQuantityWarehouse(int quantityWarehouse) {
        this.quantityWarehouse = quantityWarehouse;
    }

    public String getSkuName() {
        return this.product.getName() + " " + this.color + " size " + this.size;
    }

}
