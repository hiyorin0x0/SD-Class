package chapter7;

/**
 * Demonstration entry point illustrating both Exercise 4 (integration of
 * {@link CompositeShow} and {@link IntroducedShow} alongside {@link AbstractShow})
 * and Exercise 6 (calling {@link SponsoredConcert#description()}).
 */
public final class Chapter7Demo {

    private Chapter7Demo() {
    }

    public static void main(String[] args) {
        Concert concert = new Concert("Autumn Symphony", "Montreal Orchestra", 90);
        Movie movie1 = new Movie("Arrival", 2016, 116);
        Movie movie2 = new Movie("Inception", 2010, 148);
        SponsoredConcert sponsored = new SponsoredConcert(
                "Amazing Concert", "Anonymous Artist", 100, "McGill University", 10);

        // Exercise 6: description() executes AbstractShow#description() template method
        System.out.println("=== Exercise 6: SponsoredConcert.description() ===");
        System.out.println(sponsored.description());
        System.out.println("Total time: " + sponsored.time() + " minutes");

        // Exercise 4: CompositeShow & IntroducedShow integrate via Show interface
        Show program = new IntroducedShow(
                "Dr. Martin",
                10,
                new CompositeShow(
                        sponsored,
                        new CompositeShow(
                                movie1,
                                new IntroducedShow("Film Critic", 5, movie2)),
                        concert));

        System.out.println("\n=== Exercise 4: Integrated Show Hierarchy ===");
        System.out.println("Title      : " + program.title());
        System.out.println("Description: " + program.description());
        System.out.println("Total time : " + program.time() + " minutes");
    }
}
