package chapter7;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Verifies the Template Method output, method-binding resolution, and exact call
 * sequence modeled in Chapter 7 Exercise 6 for {@link SponsoredConcert#description()}.
 */
class TestExercise06Sequence {

    @Test
    void testSponsoredConcertDescriptionAndTotalTime() {
        SponsoredConcert concert = new SponsoredConcert(
                "Amazing Concert", "Anonymous Artist", 100, "McGill University", 10);

        assertEquals(110, concert.time());
        assertEquals("Amazing Concert", concert.title());
        assertEquals(
                "Amazing Concert: by Anonymous Artist sponsored by McGill University (110 minutes)",
                concert.description());
    }

    @Test
    void testMethodDispatchAndSuperBindingTargets() throws Exception {
        // description() is inherited from AbstractShow and locked final
        Method descriptionMethod = SponsoredConcert.class.getMethod("description");
        assertEquals(AbstractShow.class, descriptionMethod.getDeclaringClass());
        assertTrue(Modifier.isFinal(descriptionMethod.getModifiers()));

        // title() is inherited from AbstractShow -> AbstractShow#title()
        Method titleMethod = SponsoredConcert.class.getMethod("title");
        assertEquals(AbstractShow.class, titleMethod.getDeclaringClass());

        // extra() is overridden in SponsoredConcert -> SponsoredConcert#extra()
        Method sponsoredExtra = SponsoredConcert.class.getDeclaredMethod("extra");
        assertEquals(SponsoredConcert.class, sponsoredExtra.getDeclaringClass());

        // super.extra() in SponsoredConcert resolves statically to Concert#extra()
        Method concertExtra = Concert.class.getDeclaredMethod("extra");
        assertEquals(Concert.class, concertExtra.getDeclaringClass());

        // time() is overridden in SponsoredConcert -> SponsoredConcert#time()
        Method sponsoredTime = SponsoredConcert.class.getMethod("time");
        assertEquals(SponsoredConcert.class, sponsoredTime.getDeclaringClass());

        // super.time() in SponsoredConcert goes up to Concert, which inherits AbstractShow#time()
        Method concertTime = Concert.class.getMethod("time");
        assertEquals(AbstractShow.class, concertTime.getDeclaringClass());
    }

    @Test
    void testSequenceOfSelfCallsDuringDescription() {
        List<String> callLog = new ArrayList<>();

        SponsoredConcert tracedConcert = new SponsoredConcert(
                "Amazing Concert", "Anonymous Artist", 100, "McGill University", 10) {
            @Override
            public String title() {
                callLog.add("AbstractShow#title()");
                return super.title();
            }

            @Override
            protected String extra() {
                callLog.add("SponsoredConcert#extra()");
                String result = super.extra();
                callLog.add("<- returned from SponsoredConcert#extra() [via Concert#extra()]");
                return result;
            }

            @Override
            public int time() {
                callLog.add("SponsoredConcert#time()");
                int total = super.time();
                callLog.add("<- returned from SponsoredConcert#time() [via AbstractShow#time()]");
                return total;
            }
        };

        String description = tracedConcert.description();

        assertEquals(
                "Amazing Concert: by Anonymous Artist sponsored by McGill University (110 minutes)",
                description);
        assertEquals(
                List.of(
                        "AbstractShow#title()",
                        "SponsoredConcert#extra()",
                        "<- returned from SponsoredConcert#extra() [via Concert#extra()]",
                        "SponsoredConcert#time()",
                        "<- returned from SponsoredConcert#time() [via AbstractShow#time()]"),
                callLog);
    }

    @Test
    void testMovieAndConcertAlsoFollowTemplateMethod() {
        Show movie = new Movie("Inception", 2010, 148);
        Show concert = new Concert("Jazz Night", "Miles Quartet", 90);

        assertEquals("Inception: (2010) (148 minutes)", movie.description());
        assertEquals("Jazz Night: by Miles Quartet (90 minutes)", concert.description());
    }
}
