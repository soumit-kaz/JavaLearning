import java.util.Arrays;

public class M06P03_MoveZeros {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the test input and the expected answer
            int[] data = switch (t) {
                case 0 -> new int[] {0, 1, 0, 3, 12};
                case 1 -> new int[] {};
                case 2 -> new int[] {0, 0, 1};
                default -> new int[] {-1, 0, -2};
            };
            String expected = switch (t) {
                case 0 -> "[1, 3, 12, 0, 0]";
                case 1 -> "[]";
                case 2 -> "[1, 0, 0]";
                default -> "[-1, -2, 0]";
            };
            String input = Arrays.toString(data);

            // copy every non-zero value forward, keeping their order
            int write = 0;
            for (int value : data) {
                if (value != 0) {
                    data[write] = value;
                    write++;
                }
            }
            // fill the rest with zeros
            Arrays.fill(data, write, data.length, 0);
            String actual = Arrays.toString(data);

            System.out.println(input + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
