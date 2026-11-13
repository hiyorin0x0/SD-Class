package ex618;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestProgramEncapsulatedProcessor {

    private Program program;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        program = new Program();
        movie1 = new Movie("The Dark Knight", 2008, 152);
        movie2 = new Movie("Memento", 2000, 113);
    }

    @Test
    void testDirectProgramMethodsWithUndo() {
        program.add(movie1, Program.Day.MONDAY);
        program.add(movie2, Program.Day.WEDNESDAY);
        assertEquals(movie1, program.get(Program.Day.MONDAY));
        assertEquals(movie2, program.get(Program.Day.WEDNESDAY));

        program.undoLast(); // undo add movie2
        assertTrue(program.isNullShow(Program.Day.WEDNESDAY));
        assertEquals(movie1, program.get(Program.Day.MONDAY));

        program.redoLast(); // redo add movie2
        assertEquals(movie2, program.get(Program.Day.WEDNESDAY));
    }

    @Test
    void testClearAndUndo() {
        program.add(movie1, Program.Day.MONDAY);
        program.clear();
        assertTrue(program.isNullShow(Program.Day.MONDAY));

        program.undoLast();
        assertEquals(movie1, program.get(Program.Day.MONDAY));
    }

    @Test
    void testDependencyInjectionPreventsGodClass() {
        // Proves decoupling: Program works with any custom implementation of CommandProcessor
        final int[] consumeCount = {0};
        CommandProcessor spyProcessor = new BasicCommandProcessor() {
            @Override
            public void consume(UndoableCommand pCommand) {
                consumeCount[0]++;
                super.consume(pCommand);
            }
        };

        Program customProgram = new Program(spyProcessor);
        customProgram.add(movie1, Program.Day.FRIDAY);

        assertEquals(1, consumeCount[0]);
        assertEquals(movie1, customProgram.get(Program.Day.FRIDAY));
    }
}
