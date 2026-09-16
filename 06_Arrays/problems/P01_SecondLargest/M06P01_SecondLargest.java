import java.util.Arrays;

public class M06P01_SecondLargest {

    public static void main(String[] args) {
        for (int t = 0; t < 5; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {3, 5, 1};
                case 1 -> new int[] {5, 5, 5};
                case 2 -> new int[] {};
                case 3 -> new int[] {-2, -8, -2};
                default -> new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE};
            };
            String expected = switch (t) {
                case 0 -> "3";
                case 3 -> "-8";
                case 4 -> "-2147483648";
                default -> "none";
            };

            // long starting values are smaller than every int
            long largest = Long.MIN_VALUE;
            long second = Long.MIN_VALUE;
            for (int value : data) {
                if (value > largest) {
                    // the old largest moves down to second place
                    second = largest;
                    largest = value;
                } else if (value < largest && value > second) {
                    // copies of the largest are skipped
                    second = value;
                }
            }
            String actual = (second == Long.MIN_VALUE) ? "none" : "" + second;

            System.out.println(Arrays.toString(data) + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
