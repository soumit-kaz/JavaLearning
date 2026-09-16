import java.util.Random;

public class M1806L04_SparseTable {

    static class SparseTable {
        // table[k][i] = minimum of the 2^k values starting at i
        private final int[][] table;
        // log[len] = floor(log2(len))
        private final int[] log;

        SparseTable(int[] values) {
            int n = values.length;
            if (n == 0) {
                throw new IllegalArgumentException("array must not be empty");
            }
            log = new int[n + 1];
            for (int len = 2; len <= n; len++) {
                log[len] = log[len / 2] + 1;
            }
            int levels = log[n] + 1;
            table = new int[levels][];
            table[0] = values.clone();
            for (int k = 1; k < levels; k++) {
                int half = 1 << (k - 1);
                table[k] = new int[n - (1 << k) + 1];
                // a block of size 2^k is two blocks of size 2^(k-1)
                for (int i = 0; i < table[k].length; i++) {
                    table[k][i] = Math.min(table[k - 1][i], table[k - 1][i + half]);
                }
            }
        }

        int min(int left, int right) {
            if (left < 0 || right >= table[0].length || left > right) {
                throw new IllegalArgumentException("bad range [" + left + ", " + right + "]");
            }
            int k = log[right - left + 1];
            // two blocks of size 2^k cover the range; overlap is fine for min
            return Math.min(table[k][left], table[k][right - (1 << k) + 1]);
        }
    }

    public static void main(String[] args) {
        int[] values = {5, 2, 4, 7, 1, 3, 6, 8};
        SparseTable table = new SparseTable(values);
        System.out.println("min(0, 3): " + table.min(0, 3));
        System.out.println("min(2, 3): " + table.min(2, 3));
        System.out.println("min(5, 7): " + table.min(5, 7));

        boolean ok = table.min(0, 3) == 2 && table.min(1, 6) == 1 && table.min(6, 6) == 6;
        try {
            table.min(5, 2);
            ok = false;
        } catch (IllegalArgumentException e) {
            // expected
        }
        ok &= new SparseTable(new int[]{42}).min(0, 0) == 42;

        // random arrays compared with a simple loop
        Random random = new Random(8);
        for (int trial = 0; trial < 40; trial++) {
            int n = 1 + random.nextInt(300);
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = random.nextInt(2001) - 1000;
            }
            SparseTable st = new SparseTable(data);
            for (int q = 0; q < 200; q++) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);
                int left = Math.min(a, b);
                int right = Math.max(a, b);
                int expected = Integer.MAX_VALUE;
                for (int i = left; i <= right; i++) {
                    expected = Math.min(expected, data[i]);
                }
                ok &= st.min(left, right) == expected;
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
