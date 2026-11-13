package ex615;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUndoableCommands {

    private Program program;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        program = new Program();
        movie1 = new Movie("Movie 1", 2000, 120);
        movie2 = new Movie("Movie 2", 2010, 90);
    }

    @Test
    void testAddCommandAndUndo() {
        UndoableCommand cmd = program.createAddCommand(movie1, Program.Day.MONDAY);
        cmd.execute();
        assertEquals(movie1, program.get(Program.Day.MONDAY));

        cmd.undo();
        assertTrue(program.isNullShow(Program.Day.MONDAY));

        cmd.execute();
        assertEquals(movie1, program.get(Program.Day.MONDAY));
    }

    @Test
    void testRemoveCommandAndUndo() {
        program.createAddCommand(movie1, Program.Day.MONDAY).execute();
        program.createAddCommand(movie2, Program.Day.WEDNESDAY).execute();

        UndoableCommand removeCmd = program.createRemoveCommand(Program.Day.MONDAY);
        removeCmd.execute();
        assertTrue(program.isNullShow(Program.Day.MONDAY));
        assertEquals(movie2, program.get(Program.Day.WEDNESDAY));

        removeCmd.undo();
        assertEquals(movie1, program.get(Program.Day.MONDAY));
    }

    @Test
    void testClearCommandAndUndo() {
        program.createAddCommand(movie1, Program.Day.MONDAY).execute();
        program.createAddCommand(movie2, Program.Day.WEDNESDAY).execute();

        UndoableCommand clearCmd = program.createClearCommand();
        clearCmd.execute();
        assertTrue(program.isNullShow(Program.Day.MONDAY));
        assertTrue(program.isNullShow(Program.Day.WEDNESDAY));

        clearCmd.undo();
        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertEquals(movie2, program.get(Program.Day.WEDNESDAY));
    }
}
