public class M11P05_Fraction {

    static final class Fraction {
        private final long num;
        private final long den;

        Fraction(long num, long den) {
            if (den == 0) {
                throw new ArithmeticException("zero denominator");
            }
            // Keep the sign in the numerator and reduce to lowest terms
            if (den < 0) {
                num = -num;
                den = -den;
            }
            long g = gcd(Math.abs(num), den);
            this.num = num / g;
            this.den = den / g;
        }

        Fraction(long whole) {
            this(whole, 1);
        }

        static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        Fraction plus(Fraction o) {
            return new Fraction(num * o.den + o.num * den, den * o.den);
        }

        Fraction times(Fraction o) {
            return new Fraction(num * o.num, den * o.den);
        }

        Fraction dividedBy(Fraction o) {
            return new Fraction(num * o.den, den * o.num);
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Fraction f && num == f.num && den == f.den;
        }

        @Override
        public int hashCode() {
            return (int) (31 * num + den);
        }

        @Override
        public String toString() {
            return den == 1 ? String.valueOf(num) : num + "/" + den;
        }
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        check("2/4", new Fraction(2, 4), "1/2");
        check("3/-6", new Fraction(3, -6), "-1/2");
        check("0/5", new Fraction(0, 5), "0");
        check("1/2 + 1/3", new Fraction(1, 2).plus(new Fraction(1, 3)), "5/6");
        check("1/2 + 1/2", new Fraction(1, 2).plus(new Fraction(1, 2)), "1");
        check("2/3 * 3/4", new Fraction(2, 3).times(new Fraction(3, 4)), "1/2");
        check("1/2 / 1/4", new Fraction(1, 2).dividedBy(new Fraction(1, 4)), "2");
        check("1/2 equals 2/4", new Fraction(1, 2).equals(new Fraction(2, 4)), "true");
        check("same hash", new Fraction(1, 2).hashCode() == new Fraction(-2, -4).hashCode(), "true");
        check("3 equals 6/2", new Fraction(3).equals(new Fraction(6, 2)), "true");
        try {
            new Fraction(1, 2).dividedBy(new Fraction(0));
            check("1/2 / 0", "no error", "error: zero denominator");
        } catch (ArithmeticException e) {
            check("1/2 / 0", "error: " + e.getMessage(), "error: zero denominator");
        }

        if (failed) {
            System.exit(1);
        }
    }
}
