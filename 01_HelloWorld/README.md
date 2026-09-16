# 01 - Hello World

Your first Java programs: printing text and numbers.
Run a lesson from this folder with `java M01L01_HelloWorld.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M01L01_HelloWorld](M01L01_HelloWorld.java) | the smallest complete program |
| [M01L02_Println](M01L02_Println.java) | `println`, blank lines, drawing with rows |
| [M01L03_Print](M01L03_Print.java) | `print` stays on the same line |
| [M01L04_EscapeSequences](M01L04_EscapeSequences.java) | escapes `\n \t \" \\` |
| [M01L05_PrintingNumbers](M01L05_PrintingNumbers.java) | numbers, math in `println`, joining text with `+` |
| [M01L06_Printf](M01L06_Printf.java) | `printf` placeholders, widths, alignment, `%,d` |

## Key points

- Every program needs `public class Name` and `public static void main(String[] args)`; the file name must match the class name exactly.
- Statements end with `;`. Java is case-sensitive. Text goes in `"..."`, one character in `'A'`.
- Run with `javac File.java` then `java File`, or in one step `java File.java`.
- `print` stays on the line; `println` ends it; `println()` alone prints a blank line.
- An escape is one character: `\n` new line, `\t` tab, `\"` quote, `\\` backslash. `"C:\temp"` silently contains a tab.
- `7 / 2` is `3` (whole-number division); `7.0 / 2` is `3.5`. `0.1 + 0.2` is not exactly `0.3`.
- `* / %` happen before `+ -`. After text, `+` joins: `"2 + 3 = " + 2 + 3` prints `23`; write `(2 + 3)`.
- `printf` needs `%n` for a new line. `%.2f` rounds, `%5d` is 5 wide, `%-5d` lines up left. A wrong placeholder fails at run time.

## Problems

See [problems/PROBLEMS.md](problems/PROBLEMS.md).
