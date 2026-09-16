# 05 - Loops

Loops repeat code: `while`, `do-while` and `for`, plus `break`, `continue` and nested loops.
Run a lesson from this folder with `java M05L01_WhileLoop.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M05L01_WhileLoop](M05L01_WhileLoop.java) | `while`: repeat while a condition is true |
| [M05L02_DoWhileLoop](M05L02_DoWhileLoop.java) | `do-while`: the body runs at least once |
| [M05L03_ForLoop](M05L03_ForLoop.java) | the `for` header, steps, scope, looping over a String |
| [M05L04_Break](M05L04_Break.java) | `break`: leave a loop early |
| [M05L05_Continue](M05L05_Continue.java) | `continue`: skip to the next step |
| [M05L06_LabeledBreakContinue](M05L06_LabeledBreakContinue.java) | labeled `break` and `continue` in nested loops |
| [M05L07_NestedLoops](M05L07_NestedLoops.java) | loops inside loops, star patterns, a times table |
| [M05L08_LoopPatterns](M05L08_LoopPatterns.java) | sums, products, digits, reversing, counting, min/max |
| [M05L09_LoopPitfalls](M05L09_LoopPitfalls.java) | off-by-one, `for (...);`, double counters, endless loops |

The for-each loop comes in [06_Arrays](../06_Arrays/README.md), together with arrays.

## Key points

- `while` checks first (may run 0 times); `do-while` checks last (runs at least once) and ends with `;`.
- A loop must change something in its condition, or it never ends. `while (true)` needs a clear `break`.
- `for (int i = 0; i < n; i++)` runs `n` times; `i <= n` runs `n + 1` times.
- A variable declared in the `for` header or body exists only inside the loop. Declare totals before the loop.
- `break` leaves the nearest loop, `continue` jumps to the next step. In a `while`, update the counter before `continue`.
- Use a label (`outer:` ... `break outer;`) to leave nested loops.
- Sums start at `0`, products at `1`. Start a max at `Integer.MIN_VALUE`, not `0`.
- Never count with a `double` and `!=`. `for (...);` has an empty body. Always use braces.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
