package ex67;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a show that consists of a live performance of a single piece.
 *
 * <p>Because {@link Show} now extends {@code Iterable<Show>}, this leaf class
 * must also provide {@link #iterator()}. A leaf yields itself exactly once, so
 * iterating over any show uniformly produces its elementary components.
 */
public class Concert implements Show {

    private String aTitle;
    private String aPerformer;
    private int aRunningTime;

    /**
     * @param pTitle     The title of the concert
     * @param pPerformer The name of the artist or orchestra performing
     * @param pTime      The running time of the concert
     */
    public Concert(String pTitle, String pPerformer, int pTime) {
        aTitle = pTitle;
        aPerformer = pPerformer;
        aRunningTime = pTime;
    }

    @Override
    public Iterator<Show> iterator() {
        return List.<Show>of(this).iterator();
    }

    @Override
    public String description() {
        return String.format("%s by %s", aTitle, aPerformer);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }
}
