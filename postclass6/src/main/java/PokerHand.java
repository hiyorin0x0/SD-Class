import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** A five-card hand with the predicate used in DesignBook Exercise 5.8. */
public final class PokerHand {
    private final List<Card> hand;

    public PokerHand(Card... cards) {
        if (cards.length != 5) {
            throw new IllegalArgumentException("A poker hand must contain five cards.");
        }
        ArrayList<Card> sorted = new ArrayList<>(Arrays.asList(cards));
        sorted.sort((first, second) -> first.getRank().compareTo(second.getRank()));
        this.hand = Collections.unmodifiableList(sorted);
    }

    /**
     * Returns true when all five cards have one suit and consecutive ranks.
     * With this rank ordering, Ace is low and cannot follow King.
     */
    private boolean isStraightFlush() {
        Suit suit = hand.get(0).getSuit();
        Rank rank = hand.get(0).getRank();
        var iterator = hand.iterator();
        iterator.next();

        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getSuit() != suit || card.getRank().ordinal() != rank.ordinal() + 1) {
                return false;
            }
            rank = card.getRank();
        }
        return true;
    }
}
