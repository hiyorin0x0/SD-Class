/** A source that can draw a card and report whether it is empty. */
public interface CardSource {
    Card draw();

    boolean isEmpty();
}