import java.util.Arrays;

public class M07P04_RotateImage {

    public static void main(String[] args) {
        int[][][] tests = {
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}},
            {{1, 2}, {3, 4}},
            {{1}}
        };
        int[][][] expected = {
            {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}},
            {{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}},
            {{3, 1}, {4, 2}},
            {{1}}
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] m = tests[t];
            String input = Arrays.deepToString(m);
            int n = m.length;

            // step 1: transpose (swap only above the diagonal, or each pair swaps back)
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int temp = m[i][j];
                    m[i][j] = m[j][i];
                    m[j][i] = temp;
                }
            }

            // step 2: reverse each row
            for (int[] row : m) {
                for (int left = 0, right = n - 1; left < right; left++, right--) {
                    int temp = row[left];
                    row[left] = row[right];
                    row[right] = temp;
                }
            }

            boolean pass = Arrays.deepEquals(m, expected[t]);
            System.out.println(input + " -> " + Arrays.deepToString(m) + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
