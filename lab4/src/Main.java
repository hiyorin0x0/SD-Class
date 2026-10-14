public class Main {
    public static void main(String[] args) {
        PaymentService service =
            new PaymentService(new CreditCardPayment());
        service.pay(1_000_000);

        service.setPaymentStrategy(new PayPalPayment());
        service.pay(500_000);

        service.setPaymentStrategy(new EWalletPayment());
        service.pay(200_000);

        service.setPaymentStrategy(new BankTransferPayment());
        service.pay(2_000_000);
    }
}
