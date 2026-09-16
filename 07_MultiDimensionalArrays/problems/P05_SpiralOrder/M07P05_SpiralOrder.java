import java.util.Arrays;

public class M07P05_SpiralOrder {

    public static void main(String[] args) {
        int[][][] tests = {
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
            {{1, 2, 3}},
            {{1}, {2}, {3}},
            {{1, 2}, {3, 4}, {5, 6}}
        };
        int[][] expected = {
            {1, 2, 3, 6, 9, 8, 7, 4, 5},
            {1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7},
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 4, 6, 5, 3}
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] m = tests[t];
            int[] result = new int[m.length * m[0].length];
            int count = 0;

            int top = 0;
            int bottom = m.length - 1;
            int left = 0;
            int right = m[0].length - 1;

            while (top <= bottom && left <= right) {
                for (int c = left; c <= right; c++) {
                    result[count++] = m[top][c];
                }
                top++;
                for (int r = top; r <= bottom; r++) {
                    result[count++] = m[r][right];
                }
                right--;

                // these two checks stop a single leftover row or column from being read twice
                if (top <= bottom) {
                    for (int c = right; c >= left; c--) {
                        result[count++] = m[bottom][c];
                    }
                    bottom--;
                }
                if (left <= right) {
                    for (int r = bottom; r >= top; r--) {
                        result[count++] = m[r][left];
                    }
                    left++;
                }
            }

            boolean pass = Arrays.equals(result, expected[t]);
            System.out.println(Arrays.deepToString(m) + " -> " + Arrays.toString(result)
                    + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
