/** Combines two card sources using the behavior shown in Exercise 5.11. */
public final class BiCardSource implements CardSource {
    private final CardSource aSource1;
    private final CardSource aSource2;

    public BiCardSource(CardSource pSource1, CardSource pSource2) {
        aSource1 = pSource1;
        aSource2 = pSource2;
    }

    /**
     * This reproduces the exercise listing exactly. The branch routing appears
     * inconsistent with the adjacent comment in the exercise.
     */
    @Override
    public Card draw() {
        if (!aSource1.isEmpty()) {
            return aSource2.draw();
        } else {
            return aSource1.draw();
        }
    }

    @Override
    public boolean isEmpty() {
        return aSource1.isEmpty() && aSource2.isEmpty();
    }
}