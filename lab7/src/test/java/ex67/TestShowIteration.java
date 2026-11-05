package ex67;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for Exercise 6.7: the Show interface itself is modified to extend
 * Iterable, so any show can be unpacked uniformly, with no downcast.
 */
class TestShowIteration {

    private final Concert aConcert = new Concert("Concert 1", "Artist X", 100);
    private final Movie aMovie1 = new Movie("Movie 1", 2017, 120);
    private final Movie aMovie2 = new Movie("Movie 2", 2018, 130);

    private CompositeShow nestedShow() {
        return new CompositeShow(aConcert, new CompositeShow(aMovie1, aMovie2));
    }

    @Test
    void leafMovieIteratesOverItself() {
        Movie movie = aMovie1;

        List<Show> elements = new ArrayList<>();
        for (Show element : movie) {
            elements.add(element);
        }

        assertEquals(1, elements.size());
        assertSame(movie, elements.get(0));
    }

    @Test
    void leafConcertIteratesOverItself() {
        List<Show> elements = new ArrayList<>();
        for (Show element : aConcert) {
            elements.add(element);
        }

        assertEquals(1, elements.size());
        assertSame(aConcert, elements.get(0));
    }

    @Test
    void compositeIteratesOverChildrenInInsertionOrder() {
        CompositeShow show = nestedShow();

        List<String> descriptions = new ArrayList<>();
        for (Show subshow : show) {
            descriptions.add(subshow.description());
        }

        assertEquals(List.of("Concert 1 by Artist X", "[Movie 1 (2017); Movie 2 (2018)]"), descriptions);
    }

    @Test
    void nestedShowsUnpackUniformlyWithoutDowncasts() {
        Show show = nestedShow();

        List<String> descriptions = new ArrayList<>();
        // No instanceof, no cast: the for-each works on every Show because the
        // interface itself now provides the iteration capability.
        for (Show firstLevel : show) {
            for (Show element : firstLevel) {
                descriptions.add(element.description());
            }
        }

        assertEquals(List.of("Concert 1 by Artist X", "Movie 1 (2017)", "Movie 2 (2018)"), descriptions);
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
