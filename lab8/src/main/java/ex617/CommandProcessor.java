package ex617;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CommandProcessor with Redo capability (Exercise 6.17).
 * Maintains two stacks: aExecutedCommands and aUndoneCommands.
 */
public class CommandProcessor {

    private final List<UndoableCommand> aExecutedCommands = new ArrayList<>();
    private final List<UndoableCommand> aUndoneCommands = new ArrayList<>();

    public void consume(UndoableCommand pCommand) {
        Objects.requireNonNull(pCommand);
        pCommand.execute();
        aExecutedCommands.add(pCommand);
    }

    public void undoLast() {
        assert !aExecutedCommands.isEmpty() : "No command to undo";
        UndoableCommand command = aExecutedCommands.remove(aExecutedCommands.size() - 1);
        command.undo();
        aUndoneCommands.add(command);
    }

    public void redoLast() {
        assert !aUndoneCommands.isEmpty() : "No command to redo";
        UndoableCommand command = aUndoneCommands.remove(aUndoneCommands.size() - 1);
        consume(command);
    }

    public boolean canUndo() {
        return !aExecutedCommands.isEmpty();
    }

    public boolean canRedo() {
        return !aUndoneCommands.isEmpty();
    }
}
