package ex67;

import java.util.Iterator;

/**
 * Represents a performance that can take place in a theater. All
 * implementations of this interface should be immutable.
 *
 * <p>Variant of the DesignBook Chapter 6 hierarchy for Exercise 6.7: the
 * interface itself is modified so that it extends {@link Iterable Show}. Every
 * show, leaf or composite, can therefore be iterated over ("unpacked")
 * directly through a {@code Show} reference, with no downcast.
 */
public interface Show extends Iterable<Show> {

    /**
     * @return A description of the show.
     */
    String description();

    /**
     * @return The running time of the show, in minutes.
     */
    int runningTime();
}
