package ex66;

/**
 * Represents a show that consists of the screening of a single movie.
 *
 * <p>Reconstructed from DesignBook Chapter 6 exercise code.
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
    public String description() {
        return String.format("%s (%d)", aTitle, aYear);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }
}
