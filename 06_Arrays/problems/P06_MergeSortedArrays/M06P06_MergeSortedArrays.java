import java.util.Arrays;

public class M06P06_MergeSortedArrays {

    public static void main(String[] args) {
        for (int t = 0; t < 4; t++) {
            // pick the two test inputs and the expected answer
            int[] a = switch (t) {
                case 0 -> new int[] {1, 4, 7};
                case 1, 2 -> new int[] {};
                default -> new int[] {-5, 0};
            };
            int[] b = switch (t) {
                case 0 -> new int[] {2, 3, 8, 9};
                case 1 -> new int[] {1};
                case 2 -> new int[] {};
                default -> new int[] {-6, 10};
            };
            String expected = switch (t) {
                case 0 -> "[1, 2, 3, 4, 7, 8, 9]";
                case 1 -> "[1]";
                case 2 -> "[]";
                default -> "[-6, -5, 0, 10]";
            };

            int[] merged = new int[a.length + b.length];
            int i = 0;
            int j = 0;
            for (int k = 0; k < merged.length; k++) {
                // take from a if b is used up, or if a's front element is smaller
                if (j == b.length || (i < a.length && a[i] <= b[j])) {
                    merged[k] = a[i];
                    i++;
                } else {
                    merged[k] = b[j];
                    j++;
                }
            }
            String actual = Arrays.toString(merged);

            System.out.println(Arrays.toString(a) + " + " + Arrays.toString(b) + " -> " + actual
                    + (actual.equals(expected) ? "  PASS" : "  FAIL, expected " + expected));
        }
    }
}
