public class M05P07_FizzBuzz {

    public static void main(String[] args) {
        for (int t = 0; t < 3; t++) {
            int n = switch (t) {
                case 0 -> 5;
                case 1 -> 15;
                default -> 0;
            };
            String expected = switch (t) {
                case 0 -> "1 2 Fizz 4 Buzz";
                case 1 -> "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz";
                default -> "";
            };

            String actual = "";
            for (int i = 1; i <= n; i++) {
                // check 15 first, otherwise 15 would stop at "Fizz"
                String word;
                if (i % 15 == 0) {
                    word = "FizzBuzz";
                } else if (i % 3 == 0) {
                    word = "Fizz";
                } else if (i % 5 == 0) {
                    word = "Buzz";
                } else {
                    word = String.valueOf(i);
                }
                actual += i == 1 ? word : " " + word;
            }

            String result = actual.equals(expected) ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(n + " -> [" + actual + "]  " + result);
        }
    }
}
