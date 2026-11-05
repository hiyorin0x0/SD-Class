package ex66;

/**
 * Represents a performance that can take place in a theater. All
 * implementations of this interface should be immutable.
 *
 * <p>Reconstructed from DesignBook Chapter 6 (Exercise 6.6). The interface is
 * intentionally left unchanged: the iteration feature of Exercise 6.6 is
 * provided by {@link CompositeShow} only.
 */
public interface Show {

    /**
     * @return A description of the show.
     */
    String description();

    /**
     * @return The running time of the show, in minutes.
     */
    int runningTime();
}
