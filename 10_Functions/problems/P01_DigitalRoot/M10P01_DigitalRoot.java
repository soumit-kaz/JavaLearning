public class M10P01_DigitalRoot {

    // sum of the digits, found recursively
    static int digitSum(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + digitSum(n / 10);
    }

    // keep summing digits until one digit is left
    static int digitalRoot(int n) {
        if (n < 10) {
            return n;
        }
        return digitalRoot(digitSum(n));
    }

    // how many times we had to sum the digits
    static int persistence(int n) {
        if (n < 10) {
            return 0;
        }
        return 1 + persistence(digitSum(n));
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

    static String solve(int n) {
        return "root " + digitalRoot(n) + ", steps " + persistence(n);
    }

    public static void main(String[] args) {
        check("0", solve(0), "root 0, steps 0");
        check("7", solve(7), "root 7, steps 0");
        check("16", solve(16), "root 7, steps 1");
        check("38", solve(38), "root 2, steps 2");
        check("493193", solve(493193), "root 2, steps 3");
        check("2147483647", solve(2147483647), "root 1, steps 3");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
