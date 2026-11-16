package chapter7;

/**
 * Represents the screening of a single movie extending {@link AbstractShow}
 * (Exercises 3 and 5).
 */
public class Movie extends AbstractShow {

    private final int aYear;

    /**
     * @param pTitle The title of the movie
     * @param pYear  The release year of the movie
     * @param pTime  The running time in minutes
     */
    public Movie(String pTitle, int pYear, int pTime) {
        super(pTitle, pTime);
        aYear = pYear;
    }

    /**
     * @return The release year of the movie.
     */
    public int year() {
        return aYear;
    }

    @Override
    protected String extra() {
        return String.format("(%d)", aYear);
    }
}
