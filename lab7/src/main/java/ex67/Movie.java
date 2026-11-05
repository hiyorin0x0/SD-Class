package ex67;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a show that consists of the screening of a single movie.
 *
 * <p>Because {@link Show} now extends {@code Iterable<Show>}, this leaf class
 * must also provide {@link #iterator()}. A leaf yields itself exactly once, so
 * iterating over any show uniformly produces its elementary components.
 */
public class Movie implements Show {

    private String aTitle;
    private int aYear;
    private int aRunningTime;

    /**
     * @param pTitle The title of the movie
     * @param pYear  The release year of the movie
     * @param pTime  The running time of the movie
     */
    public Movie(String pTitle, int pYear, int pTime) {
        aTitle = pTitle;
        aYear = pYear;
        aRunningTime = pTime;
    }

    @Override
    public Iterator<Show> iterator() {
        return List.<Show>of(this).iterator();
    }

    @Override
    public String description() {
        return String.format("%s (%d)", aTitle, aYear);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }
}
