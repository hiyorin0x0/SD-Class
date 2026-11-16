package chapter7;

import java.util.Objects;

/**
 * Represents a concert that includes a sponsor advertisement segment (Exercises 1, 5, and 6).
 *
 * <p>In Exercise 6, invoking {@link #description()} on a {@code SponsoredConcert}
 * executes the inherited {@code final} template method {@code AbstractShow#description()},
 * triggering the exact sequence of self-calls:
 * <ol>
 *   <li>{@code AbstractShow#title()}</li>
 *   <li>{@code SponsoredConcert#extra()} -&gt; {@code Concert#extra()}</li>
 *   <li>{@code SponsoredConcert#time()} -&gt; {@code AbstractShow#time()}</li>
 * </ol>
 */
public class SponsoredConcert extends Concert {

    private final String aSponsor;
    private final int aSponsorTime;

    /**
     * @param pTitle       The title of the concert
     * @param pPerformer   The performer of the concert
     * @param pTime        The base running time of the concert in minutes
     * @param pSponsorName The sponsor name
     * @param pSponsorTime Additional minutes allocated to the sponsor
     */
    public SponsoredConcert(String pTitle, String pPerformer, int pTime,
            String pSponsorName, int pSponsorTime) {
        super(pTitle, pPerformer, pTime);
        aSponsor = Objects.requireNonNull(pSponsorName, "Sponsor name must not be null");
        if (pSponsorTime < 0) {
            throw new IllegalArgumentException("Sponsor time must be non-negative: " + pSponsorTime);
        }
        aSponsorTime = pSponsorTime;
    }

    /**
     * @return The sponsor name.
     */
    public String sponsor() {
        return aSponsor;
    }

    /**
     * @return The minutes allocated to the sponsor.
     */
    public int sponsorTime() {
        return aSponsorTime;
    }

    @Override
    protected String extra() {
        return super.extra() + " sponsored by " + aSponsor;
    }

    @Override
    public int time() {
        return super.time() + aSponsorTime;
    }
}
