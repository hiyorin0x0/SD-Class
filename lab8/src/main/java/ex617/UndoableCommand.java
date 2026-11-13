package ex617;

/**
 * Command that supports undoing its execution.
 */
public interface UndoableCommand extends Command {
    void undo();
}
