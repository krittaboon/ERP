package sample.procurement;

import java.time.LocalDateTime;

public class Paying {

    private static int running_no = 1;
    private int no;
    private LocalDateTime datetime;
    private String status;
    private InvoiceReceiving invoiceReceiving;
    private PaymentVoucher paymentVoucher;

    public Paying() {
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

    public InvoiceReceiving getInvoiceReceiving() {
        return invoiceReceiving;
    }

    public void setInvoiceReceiving(InvoiceReceiving invoiceReceiving) {
        this.invoiceReceiving = invoiceReceiving;
    }

    public PaymentVoucher getPaymentVoucher() {
        return paymentVoucher;
    }

    public void setPaymentVoucher(PaymentVoucher paymentVoucher) {
        this.paymentVoucher = paymentVoucher;
    }

    public void makePaymentVoucher() {
        this.no = Paying.running_no++;
        this.datetime = LocalDateTime.now();
        this.status = "paid";
        this.invoiceReceiving.setStatus("paid");
    }

}
