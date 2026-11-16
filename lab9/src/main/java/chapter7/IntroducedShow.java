package chapter7;

import java.util.Objects;

/**
 * Decorator wrapping any {@link Show} to prepend an introduction speech by a speaker.
 *
 * <p>Per Exercise 4, {@code IntroducedShow} implements {@link Show} directly rather than
 * extending {@link AbstractShow}, because it decorates an arbitrary {@link Show} via
 * composition/aggregation and delegates {@link #title()}, {@link #description()}, and
 * {@link #time()} rather than holding standalone title and base-time fields.
 */
public class IntroducedShow implements Show {

    private final Show aShow;
    private final String aSpeaker;
    private final int aSpeakerTime;

    /**
     * @param pSpeaker     Name of the speaker introducing the show
     * @param pSpeakerTime Duration of the introduction speech in minutes
     * @param pShow        The decorated {@link Show}
     */
    public IntroducedShow(String pSpeaker, int pSpeakerTime, Show pShow) {
        aSpeaker = Objects.requireNonNull(pSpeaker, "Speaker must not be null");
        if (pSpeakerTime < 0) {
            throw new IllegalArgumentException("Speaker time must be non-negative: " + pSpeakerTime);
        }
        aSpeakerTime = pSpeakerTime;
        aShow = Objects.requireNonNull(pShow, "Decorated show must not be null");
    }

    /**
     * @return The speaker name.
     */
    public String speaker() {
        return aSpeaker;
    }

    /**
     * @return The duration of the introduction speech in minutes.
     */
    public int speakerTime() {
        return aSpeakerTime;
    }

    /**
     * @return The wrapped show.
     */
    public Show decoratedShow() {
        return aShow;
    }

    @Override
    public String title() {
        return aShow.title();
    }

    @Override
    public String description() {
        return "[" + aSpeaker + " introduces " + aShow.description() + "]";
    }

    @Override
    public int time() {
        return aSpeakerTime + aShow.time();
    }
}
