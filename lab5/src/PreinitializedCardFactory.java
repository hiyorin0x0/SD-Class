import java.util.HashMap;
import java.util.Map;

public final class PreinitializedCardFactory implements CardFactory {
    private final Map<Integer, Card> cards = new HashMap<Integer, Card>();

    public PreinitializedCardFactory() {
        for (Card.Rank rank : Card.Rank.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                cards.put(Card.key(rank, suit), Card.create(rank, suit));
            }
        }
    }

    @Override
    public Card getCard(Card.Rank rank, Card.Suit suit) {
        Card card = cards.get(Card.key(rank, suit));
        if (card == null) {
            throw new IllegalArgumentException("Unknown card");
        }
        return card;
    }

    @Override
    public int size() {
        return cards.size();
    }
}
