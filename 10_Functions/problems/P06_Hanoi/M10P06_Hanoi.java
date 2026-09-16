public class M10P06_Hanoi {

    // moves are written into this builder, e.g. "1:A->C "
    static void solve(int n, char from, char to, char via, StringBuilder out) {
        if (n == 0) {
            return;
        }
        solve(n - 1, from, via, to, out);
        out.append(n).append(':').append(from).append("->").append(to).append(' ');
        solve(n - 1, via, to, from, out);
    }

    static String moves(int n) {
        StringBuilder out = new StringBuilder();
        solve(n, 'A', 'C', 'B', out);
        return out.toString().trim();
    }

    // T(n) = 2 * T(n - 1) + 1
    static long countMoves(int n) {
        if (n == 0) {
            return 0;
        }
        return 2 * countMoves(n - 1) + 1;
    }

    // the k-th move (1-based) without listing all moves
    static String kthMove(int n, long k, char from, char to, char via) {
        long half = countMoves(n - 1);
        if (k <= half) {
            return kthMove(n - 1, k, from, via, to);
        }
        if (k == half + 1) {
            return n + ":" + from + "->" + to;
        }
        return kthMove(n - 1, k - half - 1, via, to, from);
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
        check("moves(1)", moves(1), "1:A->C");
        check("moves(2)", moves(2), "1:A->B 2:A->C 1:B->C");
        check("moves(3)", moves(3), "1:A->C 2:A->B 1:C->B 3:A->C 1:B->A 2:B->C 1:A->C");
        check("countMoves(10)", countMoves(10), "1023");
        check("countMoves(63)", countMoves(63), "9223372036854775807");
        check("kthMove(3, 5)", kthMove(3, 5, 'A', 'C', 'B'), "1:B->A");
        check("kthMove(60, 2^59)", kthMove(60, 1L << 59, 'A', 'C', 'B'), "60:A->C");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
