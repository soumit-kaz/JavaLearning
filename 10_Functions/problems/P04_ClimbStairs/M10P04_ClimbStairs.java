public class M10P04_ClimbStairs {

    static long[] memo = new long[100];

    // ways to climb n steps taking 1, 2 or 3 steps at a time
    static long ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        // reuse an answer we already know
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = ways(n - 1) + ways(n - 2) + ways(n - 3);
        return memo[n];
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
        check("0", ways(0), "1");
        check("1", ways(1), "1");
        check("3", ways(3), "4");
        check("4", ways(4), "7");
        check("10", ways(10), "274");
        check("50", ways(50), "10562230626642");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
