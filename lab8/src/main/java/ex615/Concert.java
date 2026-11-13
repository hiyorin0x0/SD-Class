package ex615;

import java.util.Objects;

/**
 * Represents a concert performance.
 */
public class Concert implements Show {
    private final String aTitle;
    private final String aPerformer;
    private final int aRunningTime;

    public Concert(String pTitle, String pPerformer, int pTime) {
        aTitle = Objects.requireNonNull(pTitle);
        aPerformer = Objects.requireNonNull(pPerformer);
        aRunningTime = pTime;
    }

    @Override
    public String description() {
        return String.format("%s by %s", aTitle, aPerformer);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return aRunningTime == concert.aRunningTime && aTitle.equals(concert.aTitle) && aPerformer.equals(concert.aPerformer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aTitle, aPerformer, aRunningTime);
    }
}
