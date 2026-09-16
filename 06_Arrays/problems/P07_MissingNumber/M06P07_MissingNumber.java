import java.util.Arrays;

public class M06P07_MissingNumber {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {3, 0, 1};
                case 1 -> new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1};
                case 2 -> new int[] {1};
                default -> new int[] {};
            };
            int expected = switch (t) {
                case 0 -> 2;
                case 1 -> 8;
                default -> 0;
            };

            // 0 + 1 + ... + n, as a long so it cannot overflow
            long n = data.length;
            long missing = n * (n + 1) / 2;
            // subtract every value; what is left is the missing number
            for (int value : data) {
                missing -= value;
            }

            System.out.println(Arrays.toString(data) + " -> " + missing
                    + (missing == expected ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
