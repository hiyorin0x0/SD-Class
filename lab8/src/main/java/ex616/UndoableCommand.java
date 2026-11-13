package ex616;

/**
 * Command that supports undoing its execution.
 */
public interface UndoableCommand extends Command {
    void undo();
}
