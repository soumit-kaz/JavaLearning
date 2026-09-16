import java.util.Arrays;
import java.util.Random;

public class M1803L07_FenwickTree {

    // Fenwick tree (binary indexed tree), 1-based inside
    static class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        // add delta at index; i & -i is the lowest set bit
        void add(int index, long delta) {
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        // sum of values[0..index]
        long prefixSum(int index) {
            long sum = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                sum += tree[i];
            }
            return sum;
        }

        // sum of values[l..r] from two prefix sums
        long rangeSum(int l, int r) {
            return prefixSum(r) - (l == 0 ? 0 : prefixSum(l - 1));
        }
    }

    public static void main(String[] args) {
        int[] values = {5, 3, 8, 6, 1, 4};
        Fenwick fen = new Fenwick(values.length);
        for (int i = 0; i < values.length; i++) {
            fen.add(i, values[i]);
        }
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("sum [1, 3]: " + fen.rangeSum(1, 3));
        // to set values[2] = 10, add the difference
        fen.add(2, 10 - values[2]);
        values[2] = 10;
        System.out.println("sum [1, 3] after update: " + fen.rangeSum(1, 3));
        boolean ok = fen.rangeSum(1, 3) == 19 && fen.prefixSum(5) == 29;

        // random updates and queries compared with a plain array
        Random random = new Random(18);
        int n = 1 + random.nextInt(200);
        int[] array = new int[n];
        Fenwick f = new Fenwick(n);
        for (int step = 0; step < 5000; step++) {
            int i = random.nextInt(n);
            int value = random.nextInt(2001) - 1000;
            f.add(i, (long) value - array[i]);
            array[i] = value;
            int l = random.nextInt(n);
            int r = l + random.nextInt(n - l);
            long expected = 0;
            for (int k = l; k <= r; k++) {
                expected += array[k];
            }
            ok &= f.rangeSum(l, r) == expected;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
