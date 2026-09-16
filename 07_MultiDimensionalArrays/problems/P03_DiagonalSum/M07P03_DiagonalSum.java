import java.util.Arrays;

public class M07P03_DiagonalSum {

    public static void main(String[] args) {
        int big = Integer.MAX_VALUE;
        int[][][] tests = {
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}},
            {{5}},
            {{big, big}, {big, big}}
        };
        long[] expected = {25, 8, 5, 4L * big};

        for (int t = 0; t < tests.length; t++) {
            int[][] m = tests[t];
            int n = m.length;

            // long, because the sum can be bigger than an int
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += m[i][i];

                // the center cell is on both diagonals; add it only once
                if (i != n - 1 - i) {
                    sum += m[i][n - 1 - i];
                }
            }

            boolean pass = sum == expected[t];
            System.out.println(Arrays.deepToString(m) + " -> " + sum + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
