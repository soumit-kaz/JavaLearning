# 07 - Multi-Dimensional Arrays

A 2-D array is an array of rows. Picture it as a table and reach one cell with `grid[row][col]`. Run a lesson with `java M07L01_TwoDArrays.java`.

## Lessons

| Lesson | Shows |
|---|---|
| [M07L01_TwoDArrays](M07L01_TwoDArrays.java) | creating, `[row][col]`, defaults, `{{...}}`, `deepToString`, 3-D |
| [M07L02_LoopingTwoDArrays](M07L02_LoopingTwoDArrays.java) | nested `for`, column order, nested for-each, printing a table |
| [M07L03_JaggedArrays](M07L03_JaggedArrays.java) | rows of different lengths, null rows, bounds checks, Pascal's triangle |
| [M07L04_CopyingTwoDArrays](M07L04_CopyingTwoDArrays.java) | shared rows, swapping rows, shallow vs deep copy, `deepEquals` |
| [M07L05_MatrixOperations](M07L05_MatrixOperations.java) | add, row/column sums, multiply, transpose, rotate |
| [M07L06_GridTraversals](M07L06_GridTraversals.java) | diagonals, spiral, neighbours, staircase search |

## Key points

- `grid.length` is the number of rows. `grid[r].length` is the length of row `r`; use it in the inner loop, because `grid[0].length` breaks on jagged arrays.
- `int[] x, y[];` makes `x` an `int[]` and `y` an `int[][]`. Write `int[][] y`.
- `{{1, 2}}` works only in a declaration. Elsewhere write `new int[][] {{1, 2}}`.
- `new int[3][]` creates 3 `null` rows; using one throws `NullPointerException`.
- In a nested for-each, `row` is the real row, so `row[i] = ...` changes the array.
- Rows are separate arrays: `int[] row = grid[0]` shares the row, and `Arrays.fill(grid, new int[2])` puts the same row in every slot.
- `clone()` is shallow: the rows are shared. For a deep copy, clone every row.
- Print with `Arrays.deepToString` and compare with `Arrays.deepEquals`.
- Multiplying needs left columns == right rows. Only a square matrix can be rotated in place.

Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)
