import java.util.Arrays;
import java.util.Random;

public class M1807L06_CountingSort {

    // stable counting sort for values in [min, max]; time O(n + k), k = max - min + 1
    static int[] countingSort(int[] a) {
        if (a.length == 0) {
            return new int[0];
        }
        int min = a[0];
        int max = a[0];
        for (int x : a) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        // shifting by min lets negative values work too
        int[] count = new int[max - min + 1];
        for (int x : a) {
            count[x - min]++;
        }
        // prefix sums: count[v] becomes the index just past the last slot for v
        for (int v = 1; v < count.length; v++) {
            count[v] += count[v - 1];
        }
        // walking backwards keeps equal values in input order (stable)
        int[] out = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            out[--count[a[i] - min]] = a[i];
        }
        return out;
    }

    public static void main(String[] args) {
        int[] data = {4, -2, 2, 8, 3, 3, 1, -2};
        System.out.println(Arrays.toString(countingSort(data)));

        boolean ok = countingSort(new int[0]).length == 0;
        ok &= Arrays.equals(countingSort(new int[]{5, 5, 5}), new int[]{5, 5, 5});

        // random arrays with a small range, compared with Arrays.sort
        Random random = new Random(6);
        for (int t = 0; t < 300; t++) {
            int[] a = new int[random.nextInt(60)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(41) - 20;
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            ok &= Arrays.equals(countingSort(a), expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
