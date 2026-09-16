import java.util.Arrays;

public class M07P02_TransposeMatrix {

    public static void main(String[] args) {
        int[][][] tests = {
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{1, 2, 3}, {4, 5, 6}},
            {{1}, {2}, {3}},
            {{42}}
        };
        int[][][] expected = {
            {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
            {{1, 4}, {2, 5}, {3, 6}},
            {{1, 2, 3}},
            {{42}}
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] m = tests[t];
            int rows = m.length;
            int cols = m[0].length;

            // the shape flips: rows become columns
            int[][] result = new int[cols][rows];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    result[c][r] = m[r][c];
                }
            }

            boolean pass = Arrays.deepEquals(result, expected[t]);
            System.out.println(Arrays.deepToString(m) + " -> " + Arrays.deepToString(result)
                    + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
