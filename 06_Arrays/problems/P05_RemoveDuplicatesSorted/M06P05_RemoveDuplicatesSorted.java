import java.util.Arrays;

public class M06P05_RemoveDuplicatesSorted {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {};
                case 1 -> new int[] {1, 1, 2};
                case 2 -> new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
                default -> new int[] {-3, -3, -3};
            };
            String expected = switch (t) {
                case 0 -> "0 []";
                case 1 -> "2 [1, 2]";
                case 2 -> "5 [0, 1, 2, 3, 4]";
                default -> "1 [-3]";
            };
            String input = Arrays.toString(data);

            // the first element is always kept
            int length = data.length == 0 ? 0 : 1;
            for (int read = 1; read < data.length; read++) {
                // in a sorted array a new value differs from the last kept one
                if (data[read] != data[length - 1]) {
                    data[length] = data[read];
                    length++;
                }
            }
            String actual = length + " " + Arrays.toString(Arrays.copyOf(data, length));

            System.out.println(input + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
