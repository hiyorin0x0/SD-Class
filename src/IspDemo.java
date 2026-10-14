public class IspDemo {
    public static void main(String[] args) {
        RefundableCardPayment card = new RefundableCardPayment();
        PaymentService service = new PaymentService(card);
        service.pay(100_000);

        Refundable refundService = card;
        refundService.refund(50_000);
    }
}
