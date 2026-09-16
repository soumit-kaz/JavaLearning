import java.math.BigInteger;

public class M05P02_Factorial {

    public static void main(String[] args) {
        for (int t = 0; t < 5; t++) {
            int n = switch (t) {
                case 0 -> 0;
                case 1 -> 1;
                case 2 -> 5;
                case 3 -> 20;
                default -> 25;
            };
            String expected = switch (t) {
                case 0, 1 -> "1";
                case 2 -> "120";
                case 3 -> "2432902008176640000";
                default -> "15511210043330985984000000";
            };

            // a product starts at 1; a long overflows after 20!, BigInteger never does
            BigInteger factorial = BigInteger.ONE;
            for (int i = 2; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            String actual = factorial.toString();
            String result = actual.equals(expected) ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(n + "! -> " + actual + "  " + result);
        }
    }
}
