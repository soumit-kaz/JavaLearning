import java.util.Arrays;

public class M07P01_RowColumnSums {

    public static void main(String[] args) {
        int[][][] tests = {
            {{1, 2, 3}, {4, 5, 6}},
            {{5}},
            {{1, 2, 3}, {4}, {5, 6}},
            {{}, {-1, 1}}
        };
        int[][] expectedRows = {{6, 15}, {5}, {6, 4, 11}, {0, 0}};
        int[][] expectedCols = {{5, 7, 9}, {5}, {10, 8, 3}, {-1, 1}};

        for (int t = 0; t < tests.length; t++) {
            int[][] m = tests[t];

            // the widest row decides how many column sums we need
            int maxCols = 0;
            for (int[] row : m) {
                maxCols = Math.max(maxCols, row.length);
            }

            int[] rowSums = new int[m.length];
            int[] colSums = new int[maxCols];

            // use m[r].length because rows can have different lengths
            for (int r = 0; r < m.length; r++) {
                for (int c = 0; c < m[r].length; c++) {
                    rowSums[r] += m[r][c];
                    colSums[c] += m[r][c];
                }
            }

            boolean pass = Arrays.equals(rowSums, expectedRows[t]) && Arrays.equals(colSums, expectedCols[t]);
            System.out.println("rows " + Arrays.toString(rowSums) + " cols " + Arrays.toString(colSums)
                    + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
