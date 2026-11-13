package ex617;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRedoCommandProcessor {

    private Program program;
    private CommandProcessor processor;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        program = new Program();
        processor = new CommandProcessor();
        movie1 = new Movie("Interstellar", 2014, 169);
        movie2 = new Movie("Dunkirk", 2017, 106);
    }

    @Test
    void testUndoAndRedo() {
        processor.consume(program.createAddCommand(movie1, Program.Day.TUESDAY));
        assertEquals(movie1, program.get(Program.Day.TUESDAY));
        assertFalse(processor.canRedo());

        processor.undoLast();
        assertTrue(program.isNullShow(Program.Day.TUESDAY));
        assertTrue(processor.canRedo());

        processor.redoLast();
        assertEquals(movie1, program.get(Program.Day.TUESDAY));
        assertFalse(processor.canRedo());
    }

    @Test
    void testMultipleUndosAndRedos() {
        processor.consume(program.createAddCommand(movie1, Program.Day.MONDAY));
        processor.consume(program.createAddCommand(movie2, Program.Day.WEDNESDAY));

        processor.undoLast(); // undo add movie2
        processor.undoLast(); // undo add movie1

        assertTrue(program.isNullShow(Program.Day.MONDAY));
        assertTrue(program.isNullShow(Program.Day.WEDNESDAY));

        processor.redoLast(); // redo add movie1
        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertTrue(program.isNullShow(Program.Day.WEDNESDAY));

        processor.redoLast(); // redo add movie2
        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertEquals(movie2, program.get(Program.Day.WEDNESDAY));
    }
}
