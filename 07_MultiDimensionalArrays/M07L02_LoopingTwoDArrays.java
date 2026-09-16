import java.util.Arrays;

public class M07L02_LoopingTwoDArrays {

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };

        // outer loop picks a row, inner loop walks that row
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }

        // swap the loops to walk column by column
        System.out.print("by column: ");
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                System.out.print(grid[r][c] + " ");
            }
        }
        System.out.println();

        // nested for-each: each row is an int[], each value is an int
        int sum = 0;
        for (int[] row : grid) {
            for (int value : row) {
                sum += value;
            }
        }
        System.out.println("sum = " + sum);

        // row points to the real row, so row[0] = 0 changes the array
        for (int[] row : grid) {
            row[0] = 0;
        }
        System.out.println(Arrays.deepToString(grid));

        // a neat table with printf
        for (int[] row : grid) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }

        // println prints a char[] as text, so each char row prints as a line
        char[][] board = {
            {'X', '-', 'O'},
            {'-', 'X', '-'}
        };
        for (char[] row : board) {
            System.out.println(row);
        }
    }
}
