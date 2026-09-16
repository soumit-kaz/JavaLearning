import java.util.Arrays;

public class M07L04_CopyingTwoDArrays {

    public static void main(String[] args) {
        // the outer array holds references to separate row arrays
        int[][] grid = {{1, 2}, {3, 4}};

        // this does not copy the row: firstRow and grid[0] are the same array
        int[] firstRow = grid[0];
        firstRow[0] = 99;
        System.out.println(Arrays.deepToString(grid));

        // swapping rows only swaps two references
        int[] temp = grid[0];
        grid[0] = grid[1];
        grid[1] = temp;
        System.out.println(Arrays.deepToString(grid));

        // trap: Arrays.fill puts the SAME row in every slot
        int[][] bad = new int[3][];
        Arrays.fill(bad, new int[2]);
        bad[1][1] = 5;
        System.out.println("bad = " + Arrays.deepToString(bad));

        // fix: create a new row for each slot
        int[][] good = new int[3][];
        for (int r = 0; r < good.length; r++) {
            good[r] = new int[2];
        }
        good[1][1] = 5;
        System.out.println("good = " + Arrays.deepToString(good));

        // clone() is a shallow copy: new outer array, shared rows
        int[][] original = {{1, 2}, {3, 4}};
        int[][] shallow = original.clone();
        shallow[0][0] = 100;
        System.out.println("rows shared: " + (shallow[0] == original[0]));
        System.out.println("original = " + Arrays.deepToString(original));

        // deep copy: clone every row as well
        int[][] deep = new int[original.length][];
        for (int r = 0; r < original.length; r++) {
            deep[r] = original[r].clone();
        }
        deep[0][0] = 555;
        System.out.println("original = " + Arrays.deepToString(original));
        System.out.println("deep = " + Arrays.deepToString(deep));

        // Arrays.equals compares the rows with ==, so it is false here
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{1, 2}, {3, 4}};
        System.out.println("Arrays.equals: " + Arrays.equals(a, b));

        // Arrays.deepEquals looks inside every row
        System.out.println("Arrays.deepEquals: " + Arrays.deepEquals(a, b));
    }
}
