import java.util.Arrays;

public class M07L05_MatrixOperations {

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{6, 5, 4}, {3, 2, 1}};
        int rows = a.length;
        int cols = a[0].length;

        // addition needs the same shape: add cell by cell
        int[][] sum = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sum[r][c] = a[r][c] + b[r][c];
            }
        }
        System.out.println("a + b = " + Arrays.deepToString(sum));

        // row sums and column sums in one pass
        int[] rowSums = new int[rows];
        int[] colSums = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rowSums[r] += a[r][c];
                colSums[c] += a[r][c];
            }
        }
        System.out.println("row sums = " + Arrays.toString(rowSums));
        System.out.println("col sums = " + Arrays.toString(colSums));

        // multiply: columns of the left matrix must equal rows of the right one
        // a is 2x3 and m is 3x2, so the product is 2x2
        int[][] m = {{1, 0}, {2, 1}, {0, 3}};
        int[][] product = new int[rows][m[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // product[i][j] = sum of a[i][k] * m[k][j]
                for (int k = 0; k < cols; k++) {
                    product[i][j] += a[i][k] * m[k][j];
                }
            }
        }
        System.out.println("a * m = " + Arrays.deepToString(product));

        // transpose: rows become columns, so 2x3 becomes 3x2
        int[][] t = new int[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                t[c][r] = a[r][c];
            }
        }
        System.out.println("transpose = " + Arrays.deepToString(t));

        // rotate a square matrix clockwise in place: transpose, then reverse each row
        int[][] sq = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int n = sq.length;

        // swap only above the diagonal, or every pair swaps back
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = sq[i][j];
                sq[i][j] = sq[j][i];
                sq[j][i] = temp;
            }
        }
        for (int[] row : sq) {
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
            }
        }
        System.out.println("rotated = " + Arrays.deepToString(sq));
    }
}
