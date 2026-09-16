import java.util.Arrays;

public class M06P08_MaxSubarraySum {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
                case 1 -> new int[] {-3, -1, -2};
                case 2 -> new int[] {5};
                default -> new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
            };
            long expected = switch (t) {
                case 0 -> 6;
                case 1 -> -1;
                case 2 -> 5;
                default -> 4294967294L;
            };

            // long sums so big values cannot overflow
            long best = data[0];
            long current = data[0];
            for (int i = 1; i < data.length; i++) {
                // either extend the current run or start a new run at data[i]
                current = Math.max(data[i], current + data[i]);
                best = Math.max(best, current);
            }

            System.out.println(Arrays.toString(data) + " -> " + best
                    + (best == expected ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
