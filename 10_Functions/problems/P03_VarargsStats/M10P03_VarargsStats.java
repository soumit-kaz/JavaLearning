public class M10P03_VarargsStats {

    // the first parameter forces at least one value
    static int min(int first, int... rest) {
        int result = first;
        for (int n : rest) {
            result = Math.min(result, n);
        }
        return result;
    }

    static int max(int first, int... rest) {
        int result = first;
        for (int n : rest) {
            result = Math.max(result, n);
        }
        return result;
    }

    static double average(int first, int... rest) {
        // use long so big numbers do not overflow
        long sum = first;
        for (int n : rest) {
            sum += n;
        }
        return (double) sum / (rest.length + 1);
    }

    static String stats(int first, int... rest) {
        return min(first, rest) + " " + max(first, rest) + " " + average(first, rest);
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
        check("5", stats(5), "5 5 5.0");
        check("3, 9, -2, 6", stats(3, 9, -2, 6), "-2 9 4.0");
        check("1, 2", stats(1, 2), "1 2 1.5");
        check("10, [20, 30]", stats(10, new int[]{20, 30}), "10 30 20.0");
        check("MAX, MAX", stats(2147483647, 2147483647), "2147483647 2147483647 2.147483647E9");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
