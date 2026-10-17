import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A shuffled standard 52-card deck. */
public final class Deck {
    private static final String[] SUITS = {
        "Hearts", "Diamonds", "Clubs", "Spades"
    };

    private static final String[] RANKS = {
        "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    };

    private final List<String> cards = new ArrayList<String>();

    public Deck() {
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                cards.add(rank + " of " + suit);
            }
        }
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public List<String> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
