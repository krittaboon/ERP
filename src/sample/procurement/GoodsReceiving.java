package sample.procurement;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GoodsReceiving {

    private static int running_no = 1;
    private int no;
    private LocalDateTime datetime;
    private String status;
    private ArrayList<GoodsReceivingItem> goodsReceivingItems = new ArrayList<>();
    private Purchasing purchasing;
    private GoodsReceipt goodsReceipt;

    public GoodsReceiving() {
        this.no = 0;
        this.datetime = null;
        this.status = "pending";
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

    public ArrayList<GoodsReceivingItem> getGoodsReceivingItems() {
        return goodsReceivingItems;
    }

    public void setGoodsReceivingItems(ArrayList<GoodsReceivingItem> goodsReceivingItems) {
        this.goodsReceivingItems = goodsReceivingItems;
    }

    public Purchasing getPurchasing() {
        return purchasing;
    }

    public void setPurchasing(Purchasing purchasing) {
        this.purchasing = purchasing;
    }

    public GoodsReceipt getGoodsReceipt() {
        return goodsReceipt;
    }

    public void setGoodsReceipt(GoodsReceipt goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

    public void makeReceivingItem(Sku sku, int quantity) {
        this.goodsReceivingItems.add(new GoodsReceivingItem(sku, quantity, sku.getPurchasePrice()));
    }

    public void editGoodsReceivingItemQuantity(String skuNo, int quantity) {
        for (GoodsReceivingItem goodsReceivingItem : this.goodsReceivingItems) {
            if (goodsReceivingItem.getSku().getNo().equals(skuNo)) {
                goodsReceivingItem.setQuantity(quantity);
            }
        }
    }

    public void makeGoodsReceipt() {
        this.no = GoodsReceiving.running_no++;
        this.datetime = LocalDateTime.now();
        this.status = "not received bill";
        this.purchasing.setStatus("received");
        for (GoodsReceivingItem goodsReceivingItem : this.goodsReceivingItems) {
            int temp = goodsReceivingItem.getSku().getQuantityWarehouse();
            goodsReceivingItem.getSku().setQuantityWarehouse(temp + goodsReceivingItem.getQuantity());
        }
        this.goodsReceipt = new GoodsReceipt();
    }

    public void addGoodsReceivingItem(Sku sku) {
        this.goodsReceivingItems.add(new GoodsReceivingItem(sku, 0, sku.getPurchasePrice()));
    }

    public void removeGoodsReceivingItem(String skuNo) {
        ArrayList<GoodsReceivingItem> temp = new ArrayList<>();
        for (GoodsReceivingItem goodsReceivingItem : this.goodsReceivingItems) {
            if (!goodsReceivingItem.getSku().getNo().equals(skuNo)) {
                temp.add(goodsReceivingItem);
            }
        }
        this.goodsReceivingItems = temp;
    }

    public void editGoodsReceivingItemPurchasePrice(String skuNo, double purchasePrice) {
        for (GoodsReceivingItem goodsReceivingItem : this.goodsReceivingItems) {
            if (goodsReceivingItem.getSku().getNo().equals(skuNo)) {
                goodsReceivingItem.setPurchasePrice(purchasePrice);
            }
        }
    }

}
