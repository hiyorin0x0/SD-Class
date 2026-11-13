package ex616;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Invoker abstraction that allows clients to execute commands, store executed commands,
 * and undo the last executed command (Exercise 6.16).
 *
 * Adheres strictly to the Law of Demeter:
 * Clients interact through high-level consume() and undoLast() methods.
 * Internal history structure is completely encapsulated.
 */
public class CommandProcessor {

    private final List<UndoableCommand> aCommands = new ArrayList<>();

    public void consume(UndoableCommand pCommand) {
        Objects.requireNonNull(pCommand);
        pCommand.execute();
        aCommands.add(pCommand);
    }

    public void undoLast() {
        assert !aCommands.isEmpty() : "No command to undo";
        UndoableCommand command = aCommands.remove(aCommands.size() - 1);
        command.undo();
    }

    public boolean canUndo() {
        return !aCommands.isEmpty();
    }
}
