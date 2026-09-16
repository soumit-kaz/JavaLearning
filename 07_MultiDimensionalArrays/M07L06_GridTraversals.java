public class M07L06_GridTraversals {

    public static void main(String[] args) {
        int[][] m = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int n = m.length;

        // main diagonal: row == col; anti-diagonal: row + col == n - 1
        int diagonalSum = 0;
        for (int i = 0; i < n; i++) {
            diagonalSum += m[i][i];

            // the center cell is on both diagonals, so count it once
            if (i != n - 1 - i) {
                diagonalSum += m[i][n - 1 - i];
            }
        }
        System.out.println("diagonal sum = " + diagonalSum);

        // spiral: four walls that move inward after each side
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        System.out.print("spiral: ");
        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) {
                System.out.print(m[top][c] + " ");
            }
            top++;
            for (int r = top; r <= bottom; r++) {
                System.out.print(m[r][right] + " ");
            }
            right--;

            // these checks stop a leftover row or column being read twice
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    System.out.print(m[bottom][c] + " ");
                }
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    System.out.print(m[r][left] + " ");
                }
                left++;
            }
        }
        System.out.println();

        // neighbours of [0][0]: skip cells outside the grid and the cell itself
        int row = 0;
        int col = 0;
        int neighbourSum = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = row + dr;
                int nc = col + dc;
                boolean inside = nr >= 0 && nr < n && nc >= 0 && nc < n;
                if (inside && (dr != 0 || dc != 0)) {
                    neighbourSum += m[nr][nc];
                }
            }
        }
        System.out.println("neighbours of [0][0] sum = " + neighbourSum);

        // staircase search: rows and columns are sorted, start at the top-right
        int target = 8;
        int r = 0;
        int c = n - 1;
        while (r < n && c >= 0 && m[r][c] != target) {
            if (m[r][c] > target) {
                // too big: everything below is bigger, so go left
                c--;
            } else {
                // too small: everything to the left is smaller, so go down
                r++;
            }
        }
        System.out.println("8 at [" + r + "][" + c + "]");
    }
}
