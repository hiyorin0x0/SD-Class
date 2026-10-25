# Chapter 5 — Exercises 5.8, 5.9, 5.10

This Maven project implements the `PokerHand.isStraightFlush()` predicate and the JUnit 5 test suite requested in Exercises 5.8–5.10 of [DesignBook, Chapter 5](https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter5.md).

## Design choices

- `Rank` is ordered Ace, Two, …, King. This models the Ace-low convention from the related Chapter 3 exercise; Ace does not wrap around after King.
- The constructor sorts the five cards by rank, so the predicate only needs to compare adjacent ranks and suits.
- `isStraightFlush()` is private in the exercise. The tests use Java reflection to invoke it directly without changing the exercise's visibility.
- Exercise 5.8 enumerates all 9 consecutive rank sequences in each of 4 suits: 36 positive hands. This is not exhaustive over every five-card hand.
- Exercise 5.9 demonstrates statement coverage with two invocations. Exercise 5.10 demonstrates branch outcomes with three invocations: valid, suit mismatch, and rank gap.

## Run

With JDK 17 or later and Maven installed, run from this directory:

```shell
mvn test
```

JUnit Jupiter and the Maven Surefire plugin are declared in `pom.xml`.
