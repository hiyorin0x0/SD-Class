# Chapter 7, Exercises 4 & 6 — Inheritance, Abstract Classes, Template Method & Call Sequence

This project implements and tests the refactored `Show` type hierarchy from [DesignBook Chapter 7](https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter7.md) (Martin P. Robillard), focusing on the designs modeled in **Exercise 4** and **Exercise 6** (which build upon Exercises 1–3 and 5 plus Chapter 6's `CompositeShow` and `IntroducedShow`).

## Architecture Overview

- **`Show`** (`interface`): Root contract declaring `title()`, `description()`, and `time()`.
- **`AbstractShow`** (`abstract class implements Show`, Ex 3 & Ex 5):
  - Factors out shared `private final` fields `aTitle` and `aTime`, initialized through a `protected` constructor.
  - Implements `title()` and `time()`.
  - Applies the **Template Method** pattern: `public final String description()` fixes the output format `"$TITLE$: %s ($TIME$ minutes)"` and delegates subclass-specific details to `protected abstract String extra()`.
- **`Concert extends AbstractShow`** (Ex 2, 3, 5): Encapsulates `private final String aPerformer` with `protected String performer()` and implements `extra()` as `"by " + aPerformer`.
- **`Movie extends AbstractShow`** (Ex 3, 5): Stores `private final int aYear` and implements `extra()` as `"(year)"`.
- **`SponsoredConcert extends Concert`** (Ex 1, 5, 6): Adds `aSponsor` and `aSponsorTime`, overrides `extra()` (`super.extra() + " sponsored by " + aSponsor`) and `time()` (`super.time() + aSponsorTime`).
- **`CompositeShow implements Show`** (Ex 4): Aggregates 2–5 `Show` objects; implements `Show` directly instead of extending `AbstractShow`.
- **`IntroducedShow implements Show`** (Ex 4): Decorates a `Show` with speaker & speech time; implements `Show` directly instead of extending `AbstractShow`.

## Exercise 6 Call Sequence (`SponsoredConcert#description()`)

Calling `description()` on a `:SponsoredConcert` executes `AbstractShow#description()` and triggers:
1. `AbstractShow#title()`
2. `SponsoredConcert#extra()` → `Concert#extra()` (`super.extra()`)
3. `SponsoredConcert#time()` → `AbstractShow#time()` (`super.time()`)

## Project Structure

```
post-class9/
├── pom.xml
├── README.md
└── src/
    ├── main/java/chapter7/
    │   ├── Show.java
    │   ├── AbstractShow.java
    │   ├── Concert.java
    │   ├── Movie.java
    │   ├── SponsoredConcert.java
    │   ├── CompositeShow.java
    │   ├── IntroducedShow.java
    │   └── Chapter7Demo.java
    └── test/java/chapter7/
        ├── TestExercise04Hierarchy.java
        └── TestExercise06Sequence.java
```

## Running Tests

With JDK 17+ and Maven installed:

```shell
mvn test
```
