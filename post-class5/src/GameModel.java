/**
 * Quan ly trang thai co ban cua mot van bai.
 * GameModel duoc thiet ke theo mau Singleton.
 */
public final class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    private Deck deck;

    private GameModel() {
        newGame();
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public synchronized void newGame() {
        deck = new Deck();
    }

    public synchronized Deck getDeck() {
        return deck;
    }
}
