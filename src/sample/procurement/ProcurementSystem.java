package sample.procurement;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProcurementSystem {

    private static Purchasing purchasing = null;
    private static GoodsReceiving goodsReceiving = null;
    private static InvoiceReceiving invoiceReceiving = null;
    private static Paying paying = null;

    private  static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    private static ArrayList<Sku> skuArrayList = new ArrayList<>();
    private static ArrayList<Vendor> vendorArrayList = new ArrayList<>();
    private static ArrayList<Purchasing> purchasingArrayList = new ArrayList<>();
    private static ArrayList<GoodsReceiving> goodsReceivingArrayList = new ArrayList<>();
    private static ArrayList<InvoiceReceiving> invoiceReceivingArrayList = new ArrayList<>();

    public static Purchasing getPurchasing() {
        return purchasing;
    }

    public static GoodsReceiving getGoodsReceiving() {
        return goodsReceiving;
    }

    public static InvoiceReceiving getInvoiceReceiving() {
        return invoiceReceiving;
    }

    public static Paying getPaying() {
        return paying;
    }

    public static ArrayList<Vendor> getVendorArrayList() {
        return vendorArrayList;
    }

    public static Sku findSku(String skuNo) {
        for (Sku sku : skuArrayList) {
            if (sku.getNo().equals(skuNo)) {
                return sku;
            }
        }
        return null;
    }

    public static Purchasing findPurchasing(int purchasingNo) {
        for (Purchasing purchasing : purchasingArrayList) {
            if (purchasing.getNo() == purchasingNo) {
                return purchasing;
            }
        }
        return null;
    }

    public static GoodsReceiving findGoodsReceiving(int goodsReceivingNo) {
        for (GoodsReceiving goodsReceiving : goodsReceivingArrayList) {
            if (goodsReceiving.getNo() == goodsReceivingNo) {
                return goodsReceiving;
            }
        }
        return null;
    }

    public static InvoiceReceiving findInvoiceReceiving(int goodsReceivingNo) {
        for (InvoiceReceiving invoiceReceiving : invoiceReceivingArrayList) {
            if (invoiceReceiving.getGoodsReceiving().getNo() == goodsReceivingNo) {
                return invoiceReceiving;
            }
        }
        return null;
    }

    // เปิดการสั่งซื้อ
    public static void makePurchasing() {
        purchasing = new Purchasing();
    }

    // เปิดการสั่งซื้อตอน update
    public static void openPurchasing(int purchasingNo) {
        purchasing = purchasingArrayList.get(purchasingArrayList.indexOf(findPurchasing(purchasingNo)));
    }

    // เลือก Vendor ให้การสั่งซื้อ
    public static void chooseVendor(String vendorName) {
        for (Vendor vendor : vendorArrayList) {
            if (vendor.getName().equals(vendorName)) {
                purchasing.chooseVendor(vendor);
            }
        }
    }

    // เพิ่มสินค้าในคำสั่งซื้อ
    public static void addPurchasingItem(String skuNo) {
        purchasing.addPurchasingItem(findSku(skuNo));
    }

    // แก้ไขจำนวนสินค้าในคำสั่งซื้อ
    public static void editPurchasingItem(String skuNo, int quantity) {
        purchasing.editPurchasingItem(skuNo, quantity);
    }

    // ลบสินค้าในการสั่งซื้อ
    public static void removePurchasingItem(String skuNo) {
        purchasing.removePurchasingItem(skuNo);
    }

    // ยืนยันการสร้างการสั่งซื้อ
    public static void makePurchaseOrder() {
        purchasing.makePurchaseOrder();
        purchasingArrayList.add(purchasing);
        printPoDetail(purchasing);
        purchasing = null;
    }

    // บันทึกวันรับสินค้า
    public static void saveDeliveryDate(int purchasingNo, LocalDate deliveredDate) {
        findPurchasing(purchasingNo).saveDeliveryDate(deliveredDate);
        printPoDetail(purchasing);
    }

    // เปิดการรับของ
    public static void makeReceiving(int purchasingNo) {

        goodsReceiving = new GoodsReceiving();

        purchasing = findPurchasing(purchasingNo);
        goodsReceiving.setPurchasing(purchasing);
        for (PurchasingItem purchasingItem : purchasing.getPurchasingItems()) {
            goodsReceiving.makeReceivingItem(purchasingItem.getSku(), purchasingItem.getQuantity());
        }

    }

    // เพิ่มสินค้าในการรับของ
    public static void addGoodsReceivingItem(String skuNo) {
        goodsReceiving.addGoodsReceivingItem(findSku(skuNo));
    }

    // แก้ไขจำนวนสินค้าในการรับของ
    public static void editGoodsReceivingItemQuantity(String skuNo, int quantity) {
        goodsReceiving.editGoodsReceivingItemQuantity(skuNo, quantity);
    }

    // ลบสินค้าในการรับของ
    public static void removeGoodsReceivingItem(String skuNo) {
        goodsReceiving.removeGoodsReceivingItem(skuNo);
    }

    // ยืนยันการรับของ
    public static void makeGoodReceipt() {
        goodsReceiving.makeGoodsReceipt();
        goodsReceivingArrayList.add(goodsReceiving);
        printPoDetail(purchasing);
        printGrDetail(goodsReceiving);
        purchasing = null;
        goodsReceiving = null;
    }

    // บันทึกปัญหาการรับของ
    public static void foundGoodsReceivingProblem() {
        goodsReceiving.getPurchasing().setStatus("problem");
        goodsReceiving.setPurchasing(null);
        goodsReceiving.setGoodsReceivingItems(null);
        goodsReceiving = null;
    }

    // เปิดการรับใบวางบิล
    public static void makeInvoiceReceiving(int goodsReceivingNo) {

        invoiceReceiving = new InvoiceReceiving();

        goodsReceiving = findGoodsReceiving(goodsReceivingNo);
        invoiceReceiving.setGoodsReceiving(goodsReceiving);

    }

    // แก้ไขราคาซื้อสินค้าในการรับใบวางบิล
    public static void editGoodsReceivingItemPurchasePrice(String skuNo, double purchasePrice) {
        invoiceReceiving.getGoodsReceiving().editGoodsReceivingItemPurchasePrice(skuNo, purchasePrice);
    }

    // ยืนยันการรับใบวางบิล
    public static void makeInvoiceReceipt() {
        invoiceReceiving.makeInvoiceReceipt();
        invoiceReceivingArrayList.add(invoiceReceiving);
        printGrDetail(goodsReceiving);
        printIrDetail(invoiceReceiving);
        goodsReceiving = null;
        invoiceReceiving = null;
    }

    // เปิดการจ่ายเงินให้ Vendor
    public static void makePaying(int goodsReceivingNo) {

        paying = new Paying();

        invoiceReceiving = findInvoiceReceiving(goodsReceivingNo);
        paying.setInvoiceReceiving(invoiceReceiving);

    }

    // ยืนยันการจ่ายเงินให้ Vendor
    public static void makePaymentVoucher() {
        paying.makePaymentVoucher();
        printIrDetail(invoiceReceiving);
        printPvDetail(paying);
        invoiceReceiving = null;
        paying = null;
    }

    public static void printPoDetail(Purchasing purchasing) {
        System.out.println("\nPO ===============");
        System.out.println("PO No. : " + purchasing.getNo());
        System.out.println("PO Datetime : " + purchasing.getDatetime());
        System.out.println("PO Deliv. Datetime : " + purchasing.getDeliveredDate());
        System.out.println("PO Status : " + purchasing.getStatus());
        System.out.println("PO Items :");
        for (PurchasingItem purchasingItem : purchasing.getPurchasingItems()) {
            System.out.println(purchasingItem.getSku().getSkuName() + " : " + purchasingItem.getQuantity() + " : " + purchasingItem.getSku().getPurchasePrice());
        }
    }

    public static void printGrDetail(GoodsReceiving goodsReceiving) {
        System.out.println("\nGR ===============");
        System.out.println("GR No. : " + goodsReceiving.getNo());
        System.out.println("GR Datetime : " + goodsReceiving.getDatetime());
        System.out.println("GR Status : " + goodsReceiving.getStatus());
        System.out.println("GR Items :");
        for (GoodsReceivingItem goodsReceivingItem : goodsReceiving.getGoodsReceivingItems()) {
            System.out.println(goodsReceivingItem.getSku().getSkuName() + " : " + goodsReceivingItem.getQuantity() + " : " + goodsReceivingItem.getPurchasePrice());
        }
    }

    public static void printIrDetail(InvoiceReceiving invoiceReceiving) {
        System.out.println("\nIR ===============");
        System.out.println("IR Datetime : " + invoiceReceiving.getDatetime());
        System.out.println("IR Status : " + invoiceReceiving.getStatus());
        System.out.println("IR Items :");
        for (GoodsReceivingItem goodsReceivingItem : invoiceReceiving.getGoodsReceiving().getGoodsReceivingItems()) {
            System.out.println(goodsReceivingItem.getSku().getSkuName() + " : " + goodsReceivingItem.getQuantity() + " : " + goodsReceivingItem.getPurchasePrice());
        }
    }

    public static void printPvDetail(Paying paying) {
        System.out.println("\nPV ===============");
        System.out.println("PV No. : " + paying.getNo());
        System.out.println("PV Datetime : " + paying.getDatetime());
        System.out.println("PV Status : " + paying.getStatus());
        System.out.println("PV Items :");
        for (GoodsReceivingItem goodsReceivingItem : paying.getInvoiceReceiving().getGoodsReceiving().getGoodsReceivingItems()) {
            System.out.println(goodsReceivingItem.getSku().getSkuName() + " : " + goodsReceivingItem.getQuantity() + " : " + goodsReceivingItem.getPurchasePrice());
        }
    }

    public static String signIn(String no, String password) {
        for (Employee employee : employeeArrayList) {
            if (employee.getNo().equals(no) && employee.getPassword().equals(password)) {
                return employee.getDepartment();
            }
        }
        return "ERROR";
    }

    public static ArrayList<Purchasing> getPurchasingArrayList() {
        return purchasingArrayList;
    }

    public static ArrayList<Employee> getEmployeeArrayList() {
        return employeeArrayList;
    }

    public static ArrayList<Sku> getSkuArrayList() {
        return skuArrayList;
    }

    public static ArrayList<GoodsReceiving> getGoodsReceivingArrayList() {
        return goodsReceivingArrayList;
    }

    public static ArrayList<InvoiceReceiving> getInvoiceReceivingArrayList() {
        return invoiceReceivingArrayList;
    }
}