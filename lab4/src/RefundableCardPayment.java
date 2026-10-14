/** Ví dụ mở rộng ISP, không bắt buộc mọi strategy hỗ trợ hoàn tiền. */
public class RefundableCardPayment implements PaymentStrategy, Refundable {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng thẻ: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Hoàn tiền về thẻ: " + amount);
    }
}
