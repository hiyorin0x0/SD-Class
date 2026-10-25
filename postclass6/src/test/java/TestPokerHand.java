import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestPokerHand {
    private static final Method IS_STRAIGHT_FLUSH = findPredicate();

    private static Method findPredicate() {
        try {
            Method method = PokerHand.class.getDeclaredMethod("isStraightFlush");
            method.setAccessible(true);
            return method;
        } catch (ReflectiveOperationException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    private static boolean isStraightFlush(PokerHand hand) {
        try {
            return (boolean) IS_STRAIGHT_FLUSH.invoke(hand);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new AssertionError("Could not invoke isStraightFlush", exception);
        }
    }

    private static PokerHand straightFlush(Suit suit, int firstRankIndex) {
        Rank[] ranks = Rank.values();
        List<Card> cards = new ArrayList<>(5);
        for (int index = firstRankIndex; index < firstRankIndex + 5; index++) {
            cards.add(new Card(ranks[index], suit));
        }
        return new PokerHand(cards.toArray(Card[]::new));
    }

    /** Exercise 5.8: enumerate every ace-low straight-flush hand. */
    @Test
    void exercise58_allStraightFlushesReturnTrue() {
        int executions = 0;
        for (Suit suit : Suit.values()) {
            for (int start = 0; start <= Rank.values().length - 5; start++) {
                assertTrue(isStraightFlush(straightFlush(suit, start)), "Expected a straight flush for " + suit + " starting at " + start);
                executions++;
            }
        }
        assertEquals(36, executions, "9 sequences in each of 4 suits");
    }

    /** Exercise 5.9: two executions cover every statement in the predicate. */
    @Test
    void exercise59_twoExecutionsCoverAllStatements() {
        assertTrue(isStraightFlush(straightFlush(Suit.CLUBS, 1))); // Executes the loop and final true return.
        assertFalse(isStraightFlush(new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.CLUBS)))); // Executes the false return on a rank gap.
    }

    /** Exercise 5.10: true, suit mismatch, and rank mismatch cover all branches. */
    @Test
    void exercise510_threeExecutionsCoverAllBranches() {
        assertTrue(isStraightFlush(straightFlush(Suit.HEARTS, 1)));

        assertFalse(isStraightFlush(new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.SPADES),
                new Card(Rank.FOUR, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.CLUBS)))); // Different suit makes the first OR operand true.

        assertFalse(isStraightFlush(new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.CLUBS)))); // Same suit, but the rank comparison is true for mismatch.
    }
}
