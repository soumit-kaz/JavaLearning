public class M05P03_Fibonacci {

    public static void main(String[] args) {
        for (int t = 0; t < 5; t++) {
            int count = switch (t) {
                case 0 -> 1;
                case 1 -> 2;
                case 2 -> 5;
                case 3 -> 10;
                default -> 0;
            };
            String expected = switch (t) {
                case 0 -> "0";
                case 1 -> "0 1";
                case 2 -> "0 1 1 2 3";
                case 3 -> "0 1 1 2 3 5 8 13 21 34";
                default -> "";
            };

            // keep only the last two numbers
            long previous = 0;
            long current = 1;
            String actual = "";
            for (int i = 0; i < count; i++) {
                actual += (i == 0 ? "" : " ") + previous;
                // the next number is the sum of the two before it
                long next = previous + current;
                previous = current;
                current = next;
            }

            String result = actual.equals(expected) ? "PASS" : "FAIL (expected " + expected + ")";
            System.out.println(count + " -> [" + actual + "]  " + result);
        }
    }
}
