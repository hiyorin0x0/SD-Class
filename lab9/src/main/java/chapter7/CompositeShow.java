package chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Composite implementation combining between two and five {@link Show} instances.
 *
 * <p>Per Exercise 4, {@code CompositeShow} implements {@link Show} directly instead of
 * extending {@link AbstractShow}, because it aggregates child {@link Show} objects and
 * computes its {@link #title()}, {@link #description()}, and {@link #time()} dynamically
 * through delegation rather than owning intrinsic {@code aTitle} / {@code aTime} fields.
 */
public class CompositeShow implements Show {

    private static final int MIN_SHOWS = 2;
    private static final int MAX_SHOWS = 5;

    private final List<Show> aShows = new ArrayList<>();

    /**
     * @param pShows Between two and five non-null shows to combine.
     */
    public CompositeShow(Show... pShows) {
        Objects.requireNonNull(pShows, "Shows array must not be null");
        if (pShows.length < MIN_SHOWS || pShows.length > MAX_SHOWS) {
            throw new IllegalArgumentException(
                    "Arguments should be between two and five shows inclusively");
        }
        for (Show show : pShows) {
            aShows.add(Objects.requireNonNull(show, "Child show must not be null"));
        }
    }

    /**
     * @return Unmodifiable view of the child shows in insertion order.
     */
    public List<Show> shows() {
        return Collections.unmodifiableList(aShows);
    }

    @Override
    public String title() {
        StringJoiner joiner = new StringJoiner(", ");
        for (Show show : aShows) {
            joiner.add(show.title());
        }
        return joiner.toString();
    }

    @Override
    public String description() {
        StringJoiner joiner = new StringJoiner("; ", "[", "]");
        for (Show show : aShows) {
            joiner.add(show.description());
        }
        return joiner.toString();
    }

    @Override
    public int time() {
        int totalTime = 0;
        for (Show show : aShows) {
            totalTime += show.time();
        }
        return totalTime;
    }
}
