public class M10P05_FastPowerMod {

    // recursive: x^e = (x^(e/2))^2, times x if e is odd
    static long powModRecursive(long base, long exp, long mod) {
        if (exp == 0) {
            return 1 % mod;
        }
        long half = powModRecursive(base, exp / 2, mod);
        long result = half * half % mod;
        if (exp % 2 == 1) {
            result = result * (base % mod) % mod;
        }
        return result;
    }

    // iterative: look at the bits of exp one by one
    static long powModIterative(long base, long exp, long mod) {
        long result = 1 % mod;
        long b = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = result * b % mod;
            }
            b = b * b % mod;
            exp = exp / 2;
        }
        return result;
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

    static void both(String input, long base, long exp, long mod, String expected) {
        long r1 = powModRecursive(base, exp, mod);
        long r2 = powModIterative(base, exp, mod);
        // both versions must agree
        check(input, r1 == r2 ? r1 : r1 + " vs " + r2, expected);
    }

    public static void main(String[] args) {
        long p = 1_000_000_007L;
        both("2^10 mod 1000", 2, 10, 1000, "24");
        both("3^0 mod 7", 3, 0, 7, "1");
        both("5^3 mod 1", 5, 3, 1, "0");
        both("2^1000000000 mod p", 2, 1_000_000_000L, p, "140625001");
        both("12345^(p-1) mod p", 12345, p - 1, p, "1");
        both("7^(2^62) mod p", 7, 1L << 62, p, "328846418");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
