# 04 - Conditions

Making decisions: run code only when something is true, and choose between several paths.
Run a lesson from this folder with `java M04L01_If.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M04L01_If](M04L01_If.java) | `if`: run a block only when a condition is true |
| [M04L02_IfElse](M04L02_IfElse.java) | `if`-`else`: choose one of two blocks |
| [M04L03_ElseIfLadder](M04L03_ElseIfLadder.java) | `else if`: the first true branch wins |
| [M04L04_NestedIf](M04L04_NestedIf.java) | an `if` inside another `if` |
| [M04L05_LogicalConditions](M04L05_LogicalConditions.java) | `&&` and `\|\|` in conditions, ranges, safe checks |
| [M04L06_Switch](M04L06_Switch.java) | classic `switch`, `break`, `default`, fall-through |
| [M04L07_SwitchExpressions](M04L07_SwitchExpressions.java) | arrow cases, switch expressions, `yield` |
| [M04L08_TernaryOperator](M04L08_TernaryOperator.java) | `cond ? a : b` |
| [M04L09_ConditionPitfalls](M04L09_ConditionPitfalls.java) | common `if` mistakes |

## Key points

- The condition must be a `boolean`: `if (1)` does not compile.
- In an else-if ladder only the first true branch runs, so put the most specific check first.
- A variable must be assigned on every path before it is read; a variable declared inside `{ }` is gone after it.
- `1 <= x <= 10` does not compile; write `x >= 1 && x <= 10`.
- Classic `switch` falls through until a `break`. It works on `int`, `char`, `String`, not `long`, `double` or `boolean`.
- Arrow cases never fall through. A switch expression must cover every value, ends with `;`, and uses `yield` in a block.
- The ternary must produce a value. Avoid nesting it.
- Always use braces. `if (x > 5);` ends the `if` at the semicolon. `if (flag = true)` is always true.
- Compare Strings with `equals` (literal first: `"yes".equals(answer)`); check NaN with `Double.isNaN`.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
