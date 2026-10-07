# 19 - File Handling: Problems

Run a solution from its folder, for example `java M19P01_WordCount.java`.
Each solution builds its own test files and deletes them when it is done.

## P01 - Word Count (Easy)

Count the lines and the words of a text file. Words are separated by spaces.

Example: `"one\ntwo three\n"` -> 2 lines, 3 words

Solution: [M19P01_WordCount.java](P01_WordCount/M19P01_WordCount.java)

## P02 - Line Numbers (Easy)

Copy one file to another, putting `1: `, `2: ` and so on in front of each line.

Example: `[alpha, beta]` -> `[1: alpha, 2: beta]`

Solution: [M19P02_LineNumbers.java](P02_LineNumbers/M19P02_LineNumbers.java)

## P03 - Find In File (Easy)

Return the numbers of the lines that contain a piece of text, counting from 1.
The search is case sensitive.

Example: `the` in `[the cat sat, on the mat, a dog barked, the end]` -> `[1, 2, 4]`

Solution: [M19P03_FindInFile.java](P03_FindInFile/M19P03_FindInFile.java)

## P04 - Remove Blank Lines (Easy)

Rewrite a file without its blank lines, and with no spaces left at the start or the end of a
line. Return how many lines were removed.

Example: `[  padded  ,    , x]` -> `[padded, x]`, 1 removed

Solution: [M19P04_RemoveBlankLines.java](P04_RemoveBlankLines/M19P04_RemoveBlankLines.java)

## P05 - Score Report (Medium)

A file holds one `name score` pair per line. Write a report file with one line per student,
then the total, the average with one decimal, and the name of the best student.
An empty input gives a single line `no students`.

Example: `ann 90`, `bob 72`, `cy 85` -> `ann: 90`, `bob: 72`, `cy: 85`, `total: 247`, `average: 82.3`, `best: ann`

Solution: [M19P05_ScoreReport.java](P05_ScoreReport/M19P05_ScoreReport.java)

## P06 - Folder Report (Medium)

Look at the files in a folder: how many there are, how many bytes they hold together, and the
name of the biggest one (`none` when the folder is empty).

Example: `a.txt` (5 bytes) and `b.txt` (1 byte) -> 2 files, 6 bytes, biggest `a.txt`

Solution: [M19P06_FolderReport.java](P06_FolderReport/M19P06_FolderReport.java)
