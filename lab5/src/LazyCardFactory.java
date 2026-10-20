import java.util.HashMap;
import java.util.Map;

public final class LazyCardFactory implements CardFactory {
    private final Map<Integer, Card> cards = new HashMap<Integer, Card>();

    @Override
    public synchronized Card getCard(Card.Rank rank, Card.Suit suit) {
        int key = Card.key(rank, suit);
        Card card = cards.get(key);
        if (card == null) {
            card = Card.create(rank, suit);
            cards.put(key, card);
        }
        return card;
    }

    @Override
    public synchronized int size() {
        return cards.size();
    }
}
