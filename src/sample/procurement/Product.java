package sample.procurement;

public class Product {

    private String name;
    private Vendor vendor;

    public Product(String name, Vendor vendor) {
        this.name = name;
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

}