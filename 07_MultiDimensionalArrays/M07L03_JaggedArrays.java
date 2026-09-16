import java.util.Arrays;

public class M07L03_JaggedArrays {

    public static void main(String[] args) {
        // jagged: each row has its own length
        int[][] jagged = {
            {1, 2, 3},
            {4},
            {5, 6}
        };
        System.out.println(Arrays.deepToString(jagged));

        // always use jagged[r].length in the inner loop
        int sum = 0;
        for (int r = 0; r < jagged.length; r++) {
            for (int c = 0; c < jagged[r].length; c++) {
                sum += jagged[r][c];
            }
        }
        System.out.println("sum = " + sum);

        // bug: jagged[0].length is 3, but row 1 has only 1 cell
        try {
            for (int r = 0; r < jagged.length; r++) {
                for (int c = 0; c < jagged[0].length; c++) {
                    sum += jagged[r][c];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("error: " + e.getMessage());
        }

        // check both indexes before reading a cell
        int row = 1;
        int col = 2;
        boolean inside = row >= 0 && row < jagged.length && col >= 0 && col < jagged[row].length;
        System.out.println("[1][2] inside: " + inside);

        // only the row count is given, so rows start as null (new int[][3] does not compile)
        int[][] data = new int[3][];
        data[0] = new int[] {1, 2};
        data[2] = new int[4];
        System.out.println(Arrays.deepToString(data));

        // using a null row throws NullPointerException
        try {
            data[1][0] = 5;
        } catch (NullPointerException e) {
            System.out.println("error: NullPointerException");
        }

        // Pascal's triangle: row r has r + 1 numbers
        int[][] pascal = new int[5][];
        for (int r = 0; r < pascal.length; r++) {
            pascal[r] = new int[r + 1];

            // the first and last numbers are 1
            pascal[r][0] = 1;
            pascal[r][r] = 1;

            // each inner number is the sum of the two above it
            for (int c = 1; c < r; c++) {
                pascal[r][c] = pascal[r - 1][c - 1] + pascal[r - 1][c];
            }
        }
        for (int[] line : pascal) {
            System.out.println(Arrays.toString(line));
        }
    }
}
