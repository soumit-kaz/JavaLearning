# 03 - Operators

How Java calculates, compares and combines values.
Run a lesson from this folder with `java M03L01_ArithmeticOperators.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M03L01_ArithmeticOperators](M03L01_ArithmeticOperators.java) | `+ - * / %`, integer division, precedence |
| [M03L02_AssignmentOperators](M03L02_AssignmentOperators.java) | `= += -= *= /= %=`, the hidden cast |
| [M03L03_IncrementDecrement](M03L03_IncrementDecrement.java) | `++` and `--`, prefix vs postfix |
| [M03L04_ComparisonOperators](M03L04_ComparisonOperators.java) | `== != < > <= >=`, comparing Strings and wrappers |
| [M03L05_LogicalOperators](M03L05_LogicalOperators.java) | `&& \|\| ! ^`, short-circuit |
| [M03L06_BitwiseOperators](M03L06_BitwiseOperators.java) | `& \| ^ ~` and shifts `<< >> >>>` |
| [M03L07_MathClass](M03L07_MathClass.java) | `max`, `abs`, `pow`, `sqrt`, rounding, `floorMod` |

## Key points

- `int / int` is an int: `7 / 2` is `3`. `10 / 0` with ints throws `ArithmeticException`.
- `a % b` takes the sign of `a` (`-9 % 2` is `-1`), so test odd with `n % 2 != 0`.
- `* / %` come before `+ -`; a cast binds tighter still. Use parentheses when unsure.
- `x += y` hides a cast: `byte b = 120; b += 10;` compiles and overflows.
- `x++` gives the old value, `++x` the new one. Keep `++` on its own line.
- Compare Strings and wrappers with `equals`, not `==`. `NaN == NaN` is false.
- `&&`/`||` stop early, so `x != 0 && 10 / x > 1` is safe. `&&` comes before `||`.
- `==` comes before `&`: write `(flags & 4) != 0`. `>>` keeps the sign, `>>>` fills with zeros.
- Compare doubles with a tolerance: `Math.abs(a - b) < 1e-9`. `Math.round` returns a `long`.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
