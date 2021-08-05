package sample.procurement;

import java.time.LocalDateTime;

public class InvoiceReceiving {

    private LocalDateTime datetime;
    private String status;
    private GoodsReceiving goodsReceiving;
    private InvoiceReceipt invoiceReceipt;

    public InvoiceReceiving() {
        this.datetime = null;
        this.status = "pending";
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

    public GoodsReceiving getGoodsReceiving() {
        return goodsReceiving;
    }

    public void setGoodsReceiving(GoodsReceiving goodsReceiving) {
        this.goodsReceiving = goodsReceiving;
    }

    public InvoiceReceipt getInvoiceReceipt() {
        return invoiceReceipt;
    }

    public void setInvoiceReceipt(InvoiceReceipt invoiceReceipt) {
        this.invoiceReceipt = invoiceReceipt;
    }

    public void makeInvoiceReceipt() {
        this.datetime = LocalDateTime.now();
        this.status = "not paid";
        this.goodsReceiving.setStatus("received bill");
        this.invoiceReceipt = new InvoiceReceipt();
    }

}
