public interface CardFactory {
    Card getCard(Card.Rank rank, Card.Suit suit);

    int size();
}
