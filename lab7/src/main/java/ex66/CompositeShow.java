package ex66;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * A show that combines between two and five other shows. This is the composite
 * participant of the Composite design pattern from Exercises 1-5.
 *
 * <p>Exercise 6.6 adds the possibility to "unpack" a composite without
 * changing the {@link Show} interface: only this class implements
 * {@link Iterable Show}, so clients can iterate over the sub-shows of a
 * composite with a for-each loop. Leaf shows such as {@link Movie} and
 * {@link Concert} are not iterable, which avoids forcing them to implement
 * behavior that makes no sense for them.
 */
public class CompositeShow implements Show, Iterable<Show> {

    private List<Show> aShows = new ArrayList<>();

    /**
     * @param pShows The shows to combine, between two and five inclusively.
     */
    public CompositeShow(Show... pShows) {
        if (pShows.length < 2 || pShows.length > 5) {
            throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
        }
        aShows.addAll(Arrays.asList(pShows));
    }

    /**
     * @return An iterator over the sub-shows of this composite, in insertion
     *         order. The internal list is wrapped in an unmodifiable view so
     *         that elements cannot be deleted through the iterator's
     *         {@code remove} method, which preserves encapsulation.
     */
    @Override
    public Iterator<Show> iterator() {
        return Collections.unmodifiableList(aShows).iterator();
    }

    @Override
    public String description() {
        StringJoiner description = new StringJoiner("; ", "[", "]");
        for (Show show : aShows) {
            description.add(show.description());
        }
        return description.toString();
    }

    @Override
    public int runningTime() {
        int time = 0;
        for (Show show : aShows) {
            time += show.runningTime();
        }
        return time;
    }
}
