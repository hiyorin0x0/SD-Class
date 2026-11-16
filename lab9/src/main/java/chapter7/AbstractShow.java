package chapter7;

import java.util.Objects;

/**
 * Abstract base class factoring out the common stored fields ({@code aTitle} and
 * {@code aTime}) shared by concrete leaf shows such as {@link Concert} and {@link Movie}
 * (Exercise 3), and enforcing a uniform description format through the Template Method
 * design pattern (Exercise 5).
 *
 * <p>Per Exercise 4, structural wrappers ({@link CompositeShow} and {@link IntroducedShow})
 * implement {@link Show} directly rather than extending this class because they compute
 * their running time and description dynamically via delegation rather than storing
 * intrinsic title/time state.
 */
public abstract class AbstractShow implements Show {

    private final String aTitle;
    private final int aTime;

    /**
     * Protected constructor callable only by subclasses.
     *
     * @param pTitle The title of the show (must be non-null)
     * @param pTime  The base running time in minutes (must be non-negative)
     */
    protected AbstractShow(String pTitle, int pTime) {
        aTitle = Objects.requireNonNull(pTitle, "Title must not be null");
        if (pTime < 0) {
            throw new IllegalArgumentException("Time must be non-negative: " + pTime);
        }
        aTime = pTime;
    }

    @Override
    public String title() {
        return aTitle;
    }

    @Override
    public int time() {
        return aTime;
    }

    /**
     * Template Method (Exercise 5) locking the description skeleton:
     * {@code $TITLE$: $EXTRA$ ($TIME$ minutes)}.
     *
     * <p>Declared {@code final} so subclasses cannot alter the outer format;
     * subclasses customize only the middle segment via {@link #extra()}.
     *
     * @return Formatted show description.
     */
    @Override
    public final String description() {
        return String.format("%s: %s (%d minutes)", title(), extra(), time());
    }

    /**
     * Primitive operation invoked by {@link #description()} to supply
     * subclass-specific details.
     *
     * @return Subclass-specific middle fragment of the description.
     */
    protected abstract String extra();
}
