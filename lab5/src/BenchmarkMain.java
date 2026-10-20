import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.Locale;
import java.util.function.Supplier;

public class BenchmarkMain {
    private static final int WARMUP_ROUNDS = 500;
    private static final int MEASURED_ROUNDS = 10000;
    private static volatile long checksumSink;

    public static void main(String[] args) {
        Supplier<CardFactory> eagerSupplier = PreinitializedCardFactory::new;
        Supplier<CardFactory> lazySupplier = LazyCardFactory::new;

        verifyFactory(eagerSupplier.get(), "Preinitialized");
        verifyFactory(lazySupplier.get(), "Lazy");

        measure(eagerSupplier, WARMUP_ROUNDS);
        measure(lazySupplier, WARMUP_ROUNDS);

        long eagerMillis = measure(eagerSupplier, MEASURED_ROUNDS);
        long lazyMillis = measure(lazySupplier, MEASURED_ROUNDS);

        System.out.println("Each round constructs one factory and requests all 52 cards.");
        System.out.println("Measured rounds: " + MEASURED_ROUNDS);
        printResult("Preinitialized", eagerMillis);
        printResult("Lazy", lazyMillis);

        if (eagerMillis < lazyMillis) {
            System.out.println("This run: pre-initialization was faster for the complete 52-card set.");
        } else if (lazyMillis < eagerMillis) {
            System.out.println("This run: lazy initialization was faster for the complete 52-card set.");
        } else {
            System.out.println("This run: both approaches had the same millisecond result.");
        }
        System.out.println("Checksum: " + checksumSink);
    }

    private static void verifyFactory(CardFactory factory, String name) {
        Set<Card> identities = Collections.newSetFromMap(
            new IdentityHashMap<Card, Boolean>()
        );

        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                Card first = factory.getCard(rank, suit);
                Card second = factory.getCard(rank, suit);
                if (first != second) {
                    throw new IllegalStateException("Factory returned duplicate flyweights");
                }
                identities.add(first);
            }
        }

        if (identities.size() != 52 || factory.size() != 52) {
            throw new IllegalStateException("Expected 52 unique card flyweights");
        }
        System.out.println(name + " factory: 52 unique cards; repeated lookup reuses each object.");
    }

    private static long measure(
        Supplier<CardFactory> factorySupplier,
        int rounds
    ) {
        long checksum = 0;
        long start = System.currentTimeMillis();

        for (int round = 0; round < rounds; round++) {
            CardFactory factory = factorySupplier.get();
            for (Card.Rank rank : Card.Rank.values()) {
                for (Card.Suit suit : Card.Suit.values()) {
                    Card card = factory.getCard(rank, suit);
                    checksum += card.getRank().ordinal() + card.getSuit().ordinal();
                }
            }
        }

        long elapsedMillis = System.currentTimeMillis() - start;
        checksumSink = checksum;
        return elapsedMillis;
    }

    private static void printResult(String label, long elapsedMillis) {
        double averagePerSet = (double) elapsedMillis / MEASURED_ROUNDS;
        System.out.printf(
            Locale.ROOT,
            "%s: %d ms total; %.4f ms per complete set%n",
            label,
            elapsedMillis,
            averagePerSet
        );
    }
}
