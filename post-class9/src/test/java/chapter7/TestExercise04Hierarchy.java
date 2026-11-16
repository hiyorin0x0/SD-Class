package chapter7;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

/**
 * Verifies the class hierarchy, encapsulation rules, and composite/decorator
 * integration modeled in Chapter 7 Exercise 4 (building on Exercises 1-3 and 5).
 */
class TestExercise04Hierarchy {

    @Test
    void testTypeHierarchyMatchesExercise4Diagram() {
        assertTrue(Show.class.isInterface());
        assertTrue(Modifier.isAbstract(AbstractShow.class.getModifiers()));
        assertTrue(Show.class.isAssignableFrom(AbstractShow.class));

        // Leaf hierarchy extends AbstractShow
        assertEquals(AbstractShow.class, Concert.class.getSuperclass());
        assertEquals(AbstractShow.class, Movie.class.getSuperclass());
        assertEquals(Concert.class, SponsoredConcert.class.getSuperclass());

        // Composite & Decorator implement Show directly and do NOT extend AbstractShow
        assertTrue(Show.class.isAssignableFrom(CompositeShow.class));
        assertTrue(Show.class.isAssignableFrom(IntroducedShow.class));
        assertEquals(Object.class, CompositeShow.class.getSuperclass());
        assertEquals(Object.class, IntroducedShow.class.getSuperclass());
        assertFalse(AbstractShow.class.isAssignableFrom(CompositeShow.class));
        assertFalse(AbstractShow.class.isAssignableFrom(IntroducedShow.class));
    }

    @Test
    void testEncapsulationAndProtectedContracts() throws Exception {
        Field titleField = AbstractShow.class.getDeclaredField("aTitle");
        Field timeField = AbstractShow.class.getDeclaredField("aTime");
        assertTrue(Modifier.isPrivate(titleField.getModifiers()));
        assertTrue(Modifier.isFinal(titleField.getModifiers()));
        assertTrue(Modifier.isPrivate(timeField.getModifiers()));
        assertTrue(Modifier.isFinal(timeField.getModifiers()));

        Constructor<AbstractShow> ctor =
                AbstractShow.class.getDeclaredConstructor(String.class, int.class);
        assertTrue(Modifier.isProtected(ctor.getModifiers()));

        Field performerField = Concert.class.getDeclaredField("aPerformer");
        assertTrue(Modifier.isPrivate(performerField.getModifiers()));
        assertTrue(Modifier.isFinal(performerField.getModifiers()));

        Method performerGetter = Concert.class.getDeclaredMethod("performer");
        assertTrue(Modifier.isProtected(performerGetter.getModifiers()));

        Method descriptionMethod = AbstractShow.class.getDeclaredMethod("description");
        assertTrue(Modifier.isFinal(descriptionMethod.getModifiers()));

        Method extraMethod = AbstractShow.class.getDeclaredMethod("extra");
        assertTrue(Modifier.isProtected(extraMethod.getModifiers()));
        assertTrue(Modifier.isAbstract(extraMethod.getModifiers()));
    }

    @Test
    void testCompositeAndDecoratorIntegrationThroughShow() {
        Concert concert = new Concert("Concert 1", "Artist X", 100);
        Movie movie1 = new Movie("Movie 1", 2017, 120);
        Movie movie2 = new Movie("Movie 2", 2018, 130);
        SponsoredConcert sponsored = new SponsoredConcert(
                "Gala", "Orchestra", 80, "Civic Bank", 15);

        Show inner = new CompositeShow(
                movie1,
                new IntroducedShow("Speaker 2", 5, movie2));
        Show root = new IntroducedShow(
                "Speaker 1",
                10,
                new CompositeShow(concert, inner, sponsored));

        // Expected total time: 10 + (100 + (120 + (5 + 130)) + (80 + 15)) = 460
        assertEquals(460, root.time());
        assertEquals(
                "[Speaker 1 introduces [Concert 1: by Artist X (100 minutes); "
                        + "[Movie 1: (2017) (120 minutes); "
                        + "[Speaker 2 introduces Movie 2: (2018) (130 minutes)]]; "
                        + "Gala: by Orchestra sponsored by Civic Bank (95 minutes)]]",
                root.description());
    }

    @Test
    void testCompositeShowEnforcesTwoToFiveShows() {
        Movie m1 = new Movie("M1", 2020, 90);
        Movie m2 = new Movie("M2", 2021, 95);
        assertThrows(IllegalArgumentException.class, () -> new CompositeShow(m1));
        assertThrows(IllegalArgumentException.class,
                () -> new CompositeShow(m1, m2, m1, m2, m1, m2));
        assertDoesNotThrow(() -> new CompositeShow(m1, m2));
    }
}
