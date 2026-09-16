public class M10P07_NQueens {

    static int n;
    static int[] queenColumn;
    static boolean[] usedColumn;
    static boolean[] usedDiagonal;
    static boolean[] usedAntiDiagonal;
    static int count;
    static String first;

    static void place(int row) {
        if (row == n) {
            count++;
            if (first == null) {
                first = board();
            }
            return;
        }
        for (int col = 0; col < n; col++) {
            // cells on one diagonal share row - col; on the other, row + col
            int d = row - col + n;
            int a = row + col;
            if (usedColumn[col] || usedDiagonal[d] || usedAntiDiagonal[a]) {
                continue;
            }
            queenColumn[row] = col;
            usedColumn[col] = true;
            usedDiagonal[d] = true;
            usedAntiDiagonal[a] = true;
            place(row + 1);
            // undo the choice and try the next column
            usedColumn[col] = false;
            usedDiagonal[d] = false;
            usedAntiDiagonal[a] = false;
        }
    }

    static String board() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < n; row++) {
            char[] line = ".".repeat(n).toCharArray();
            line[queenColumn[row]] = 'Q';
            sb.append(line).append(row < n - 1 ? "/" : "");
        }
        return sb.toString();
    }

    static String solve(int size) {
        n = size;
        queenColumn = new int[n];
        usedColumn = new boolean[n];
        usedDiagonal = new boolean[2 * n];
        usedAntiDiagonal = new boolean[2 * n];
        count = 0;
        first = null;
        place(0);
        return count + " " + first;
    }

    static boolean allPassed = true;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            allPassed = false;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        check("n = 1", solve(1), "1 Q");
        check("n = 3", solve(3), "0 null");
        check("n = 4", solve(4), "2 .Q../...Q/Q.../..Q.");
        check("n = 6", solve(6), "4 .Q..../...Q../.....Q/Q...../..Q.../....Q.");
        check("n = 8", solve(8).split(" ")[0], "92");
        check("n = 10", solve(10).split(" ")[0], "724");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
