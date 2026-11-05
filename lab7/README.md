# Chapter 6, Exercises 6.6 & 6.7 — Unpacking a composite show with an iterator

This project reconstructs the `Show` hierarchy of [DesignBook Chapter 6](https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter6.md) (`Show`, `Movie`, `Concert`, `CompositeShow`) and implements the same "unpack a composite" feature in the two variants required by Exercises 6 and 7.

The two variants cannot coexist in one type hierarchy, so each lives in its own package:

- `ex66` — **Exercise 6.6**: the `Show` interface is unchanged. `CompositeShow` additionally implements `Iterable<Show>`, so clients can for-each over a composite after checking its concrete type (`instanceof`). Leaf shows are deliberately not iterable.
- `ex67` — **Exercise 6.7**: the `Show` interface itself is modified to `extends Iterable<Show>`. Every show is iterable through the interface type; leaves yield themselves exactly once, composites yield their direct children, so nested shows unpack uniformly with no downcast.

Both variants return `Collections.unmodifiableList(aShows).iterator()` so elements cannot be deleted through the iterator's `remove` method.

## Tests

- Iteration order matches child insertion order; nested composites unpack recursively.
- Leaves: `ex66` asserts `Movie`/`Concert` are **not** `Iterable`; `ex67` asserts each leaf iterates over exactly itself (`assertSame`).
- `iterator().remove()` throws `UnsupportedOperationException` in both variants.
- Regression on `description()` / `runningTime()` and the 2–5 shows constructor validation.

## Run

With JDK 17 or later and Maven installed, run from this folder:

```shell
mvn test
```
