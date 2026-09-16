import java.util.Arrays;
import java.util.Random;

public class M1803L06_SegmentTree {

    // node i covers a range; children 2i and 2i+1 cover the two halves
    static class SegmentTree {
        int n;
        long[] tree;

        SegmentTree(int[] values) {
            n = values.length;
            tree = new long[4 * Math.max(1, n)];
            build(1, 0, n - 1, values);
        }

        void build(int node, int lo, int hi, int[] values) {
            if (lo > hi) {
                return;
            }
            if (lo == hi) {
                tree[node] = values[lo];
                return;
            }
            int mid = (lo + hi) / 2;
            build(2 * node, lo, mid, values);
            build(2 * node + 1, mid + 1, hi, values);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }

        // sum of values[l..r]
        long query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }

        long query(int node, int lo, int hi, int l, int r) {
            // no overlap
            if (r < lo || hi < l) {
                return 0;
            }
            // node range fully inside the query
            if (l <= lo && hi <= r) {
                return tree[node];
            }
            int mid = (lo + hi) / 2;
            return query(2 * node, lo, mid, l, r) + query(2 * node + 1, mid + 1, hi, l, r);
        }

        // values[index] = value, then fix the sums on the way up
        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        void update(int node, int lo, int hi, int index, int value) {
            if (lo == hi) {
                tree[node] = value;
                return;
            }
            int mid = (lo + hi) / 2;
            if (index <= mid) {
                update(2 * node, lo, mid, index, value);
            } else {
                update(2 * node + 1, mid + 1, hi, index, value);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {
        int[] values = {5, 3, 8, 6, 1, 4};
        SegmentTree seg = new SegmentTree(values);
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("sum [1, 3]: " + seg.query(1, 3));
        seg.update(2, 10);
        System.out.println("sum [1, 3] after update: " + seg.query(1, 3));
        boolean ok = seg.query(1, 3) == 19 && seg.query(0, 5) == 29;

        // random updates and queries compared with a plain array
        Random random = new Random(18);
        int n = 1 + random.nextInt(200);
        int[] array = new int[n];
        SegmentTree s = new SegmentTree(array);
        for (int step = 0; step < 5000; step++) {
            int i = random.nextInt(n);
            int value = random.nextInt(2001) - 1000;
            s.update(i, value);
            array[i] = value;
            int l = random.nextInt(n);
            int r = l + random.nextInt(n - l);
            long expected = 0;
            for (int k = l; k <= r; k++) {
                expected += array[k];
            }
            ok &= s.query(l, r) == expected;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
