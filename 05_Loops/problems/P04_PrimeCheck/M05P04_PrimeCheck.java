public class M05P04_PrimeCheck {

    public static void main(String[] args) {
        for (int t = 0; t < 8; t++) {
            int n = switch (t) {
                case 0 -> 2;
                case 1 -> 17;
                case 2 -> 1;
                case 3 -> 0;
                case 4 -> -7;
                case 5 -> 91;
                case 6 -> 97;
                default -> 2_147_483_647;
            };
            boolean expected = switch (t) {
                case 0, 1, 6, 7 -> true;
                default -> false;
            };

            // numbers below 2 are not prime
            boolean isPrime = n >= 2;

            // only divisors up to the square root need checking; long avoids overflow of d * d
            for (long d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    isPrime = false;
                    break;
                }
            }

            String result = isPrime == expected ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(n + " -> " + isPrime + "  " + result);
        }
    }
}
