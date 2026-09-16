import java.util.Arrays;

public class M06P04_RotateArray {

    public static void main(String[] args) {
        for (int t = 0; t < 5; t++) {
            // pick the test input, k and the expected answer
            int[] data = switch (t) {
                case 3 -> new int[] {};
                case 4 -> new int[] {1, 2, 3};
                default -> new int[] {1, 2, 3, 4, 5, 6, 7};
            };
            int k = switch (t) {
                case 0 -> 3;
                case 1 -> 10;
                case 2 -> -1;
                case 3 -> 5;
                default -> Integer.MIN_VALUE;
            };
            String expected = switch (t) {
                case 0, 1 -> "[5, 6, 7, 1, 2, 3, 4]";
                case 2 -> "[2, 3, 4, 5, 6, 7, 1]";
                case 3 -> "[]";
                default -> "[3, 1, 2]";
            };
            String input = Arrays.toString(data) + ", k=" + k;

            int n = data.length;
            if (n > 0) {
                // floorMod turns any k (even negative) into 0..n-1
                int shift = Math.floorMod(k, n);
                // element i moves to (i + shift) % n
                int[] rotated = new int[n];
                for (int i = 0; i < n; i++) {
                    rotated[(i + shift) % n] = data[i];
                }
                // copy the result back into the original array
                System.arraycopy(rotated, 0, data, 0, n);
            }
            String actual = Arrays.toString(data);

            System.out.println(input + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
