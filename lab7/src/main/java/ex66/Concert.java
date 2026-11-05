package ex66;

/**
 * Represents a show that consists of a live performance of a single piece.
 *
 * <p>Reconstructed from DesignBook Chapter 6 exercise code.
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
    public String description() {
        return String.format("%s by %s", aTitle, aPerformer);
    }

    @Override
    public int runningTime() {
        return aRunningTime;
    }
}
