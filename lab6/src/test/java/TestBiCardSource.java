import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestBiCardSource {
    private static final class StubCardSource implements CardSource {
        private final boolean empty;
        private final Card card;
        private int drawCalls;
        private int isEmptyCalls;

        StubCardSource(boolean empty, Card card) {
            this.empty = empty;
            this.card = card;
        }

        @Override
        public Card draw() {
            drawCalls++;
            return card;
        }

        @Override
        public boolean isEmpty() {
            isEmptyCalls++;
            return empty;
        }
    }

    @Test
    void drawWhenFirstSourceIsNotEmptyTakesSecondBranch() {
        Card fromFirst = new Card("first");
        Card fromSecond = new Card("second");
        StubCardSource first = new StubCardSource(false, fromFirst);
        StubCardSource second = new StubCardSource(false, fromSecond);
        BiCardSource combined = new BiCardSource(first, second);

        assertSame(fromSecond, combined.draw());
        assertEquals(0, first.drawCalls);
        assertEquals(1, second.drawCalls);
    }

    @Test
    void drawWhenFirstSourceIsEmptyTakesElseBranch() {
        Card fromFirst = new Card("first");
        Card fromSecond = new Card("second");
        StubCardSource first = new StubCardSource(true, fromFirst);
        StubCardSource second = new StubCardSource(false, fromSecond);
        BiCardSource combined = new BiCardSource(first, second);

        assertSame(fromFirst, combined.draw());
        assertEquals(1, first.drawCalls);
        assertEquals(0, second.drawCalls);
    }

    @Test
    void isEmptyShortCircuitsWhenFirstSourceIsNotEmpty() {
        StubCardSource first = new StubCardSource(false, new Card("first"));
        StubCardSource second = new StubCardSource(true, new Card("second"));
        BiCardSource combined = new BiCardSource(first, second);

        assertFalse(combined.isEmpty());
        assertEquals(1, first.isEmptyCalls);
        assertEquals(0, second.isEmptyCalls);
    }

    @Test
    void isEmptyChecksSecondSourceWhenFirstIsEmptyAndSecondIsNot() {
        StubCardSource first = new StubCardSource(true, new Card("first"));
        StubCardSource second = new StubCardSource(false, new Card("second"));
        BiCardSource combined = new BiCardSource(first, second);

        assertFalse(combined.isEmpty());
        assertEquals(1, first.isEmptyCalls);
        assertEquals(1, second.isEmptyCalls);
    }

    @Test
    void isEmptyReturnsTrueWhenBothSourcesAreEmpty() {
        StubCardSource first = new StubCardSource(true, new Card("first"));
        StubCardSource second = new StubCardSource(true, new Card("second"));
        BiCardSource combined = new BiCardSource(first, second);

        assertTrue(combined.isEmpty());
        assertEquals(1, first.isEmptyCalls);
        assertEquals(1, second.isEmptyCalls);
    }
}