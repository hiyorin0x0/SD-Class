/** Chức năng hoàn tiền được tách riêng theo ISP. */
public interface Refundable {
    void refund(double amount);
}
