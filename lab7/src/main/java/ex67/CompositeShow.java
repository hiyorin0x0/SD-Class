package ex67;

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
 * <p>In the Exercise 6.7 variant, {@link Show} itself extends
 * {@code Iterable<Show>}, so this class only needs to provide its part of the
 * contract: an iterator over its sub-shows. The internal list is wrapped in an
 * unmodifiable view so that elements cannot be deleted through the iterator's
 * {@code remove} method.
 */
public class CompositeShow implements Show {

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
     *         order.
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
