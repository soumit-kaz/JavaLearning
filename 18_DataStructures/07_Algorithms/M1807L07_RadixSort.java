import java.util.Arrays;
import java.util.Random;

public class M1807L07_RadixSort {

    // LSD radix sort for non-negative ints: stable counting sort on each decimal digit
    static void radixSort(int[] a) {
        int max = 0;
        for (int x : a) {
            if (x < 0) {
                throw new IllegalArgumentException("only non-negative values");
            }
            max = Math.max(max, x);
        }
        int[] out = new int[a.length];
        // long avoids overflow when exp passes the largest power of ten
        for (long exp = 1; max / exp > 0; exp *= 10) {
            int[] count = new int[10];
            for (int x : a) {
                count[(int) (x / exp % 10)]++;
            }
            for (int d = 1; d < 10; d++) {
                count[d] += count[d - 1];
            }
            // stability here is what keeps the order from earlier digits
            for (int i = a.length - 1; i >= 0; i--) {
                out[--count[(int) (a[i] / exp % 10)]] = a[i];
            }
            System.arraycopy(out, 0, a, 0, a.length);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(numbers);
        System.out.println(Arrays.toString(numbers));

        int[] big = {Integer.MAX_VALUE, 0, 1_000_000_000, 7};
        radixSort(big);
        boolean ok = Arrays.equals(big, new int[]{0, 7, 1_000_000_000, Integer.MAX_VALUE});
        try {
            radixSort(new int[]{1, -1});
            ok = false;
        } catch (IllegalArgumentException e) {
            // expected: negatives are rejected
        }

        // random arrays over the full int range, compared with Arrays.sort
        Random random = new Random(7);
        for (int t = 0; t < 300; t++) {
            int[] a = new int[random.nextInt(60)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(Integer.MAX_VALUE);
            }
            int[] expected = a.clone();
            Arrays.sort(expected);
            radixSort(a);
            ok &= Arrays.equals(a, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
