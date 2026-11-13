package ex616;

import java.util.Objects;

/**
 * Represents a movie screening show.
 */
public class Movie implements Show {
    private final String aTitle;
    private final int aYear;
    private final int aRunningTime;

    public Movie(String pTitle, int pYear, int pTime) {
        aTitle = Objects.requireNonNull(pTitle);
        aYear = pYear;
        aRunningTime = pTime;
    }

    @Override
    public String description() {
        return String.format("%s (%d)", aTitle, aYear);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return aYear == movie.aYear && aRunningTime == movie.aRunningTime && aTitle.equals(movie.aTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aTitle, aYear, aRunningTime);
    }
}
