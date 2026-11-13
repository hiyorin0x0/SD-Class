package ex618;

import java.util.EnumMap;
import java.util.Objects;

/**
 * Program that encapsulates command processing within its public interface (Exercise 6.18).
 *
 * Avoids becoming a God Class by:
 * 1. Delegation: transaction handling is delegated to an aggregated CommandProcessor.
 * 2. Dependency Inversion / Injection: Program depends on the CommandProcessor interface,
 *    injected via constructor.
 * 3. Encapsulation: Factory methods creating commands are private to Program.
 */
public class Program {

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private static final Show NULL_SHOW = createNullShow();

    private static Show createNullShow() {
        return new Show() {
            @Override
            public String description() { return ""; }
            @Override
            public int runningTime() { return 0; }
            @Override
            public boolean equals(Object o) {
                return o != null && o.getClass() == this.getClass();
            }
            @Override
            public int hashCode() { return 0; }
        };
    }

    private final EnumMap<Day, Show> aShows = new EnumMap<>(Day.class);
    private final CommandProcessor aProcessor;

    /**
     * Constructs a Program with an injected CommandProcessor.
     */
    public Program(CommandProcessor pProcessor) {
        Objects.requireNonNull(pProcessor);
        this.aProcessor = pProcessor;
        clearInternal();
    }

    /**
     * Default constructor using BasicCommandProcessor.
     */
    public Program() {
        this(new BasicCommandProcessor());
    }

    public void add(Show pShow, Day pDay) {
        aProcessor.consume(createAddCommand(pShow, pDay));
    }

    public void remove(Day pDay) {
        aProcessor.consume(createRemoveCommand(pDay));
    }

    public void clear() {
        aProcessor.consume(createClearCommand());
    }

    public void undoLast() {
        aProcessor.undoLast();
    }

    public void redoLast() {
        aProcessor.redoLast();
    }

    public Show get(Day pDay) {
        Objects.requireNonNull(pDay);
        return aShows.get(pDay);
    }

    public boolean isNullShow(Day pDay) {
        return NULL_SHOW.equals(get(pDay));
    }

    private UndoableCommand createAddCommand(Show pShow, Day pDay) {
        Objects.requireNonNull(pShow);
        Objects.requireNonNull(pDay);
        return new UndoableCommand() {
            @Override
            public void execute() {
                addInternal(pShow, pDay);
            }
            @Override
            public void undo() {
                removeInternal(pDay);
            }
        };
    }

    private UndoableCommand createRemoveCommand(Day pDay) {
        Objects.requireNonNull(pDay);
        return new UndoableCommand() {
            private Show show = aShows.get(pDay);
            @Override
            public void execute() {
                show = aShows.get(pDay);
                removeInternal(pDay);
            }
            @Override
            public void undo() {
                addInternal(show, pDay);
            }
        };
    }

    private UndoableCommand createClearCommand() {
        return new UndoableCommand() {
            private EnumMap<Day, Show> previousShows = new EnumMap<>(aShows);
            @Override
            public void execute() {
                previousShows = new EnumMap<>(aShows);
                clearInternal();
            }
            @Override
            public void undo() {
                aShows.putAll(previousShows);
            }
        };
    }

    private void clearInternal() {
        for (Day day : Day.values()) {
            aShows.put(day, NULL_SHOW);
        }
    }

    private void addInternal(Show pShow, Day pDay) {
        aShows.put(pDay, pShow);
    }

    private void removeInternal(Day pDay) {
        aShows.put(pDay, NULL_SHOW);
    }
}
