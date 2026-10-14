import java.util.Objects;

public class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        setPaymentStrategy(strategy);
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = Objects.requireNonNull(
            strategy, "Phương thức thanh toán không được null");
    }

    public void pay(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                "Số tiền phải hữu hạn và lớn hơn 0");
        }
        strategy.pay(amount);
    }
}
