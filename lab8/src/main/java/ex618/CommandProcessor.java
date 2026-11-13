package ex618;

/**
 * Interface abstraction for a command processor (Exercise 6.18).
 * Decouples class Program from concrete command execution strategies.
 */
public interface CommandProcessor {
    void consume(UndoableCommand pCommand);
    void undoLast();
    void redoLast();
    boolean canUndo();
    boolean canRedo();
}
