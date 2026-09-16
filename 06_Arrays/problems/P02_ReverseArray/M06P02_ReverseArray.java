import java.util.Arrays;

public class M06P02_ReverseArray {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {};
                case 1 -> new int[] {1};
                case 2 -> new int[] {1, 2, 3};
                default -> new int[] {5, -1, 0, 9};
            };
            String expected = switch (t) {
                case 0 -> "[]";
                case 1 -> "[1]";
                case 2 -> "[3, 2, 1]";
                default -> "[9, 0, -1, 5]";
            };
            String input = Arrays.toString(data);

            // swap the outer pair and move both indexes inward
            for (int left = 0, right = data.length - 1; left < right; left++, right--) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
            String actual = Arrays.toString(data);

            System.out.println(input + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
