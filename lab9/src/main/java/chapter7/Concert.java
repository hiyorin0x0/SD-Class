package chapter7;

import java.util.Objects;

/**
 * Represents a live musical concert extending {@link AbstractShow} (Exercises 2, 3, and 5).
 *
 * <p>Per Exercise 2, {@code aPerformer} is strictly {@code private final} rather than
 * {@code protected}, and exposed to subclasses only through {@link #performer()}.
 */
public class Concert extends AbstractShow {

    private final String aPerformer;

    /**
     * @param pTitle     The title of the concert
     * @param pPerformer The artist or ensemble performing
     * @param pTime      The base running time in minutes
     */
    public Concert(String pTitle, String pPerformer, int pTime) {
        super(pTitle, pTime);
        aPerformer = Objects.requireNonNull(pPerformer, "Performer must not be null");
    }

    /**
     * Protected accessor preserving encapsulation of {@code aPerformer} while
     * allowing subclasses (e.g., {@link SponsoredConcert}) to read it.
     *
     * @return The performer name.
     */
    protected String performer() {
        return aPerformer;
    }

    @Override
    protected String extra() {
        return "by " + aPerformer;
    }
}
