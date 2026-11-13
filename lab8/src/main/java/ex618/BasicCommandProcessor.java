package ex618;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Standard implementation of CommandProcessor using two history stacks.
 */
public class BasicCommandProcessor implements CommandProcessor {

    private final List<UndoableCommand> aExecutedCommands = new ArrayList<>();
    private final List<UndoableCommand> aUndoneCommands = new ArrayList<>();

    @Override
    public void consume(UndoableCommand pCommand) {
        Objects.requireNonNull(pCommand);
        pCommand.execute();
        aExecutedCommands.add(pCommand);
    }

    @Override
    public void undoLast() {
        assert !aExecutedCommands.isEmpty() : "No command to undo";
        UndoableCommand command = aExecutedCommands.remove(aExecutedCommands.size() - 1);
        command.undo();
        aUndoneCommands.add(command);
    }

    @Override
    public void redoLast() {
        assert !aUndoneCommands.isEmpty() : "No command to redo";
        UndoableCommand command = aUndoneCommands.remove(aUndoneCommands.size() - 1);
        consume(command);
    }

    @Override
    public boolean canUndo() {
        return !aExecutedCommands.isEmpty();
    }

    @Override
    public boolean canRedo() {
        return !aUndoneCommands.isEmpty();
    }
}
