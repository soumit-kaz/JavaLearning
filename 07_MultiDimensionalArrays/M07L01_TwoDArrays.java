import java.util.Arrays;

public class M07L01_TwoDArrays {

    public static void main(String[] args) {
        // a 2-D array is a table: 2 rows, 3 columns
        int[][] table = new int[2][3];
        System.out.println("rows = " + table.length);
        System.out.println("cols = " + table[0].length);

        // read and write a cell with [row][col]
        table[1][2] = 7;
        System.out.println("table[1][2] = " + table[1][2]);

        // println(table) prints a code like [[I@1b6d3586; deepToString shows the values
        System.out.println(Arrays.deepToString(table));

        // new cells start with default values (0, 0.0, false, null)
        System.out.println(Arrays.deepToString(new String[1][2]));

        // each inner {} is one row
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };

        // grid[1] is a whole row, a plain int[]
        System.out.println("row 1 = " + Arrays.toString(grid[1]));

        // {{...}} works only in a declaration; later you must write new int[][]
        int[][] later;
        later = new int[][] {{7, 8}, {9, 10}};
        System.out.println(Arrays.deepToString(later));

        // trap: here x is int[] but y is int[][]; prefer writing int[][] y
        int[] x, y[];
        x = new int[2];
        y = new int[2][2];
        System.out.println("x.length = " + x.length + ", y[0].length = " + y[0].length);

        // more brackets, more dimensions: a 3-D array is [layer][row][col]
        int[][][] cube = {
            {{1, 2}, {3, 4}},
            {{5, 6}, {7, 8}}
        };
        System.out.println("cube[1][0][1] = " + cube[1][0][1]);
    }
}
