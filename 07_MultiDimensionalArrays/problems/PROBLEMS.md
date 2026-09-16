# 07 - Multi-Dimensional Arrays: Problems

Run one from its folder, for example `java M07P01_RowColumnSums.java`. Each program checks itself and prints PASS or FAIL.

## P01 - Row and Column Sums (Easy)

Find the sum of each row and each column of a 2-D array that may be jagged. Use `m[r].length` in the inner loop.

Example: `[[1,2,3],[4],[5,6]]` -> rows `[6,4,11]`, cols `[10,8,3]`

Solution: [M07P01_RowColumnSums.java](P01_RowColumnSums/M07P01_RowColumnSums.java)

## P02 - Transpose a Matrix (Easy)

Build a new matrix where rows become columns: `result[c][r] = m[r][c]`. The new shape is `cols x rows`.

Example: `[[1,2,3],[4,5,6]]` -> `[[1,4],[2,5],[3,6]]`

Solution: [M07P02_TransposeMatrix.java](P02_TransposeMatrix/M07P02_TransposeMatrix.java)

## P03 - Diagonal Sum (Easy)

Add up both diagonals of a square matrix. Count the center cell only once. Use a `long` total.

Example: `[[1,2,3],[4,5,6],[7,8,9]]` -> `25`

Solution: [M07P03_DiagonalSum.java](P03_DiagonalSum/M07P03_DiagonalSum.java)

## P04 - Rotate Image (Medium)

Rotate a square matrix 90 degrees clockwise in place: transpose, then reverse each row.

Example: `[[1,2],[3,4]]` -> `[[3,1],[4,2]]`

Solution: [M07P04_RotateImage.java](P04_RotateImage/M07P04_RotateImage.java)

## P05 - Spiral Order (Medium)

List all values in clockwise spiral order, starting at the top-left. Move four walls inward and check them before the bottom and left sides.

Example: `[[1,2],[3,4],[5,6]]` -> `[1,2,4,6,5,3]`

Solution: [M07P05_SpiralOrder.java](P05_SpiralOrder/M07P05_SpiralOrder.java)

## P06 - Search a Sorted Matrix (Medium)

Every row and column is sorted. Find `target` or return `[-1, -1]`. Start at the top-right: too big, go left; too small, go down.

Example: `5` in the 5x5 matrix of the solution -> `[1, 1]`

Solution: [M07P06_SearchSortedMatrix.java](P06_SearchSortedMatrix/M07P06_SearchSortedMatrix.java)
