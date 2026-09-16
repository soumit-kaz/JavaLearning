# 12 - Overloading

**Overloading** means several methods share one name but have different parameter lists.
The compiler picks one **at compile time**, using the **declared** types of the arguments.
Run a lesson from this folder, for example `java M12L01_OverloadingBasics.java`.

## Lessons

| # | Lesson | What it shows |
|---|---|---|
| 1 | [M12L01_OverloadingBasics](M12L01_OverloadingBasics.java) | Different count, types and order; overloading `main` |
| 2 | [M12L02_ConstructorOverloading](M12L02_ConstructorOverloading.java) | Overloaded constructors chained with `this(...)` |
| 3 | [M12L03_PrimitiveWidening](M12L03_PrimitiveWidening.java) | Literal types, widening, expression types |
| 4 | [M12L04_Boxing](M12L04_Boxing.java) | Widening beats boxing, boxing rules, unboxing `null` |
| 5 | [M12L05_Varargs](M12L05_Varargs.java) | Varargs comes last, arrays as varargs |
| 6 | [M12L06_MostSpecificAndNull](M12L06_MostSpecificAndNull.java) | `null` arguments, the most specific type, ambiguity |
| 7 | [M12L07_OverloadingPitfalls](M12L07_OverloadingPitfalls.java) | Declared type decides, overload vs override, `equals(Point)`, `char[]` |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

## Key points

- Overloads must differ in the number, types or order of parameters. The return type and parameter names do not count.
- The compiler stops at the **first** phase with a match, then picks the **most specific** method:
  1. exact match or widening, no boxing, no varargs;
  2. boxing and unboxing allowed;
  3. varargs allowed.
- Widening order: `byte -> short -> int -> long -> float -> double`, and `char -> int`.
- Boxing then widening is allowed (`int -> Integer -> Object`); widening then boxing is not (`int -> long -> Long`).
- Unboxing `null` throws `NullPointerException`.
- `null` goes to the most specific type. With unrelated types the call is ambiguous; cast to choose: `pet((Cat) null)`.
- A `String[]` passed to `Object...` **is** the array; an `int[]` is always one item.
- A subclass method with different parameters **overloads**, it does not override. Use `@Override`.
