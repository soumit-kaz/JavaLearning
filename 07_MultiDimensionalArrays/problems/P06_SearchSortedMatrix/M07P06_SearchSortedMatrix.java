import java.util.Arrays;

public class M07P06_SearchSortedMatrix {

    public static void main(String[] args) {
        // every row and every column is sorted
        int[][] m = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        int[] targets = {5, 20, 30, 18, 0, 15};
        int[][] expected = {{1, 1}, {-1, -1}, {4, 4}, {4, 0}, {-1, -1}, {0, 4}};

        for (int t = 0; t < targets.length; t++) {
            int target = targets[t];
            int[] found = {-1, -1};

            // start at the top-right corner
            int r = 0;
            int c = m[0].length - 1;
            while (r < m.length && c >= 0) {
                if (m[r][c] == target) {
                    found[0] = r;
                    found[1] = c;
                    break;
                } else if (m[r][c] > target) {
                    // too big: the whole column below is bigger too
                    c--;
                } else {
                    // too small: the whole row to the left is smaller too
                    r++;
                }
            }

            boolean pass = Arrays.equals(found, expected[t]);
            System.out.println(target + " -> " + Arrays.toString(found) + "  " + (pass ? "PASS" : "FAIL"));
        }
    }
}
