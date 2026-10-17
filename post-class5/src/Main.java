public class Main {
    public static void main(String[] args) {
        GameModel firstAccess = GameModel.getInstance();
        GameModel secondAccess = GameModel.getInstance();

        System.out.println("Cung mot GameModel: " + (firstAccess == secondAccess));

        firstAccess.newGame();
        Deck firstDeck = firstAccess.getDeck();

        firstAccess.newGame();
        Deck secondDeck = firstAccess.getDeck();

        System.out.println("newGame tao Deck moi: " + (firstDeck != secondDeck));
        System.out.println("So la bai trong bo moi: " + secondDeck.size());
    }
}
