public class M05P06_GcdAndLcm {

    public static void main(String[] args) {
        for (int t = 0; t < 6; t++) {
            int a = switch (t) {
                case 0 -> 12;
                case 1 -> 17;
                case 2 -> 0;
                case 3 -> -24;
                case 4 -> 100_000;
                default -> 21;
            };
            int b = switch (t) {
                case 0 -> 18;
                case 1 -> 5;
                case 2 -> 9;
                case 3 -> 36;
                case 4 -> 99_999;
                default -> 21;
            };
            String expected = switch (t) {
                case 0 -> "gcd=6 lcm=36";
                case 1 -> "gcd=1 lcm=85";
                case 2 -> "gcd=9 lcm=0";
                case 3 -> "gcd=12 lcm=72";
                case 4 -> "gcd=1 lcm=9999900000";
                default -> "gcd=21 lcm=21";
            };

            // Euclid: replace (x, y) by (y, x % y) until y is 0
            int x = Math.abs(a);
            int y = Math.abs(b);
            while (y != 0) {
                int remainder = x % y;
                x = y;
                y = remainder;
            }
            int gcd = x;

            // lcm = a / gcd * b; dividing first and using long avoids overflow
            long lcm = gcd == 0 ? 0 : (long) Math.abs(a) / gcd * Math.abs(b);

            String actual = "gcd=" + gcd + " lcm=" + lcm;
            String result = actual.equals(expected) ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(a + ", " + b + " -> " + actual + "  " + result);
        }
    }
}
