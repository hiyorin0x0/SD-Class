package ex615;

import java.util.EnumMap;
import java.util.Objects;

/**
 * Manages weekly shows with undoable command factory methods (Exercise 6.15).
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

    public Program() {
        clear();
    }

    public UndoableCommand createAddCommand(Show pShow, Day pDay) {
        Objects.requireNonNull(pShow);
        Objects.requireNonNull(pDay);
        return new UndoableCommand() {
            @Override
            public void execute() {
                add(pShow, pDay);
            }

            @Override
            public void undo() {
                remove(pDay);
            }
        };
    }

    public UndoableCommand createRemoveCommand(Day pDay) {
        Objects.requireNonNull(pDay);
        return new UndoableCommand() {
            private Show show = aShows.get(pDay);

            @Override
            public void execute() {
                show = aShows.get(pDay);
                remove(pDay);
            }

            @Override
            public void undo() {
                add(show, pDay);
            }
        };
    }

    public UndoableCommand createClearCommand() {
        return new UndoableCommand() {
            private EnumMap<Day, Show> previousShows = new EnumMap<>(aShows);

            @Override
            public void execute() {
                previousShows = new EnumMap<>(aShows);
                clear();
            }

            @Override
            public void undo() {
                aShows.putAll(previousShows);
            }
        };
    }

    private void clear() {
        for (Day day : Day.values()) {
            aShows.put(day, NULL_SHOW);
        }
    }

    private void add(Show pShow, Day pDay) {
        assert pShow != null && pDay != null;
        aShows.put(pDay, pShow);
    }

    private void remove(Day pDay) {
        assert pDay != null;
        aShows.put(pDay, NULL_SHOW);
    }

    public Show get(Day pDay) {
        Objects.requireNonNull(pDay);
        return aShows.get(pDay);
    }

    public boolean isNullShow(Day pDay) {
        return NULL_SHOW.equals(get(pDay));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Day day : aShows.keySet()) {
            result.append(String.format("%9s: %s%n", day.name(), aShows.get(day).description()));
        }
        return result.toString();
    }
}
