# 19 - File Handling

Reading and writing files with `Files` and `Path`.
Run a lesson with `java M19L01_PathBasics.java`.

Every lesson makes its own small files in the folder you run it from and deletes them again at
the end, so nothing is left behind.

## Lessons

| File | What it shows |
|---|---|
| [M19L01_PathBasics](M19L01_PathBasics.java) | `Path.of`, `getFileName`, `getParent`, `resolve`, `toAbsolutePath` |
| [M19L02_FileInfo](M19L02_FileInfo.java) | `exists`, `isRegularFile`, `isDirectory`, `size`, `delete` |
| [M19L03_WriteTextFile](M19L03_WriteTextFile.java) | `Files.writeString`, writing a list of lines, replacing the content |
| [M19L04_ReadTextFile](M19L04_ReadTextFile.java) | `Files.readString`, `Files.readAllLines`, looping, searching, counting |
| [M19L05_AppendToFile](M19L05_AppendToFile.java) | `StandardOpenOption.APPEND` and `CREATE`, writing a log |
| [M19L06_BufferedReaderWriter](M19L06_BufferedReaderWriter.java) | `newBufferedWriter`, `newBufferedReader`, the `readLine` loop |
| [M19L07_TryWithResources](M19L07_TryWithResources.java) | Closing a file, two files at once, closing when something fails |
| [M19L08_FileExceptions](M19L08_FileExceptions.java) | `IOException`, `NoSuchFileException`, carrying on, `finally` |
| [M19L09_ScannerOnFiles](M19L09_ScannerOnFiles.java) | `Scanner` on a file, `hasNextInt`, the `nextInt` / `nextLine` trap |
| [M19L10_CopyMoveDelete](M19L10_CopyMoveDelete.java) | `copy`, `REPLACE_EXISTING`, `move`, `delete`, `deleteIfExists` |
| [M19L11_Directories](M19L11_Directories.java) | `createDirectory`, `Files.list`, sizes, deleting a folder |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Reading the keyboard and printing to the screen is the next module:
[20_ConsoleIO](../20_ConsoleIO).

Not covered here (look them up when you need them): charsets, `InputStream` and
`OutputStream` for pictures and other binary files, `Files.walk` for whole folder trees,
`RandomAccessFile`, `Properties` files, and the old `java.io.File` API.

## Which method should I use?

| Job | Use |
|---|---|
| Read a small text file | `Files.readString` or `Files.readAllLines` |
| Read a big text file | `Files.newBufferedReader` and a `readLine` loop |
| Write a whole file at once | `Files.writeString` or `Files.write` |
| Write many lines one by one | `Files.newBufferedWriter` |
| Add to the end of a file | `Files.writeString(..., CREATE, APPEND)` |
| Read numbers and words | `Scanner` |
| See what is in a folder | `Files.list` |

## Key points

- A `Path` is only a name. Nothing happens on disk until you call a `Files` method.
- `Files.writeString` replaces everything in the file. Add `CREATE` and `APPEND` to write at
  the end instead.
- `Files.readAllLines` drops the line ends; `Files.readString` keeps them.
- A reader, a writer or `Files.list` has to be closed. Put it in `try (...)` and Java does it.
- `IOException` is checked: catch it, or add `throws IOException` to your method.
- `delete` fails when the file is not there, `deleteIfExists` answers `false`.
- A folder has to be empty before it can be deleted.
- The folder you write into must already exist, so call `Files.createDirectories` first.
- Mixing `nextInt` and `nextLine` on one `Scanner` leaves the line end behind.
- Write paths like `Path.of("data/notes.txt")` with `/`; Java fixes the separator on Windows.
