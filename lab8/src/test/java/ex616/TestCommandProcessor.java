package ex616;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCommandProcessor {

    private Program program;
    private CommandProcessor processor;
    private Movie movie1;
    private Concert concert;

    @BeforeEach
    void setUp() {
        program = new Program();
        processor = new CommandProcessor();
        movie1 = new Movie("Inception", 2010, 148);
        concert = new Concert("Live Aid", "Queen", 120);
    }

    @Test
    void testConsumeExecutesAndStoresCommand() {
        assertFalse(processor.canUndo());
        processor.consume(program.createAddCommand(movie1, Program.Day.MONDAY));
        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertTrue(processor.canUndo());
    }

    @Test
    void testUndoLast() {
        processor.consume(program.createAddCommand(movie1, Program.Day.MONDAY));
        processor.consume(program.createAddCommand(concert, Program.Day.FRIDAY));

        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertEquals(concert, program.get(Program.Day.FRIDAY));

        processor.undoLast();
        assertTrue(program.isNullShow(Program.Day.FRIDAY));
        assertEquals(movie1, program.get(Program.Day.MONDAY));

        processor.undoLast();
        assertTrue(program.isNullShow(Program.Day.MONDAY));
        assertFalse(processor.canUndo());
    }

    @Test
    void testRespectLawOfDemeter() {
        processor.consume(program.createAddCommand(movie1, Program.Day.SATURDAY));
        processor.undoLast();
        assertTrue(program.isNullShow(Program.Day.SATURDAY));
    }
}
