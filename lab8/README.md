# Chapter 6, Exercises 6.15 - 6.18 — Command Pattern, Undo/Redo & Law of Demeter

This project reconstructs and solves the progression of exercises 6.15 through 6.18 from [DesignBook Chapter 6](https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter6.md) (Martin P. Robillard).

The exercises illustrate the evolution of an object-oriented transaction system using the **Command Pattern**:

- `ex615` — **Exercise 6.15**: Commands are made undoable by extending `Command` with `UndoableCommand` (`undo()`). Each command manages state snapshotting (e.g., `previousShows` for clear, previous show instance for remove).
- `ex616` — **Exercise 6.16**: Introduction of `CommandProcessor` (Invoker) to manage command execution and history stack. Adheres to the **Law of Demeter** by exposing only `consume(cmd)` and `undoLast()`, encapsulating internal stack operations.
- `ex617` — **Exercise 6.17**: Extended `CommandProcessor` supporting **Redo** using two stacks (`aExecutedCommands` and `aUndoneCommands`).
- `ex618` — **Exercise 6.18**: Encapsulating `CommandProcessor` inside `Program`. Client uses familiar high-level methods (`add`, `remove`, `clear`, `undoLast`, `redoLast`). Avoids becoming a **God Class** through **Delegation** and **Dependency Injection** via `interface CommandProcessor`.

## Project Structure

```
lab8/
├── pom.xml
├── README.md
├── BaoCao_ThucHanh_DesignBook_6.15-6.18.docx
└── src/
    ├── main/java/
    │   ├── ex615/   # Exercise 6.15: UndoableCommand
    │   ├── ex616/   # Exercise 6.16: CommandProcessor & Law of Demeter
    │   ├── ex617/   # Exercise 6.17: Redo Support
    │   └── ex618/   # Exercise 6.18: Encapsulation & God Class Avoidance
    └── test/java/
        ├── ex615/   # Tests for undoing add, remove, clear
        ├── ex616/   # Tests for CommandProcessor and Law of Demeter
        ├── ex617/   # Tests for Redo workflow
        └── ex618/   # Tests for Program facade and DI
```

## Running Tests

With JDK 17 or later and Maven installed:

```shell
mvn test
```
