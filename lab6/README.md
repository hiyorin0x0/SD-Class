# Exercise 5.11 — BiCardSource branch coverage

This project reconstructs the `CardSource` interface and `BiCardSource` shown in [DesignBook Chapter 5, Exercise 5.11](https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter5.md), then tests every public operation with controllable stubs.

## Coverage plan

- `draw()` has two paths, selected by whether source 1 is empty. Two tests check both returned card identities and which stub receives `draw()`.
- `isEmpty()` combines both source states with `&&`. Three tests cover short-circuit false (source 1 is not empty), false after checking source 2, and true when both are empty.
- `StubCardSource` records `draw()` and `isEmpty()` calls so tests can check both the result and interactions.

The exercise's comment says `draw()` draws from source 1 when it is not empty and source 2 otherwise. The code listing routes those cases in the opposite order. `BiCardSource` here follows the code listing so the tests verify the supplied implementation and cover its branches; the mismatch is described in the report. To follow the comment as the intended behavior, swap the two return expressions in `draw()` and update the two draw assertions accordingly.

## Run

With JDK 17 or later and Maven installed, run from this folder:

```shell
mvn test
```