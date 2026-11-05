package ex66;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for Exercise 6.6: the composite can be unpacked through an iterator
 * obtained from CompositeShow only, without any change to the Show interface.
 */
class TestCompositeShow {

    private final Concert aConcert = new Concert("Concert 1", "Artist X", 100);
    private final Movie aMovie1 = new Movie("Movie 1", 2017, 120);
    private final Movie aMovie2 = new Movie("Movie 2", 2018, 130);

    private CompositeShow nestedShow() {
        // A concert followed by two movies grouped in their own composite,
        // as in the object graph of Exercises 1-5.
        return new CompositeShow(aConcert, new CompositeShow(aMovie1, aMovie2));
    }

    @Test
    void iteratorYieldsDirectChildrenInInsertionOrder() {
        CompositeShow show = nestedShow();

        List<String> descriptions = new ArrayList<>();
        for (Show subshow : show) {
            descriptions.add(subshow.description());
        }

        assertEquals(List.of("Concert 1 by Artist X", "[Movie 1 (2017); Movie 2 (2018)]"), descriptions);
    }

    @Test
    void unpackingNestedCompositesRequiresDowncasts() {
        Show show = nestedShow();

        List<String> descriptions = new ArrayList<>();
        // Because Show does not declare iterator(), the client must know the
        // concrete type before it can unpack anything.
        if (show instanceof CompositeShow root) {
            for (Show subshow : root) {
                if (subshow instanceof CompositeShow inner) {
                    for (Show leaf : inner) {
                        descriptions.add(leaf.description());
                    }
                } else {
                    descriptions.add(subshow.description());
                }
            }
        }

        assertEquals(List.of("Concert 1 by Artist X", "Movie 1 (2017)", "Movie 2 (2018)"), descriptions);
    }

    @Test
    void leafShowsAreNotIterable() {
        // The design point of Exercise 6.6: leaves are not forced to support
        // iteration, so a Show reference cannot always be unpacked.
        assertFalse(aMovie1 instanceof Iterable);
        assertFalse(aConcert instanceof Iterable);
    }

    @Test
    void iteratorDoesNotSupportRemove() {
        CompositeShow show = new CompositeShow(aConcert, aMovie1);

        Iterator<Show> iterator = show.iterator();

        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
    }

    @Test
    void runningTimeIsSumOfAllChildren() {
        assertEquals(350, nestedShow().runningTime());
    }

    @Test
    void descriptionJoinsChildDescriptions() {
        assertEquals("[Concert 1 by Artist X; [Movie 1 (2017); Movie 2 (2018)]]",
                nestedShow().description());
    }

    @Test
    void constructorRejectsFewerThanTwoShows() {
        assertThrows(IllegalArgumentException.class, () -> new CompositeShow(aConcert));
    }

    @Test
    void constructorRejectsMoreThanFiveShows() {
        Movie third = new Movie("Movie 3", 2019, 90);
        Movie fourth = new Movie("Movie 4", 2020, 95);
        Movie fifth = new Movie("Movie 5", 2021, 100);
        Movie sixth = new Movie("Movie 6", 2022, 105);

        assertThrows(IllegalArgumentException.class, () -> new CompositeShow(aMovie1, aMovie2, third, fourth, fifth, sixth));
    }
}
