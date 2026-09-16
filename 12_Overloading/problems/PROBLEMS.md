# 12 - Overloading: Problems

## P01 - AreaCalculator (Easy)

Write one method name, `area`, for a square `(side)`, a rectangle `(w, h)`, a triangle `(a, b, c)` using Heron's formula,
and a `Circle` record. Reject impossible triangles with `IllegalArgumentException`.

Example: `area(3, 4, 5)` gives `6.0`, and `area('A')` gives `4225.0` (a char widens to double).

Solution: [M12P01_AreaCalculator.java](P01_AreaCalculator/M12P01_AreaCalculator.java)

## P02 - MaxOverloads (Easy)

Write `max` for `(int, int)`, `(long, long)`, `(double, double)`, `(int, int, int)`, `(String, String)` and a fallback `(int...)`.
Each returns which version ran plus the result.

Example: `max(3L, 7)` gives `long 7`, and `max(5)` gives `varargs 5`.

Solution: [M12P02_MaxOverloads.java](P02_MaxOverloads/M12P02_MaxOverloads.java)

## P03 - WhichPrimitive (Medium)

Given `m(short)`, `m(int)`, `m(long)` and `m(double)`, predict which one runs for calls like
`m(b)`, `m(c)`, `m(s + s)`, `m(b++)` and `m(true ? b : s)` (`b` byte, `s` short, `c` char).

Example: `m(c)` gives `int`, because a char cannot widen to short.

Solution: [M12P03_WhichPrimitive.java](P03_WhichPrimitive/M12P03_WhichPrimitive.java)

## P04 - WideningVsBoxing (Medium)

Given `p(long)`, `p(Integer)`, `p(Object)` and `p(int...)`, plus `r(Long)` and `r(Object)`, predict each result.
Remember the order: widening, then boxing, then varargs.

Example: `p(5)` gives `long`, `p(5.0)` gives `Object`, and `r(5)` gives `Object`.

Solution: [M12P04_WideningVsBoxing.java](P04_WideningVsBoxing/M12P04_WideningVsBoxing.java)

## P05 - NullArgumentPuzzle (Medium)

For pairs such as `a(Object)`/`a(String)`, `c(Object)`/`c(int...)` and `k(int)`/`k(Integer)`, predict what `f(null)` calls,
and use casts to pick another version.

Example: `c(null)` calls `c(int...)` with a null array.

Solution: [M12P05_NullArgumentPuzzle.java](P05_NullArgumentPuzzle/M12P05_NullArgumentPuzzle.java)

## P06 - VarargsPuzzle (Hard)

Predict the results for `v(int...)`/`v(long...)`, `w(Object...)`/`w(Integer...)`, `x(Object)`/`x(Object...)`
and `y(String, Object...)`/`y(Object...)`.

Example: `x(new int[0])` gives `Object`, because an `int[]` is one Object, not an `Object[]`.

Solution: [M12P06_VarargsPuzzle.java](P06_VarargsPuzzle/M12P06_VarargsPuzzle.java)
