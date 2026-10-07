# 20 - Console Input and Output

Printing to the screen and reading what the user types.
Run a lesson with `java M20L01_PrintingOutput.java`.

The lessons that read input (`M20L02` to `M20L05` and `M20L08`) wait for you to type the
answers, so run them yourself. When the input is finished, press **Ctrl+D** on macOS and Linux,
or **Ctrl+Z** then Enter on Windows. Started by a script with nothing to type, they print
`No input` and stop instead of crashing.

You can also feed them a file or a pipe:

```bash
echo "Ann" | java M20L02_ReadingWithScanner.java
java M20L05_ReadingManyLines.java < some-file.txt
```

## Lessons

| File | What it shows |
|---|---|
| [M20L01_PrintingOutput](M20L01_PrintingOutput.java) | `println`, `print`, joining text with `+`, `System.err` |
| [M20L02_ReadingWithScanner](M20L02_ReadingWithScanner.java) | `new Scanner(System.in)`, `nextLine`, `nextInt`, `next`, `close` |
| [M20L03_ScannerPitfalls](M20L03_ScannerPitfalls.java) | The `nextInt` then `nextLine` trap and its fix, `hasNextInt` |
| [M20L04_ReadingNumbers](M20L04_ReadingNumbers.java) | `Integer.parseInt`, `NumberFormatException`, asking again until it is a number |
| [M20L05_ReadingManyLines](M20L05_ReadingManyLines.java) | Looping while `hasNextLine`, stopping at a word, end of input |
| [M20L06_CommandLineArguments](M20L06_CommandLineArguments.java) | `args`, checking `args.length`, converting arguments to numbers |
| [M20L07_FormattedOutput](M20L07_FormattedOutput.java) | `printf` with `%s` `%d` `%f` `%n`, widths, `String.format` |
| [M20L08_BufferedReaderInput](M20L08_BufferedReaderInput.java) | `BufferedReader` on `System.in`, `readLine`, `null` at the end |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here (look them up when you need them): `System.console()` and reading a password
without showing it, `Scanner` with a different locale or delimiter, sending output to a file
with `>` on the command line, and text menus that loop until the user quits.

## Scanner or BufferedReader?

| | Scanner | BufferedReader |
|---|---|---|
| Reads words and numbers | yes: `next`, `nextInt` | no, lines only |
| Reads a line | `nextLine` | `readLine` |
| End of input | `hasNextLine` is false | `readLine` gives `null` |
| Needs `throws IOException` | no | yes |
| Speed | slower | faster |

## Key points

- `print` stays on the line, `println` ends it. Print the question, then read the answer.
- Everything typed arrives as text. Use `Integer.parseInt` or `Double.parseDouble` for numbers.
- `"42" + 1` is `"421"`, but `Integer.parseInt("42") + 1` is `43`.
- Never mix `nextInt` and `nextLine` without reading the leftover line end, or read lines only.
- Check before you read: `hasNextInt` and `hasNextLine` stop the program from crashing.
- Wrong text in `parseInt` throws `NumberFormatException`; wrong type in `nextInt` throws
  `InputMismatchException`. Catch them and ask again.
- `System.err` is for error messages, `System.out` for everything else.
- Use `printf` when columns need to line up, and `println` the rest of the time.
- Check `args.length` before reading `args[0]`.
