public class M05P05_ReverseNumber {

    public static void main(String[] args) {
        for (int t = 0; t < 6; t++) {
            int n = switch (t) {
                case 0 -> 1234;
                case 1 -> 1200;
                case 2 -> -56;
                case 3 -> 0;
                case 4 -> 1_463_847_412;
                default -> 1_999_999_999;
            };
            int expected = switch (t) {
                case 0 -> 4321;
                case 1 -> 21;
                case 2 -> -65;
                case 3 -> 0;
                case 4 -> 2_147_483_641;
                default -> 0;
            };

            // reverse the digits into a long so an overflow can be detected
            long reversed = 0;
            int rest = n;
            while (rest != 0) {
                // for negative n, rest % 10 is negative too, so the sign is kept
                reversed = reversed * 10 + rest % 10;
                rest /= 10;
            }

            // if the result does not fit in an int, return 0
            int actual = reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE ? 0 : (int) reversed;

            String result = actual == expected ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(n + " -> " + actual + "  " + result);
        }
    }
}
