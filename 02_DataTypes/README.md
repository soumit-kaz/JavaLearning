# 02 - Data Types

How Java stores values: variables, the primitive types, conversions, wrappers and big numbers.
Run a lesson from this folder with `java M02L01_Variables.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M02L01_Variables](M02L01_Variables.java) | declaring and changing variables, `String`, `final`, `var` |
| [M02L02_IntegerTypes](M02L02_IntegerTypes.java) | `byte short int long`, limits, literals, type promotion |
| [M02L03_DecimalTypes](M02L03_DecimalTypes.java) | `float` and `double`, precision, Infinity and NaN |
| [M02L04_CharType](M02L04_CharType.java) | `char`, `charAt`, a char as a number, digit tricks |
| [M02L05_BooleanType](M02L05_BooleanType.java) | `boolean` values `true` and `false` |
| [M02L06_Casting](M02L06_Casting.java) | widening, narrowing casts, overflow |
| [M02L07_WrapperClasses](M02L07_WrapperClasses.java) | `Integer` and friends, autoboxing, parsing |
| [M02L08_BigNumbers](M02L08_BigNumbers.java) | `BigInteger` and `BigDecimal` |

## Key points

- 8 primitive types: `byte short int long` (whole), `float double` (decimal), `char`, `boolean`.
- A local variable must be assigned before use. `var` needs a starting value; `final` can be set only once.
- Whole literals are `int` (use `L` for bigger), decimal literals are `double` (use `f` for float). A leading `0` means octal: `010` is 8.
- `byte + byte` is an `int`. Constants that fit need no cast.
- `Double.MIN_VALUE` is the smallest positive value. `0.1 + 0.2` is not `0.3`: never use `double` for money.
- A `char` is a number (0 to 65535): `'A' + 1` is `66`, `'7' - '0'` is `7`. String positions start at `0`.
- `boolean` holds only `true` or `false` and is not a number.
- Widening is automatic. Narrowing needs a cast: decimals are cut, whole numbers keep low bits (`(byte) 300` is `44`).
- Overflow is silent: `Integer.MAX_VALUE + 1` is `MIN_VALUE`. Cast before multiplying into a `long`.
- Wrappers can be `null`; compare them with `equals`. `Integer.parseInt("abc")` throws `NumberFormatException`.
- `BigInteger`/`BigDecimal` never change: keep the result of `add`. Build `BigDecimal` from a String, compare with `compareTo`, and give a scale and `RoundingMode` when dividing.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
