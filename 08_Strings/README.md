# 08 - Strings

Strings hold text. Run a lesson from this folder with `java M08L01_StringBasics.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M08L01_StringBasics](M08L01_StringBasics.java) | literals, escapes, `""` vs `null`, `+` rules |
| [M08L02_StringEquality](M08L02_StringEquality.java) | `==` vs `equals`, the string pool, `intern`, null-safe comparing |
| [M08L03_StringMethods](M08L03_StringMethods.java) | `charAt`, `substring`, `isBlank`, `repeat`, `compareTo`, `switch`, parsing numbers |
| [M08L04_SearchingStrings](M08L04_SearchingStrings.java) | `indexOf`, `lastIndexOf`, counting matches, `contains`, `startsWith` |
| [M08L05_SplittingStrings](M08L05_SplittingStrings.java) | `split` and its traps, `String.join`, `StringJoiner` |
| [M08L06_ChangingStrings](M08L06_ChangingStrings.java) | immutability, `replace` vs `replaceAll`, case and `Locale`, `strip` |
| [M08L07_StringBuilder](M08L07_StringBuilder.java) | changing text in place, building text in loops |
| [M08L08_Characters](M08L08_Characters.java) | `Character` methods, char math, `int[26]` counting, emoji |
| [M08L09_Formatting](M08L09_Formatting.java) | `String.format`, `printf`, `formatted` |
| [M08L10_TextBlocks](M08L10_TextBlocks.java) | `"""` text blocks, indentation, `\s` and `\` |
| [M08L11_RegexBasics](M08L11_RegexBasics.java) | `matches`, regex `replaceAll`, `Pattern` / `Matcher`, groups |

## Key points

- A `String` never changes. Methods like `toUpperCase()` return a new string, so store the result.
- Compare text with `equals`, never with `==`. Literals are pooled; `new String(...)` and text built at run time are not.
- `"literal".equals(x)` and `Objects.equals(a, b)` are safe when a value may be `null`.
- `+` works left to right: `"x" + 1 + 2` is `x12`, but `1 + 2 + "x"` is `3x`. `'a' + 'b'` is the number 195.
- Indexes go from `0` to `length() - 1`. In `substring(begin, end)` the `end` is excluded. `indexOf` returns `-1` when nothing is found.
- `replace` works on plain text. `replaceAll`, `split` and `matches` use regex, where `.` `|` `+` `*` need `\\`.
- `split` drops empty parts at the end (limit `-1` keeps them).
- Prefer `strip()` over `trim()`. Pass `Locale.ROOT` when changing the case of program text.
- `Integer.parseInt` throws `NumberFormatException` for spaces, decimals and too-big values.
- Use `StringBuilder` to build text in a loop. Its `equals` compares objects, so compare `toString()` values.
- A `char` is a number: `c - 'a'` is the alphabet position and `c - '0'` a digit's value.

Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)
