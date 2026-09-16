import java.util.Random;

public class M1807L15_LongestIncreasingSubsequence {

    // O(n^2): len[i] = longest strictly increasing run that ends at a[i]
    static int lisQuadratic(int[] a) {
        int[] len = new int[a.length];
        int best = 0;
        for (int i = 0; i < a.length; i++) {
            len[i] = 1;
            // extend any earlier, smaller value
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
            best = Math.max(best, len[i]);
        }
        return best;
    }

    // O(n log n): tails[k] = smallest possible last value of an increasing run of length k + 1
    static int lisFast(int[] a) {
        int[] tails = new int[a.length];
        int size = 0;
        for (int x : a) {
            // lower bound: first tail >= x
            int low = 0;
            int high = size;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            // x either extends the longest run or gives a smaller tail
            tails[low] = x;
            if (low == size) {
                size++;
            }
        }
        return size;
    }

    // brute force: check every subset
    static int lisBrute(int[] a) {
        int best = 0;
        for (int mask = 0; mask < (1 << a.length); mask++) {
            int count = 0;
            int last = Integer.MIN_VALUE;
            boolean increasing = true;
            for (int i = 0; i < a.length && increasing; i++) {
                if ((mask & (1 << i)) != 0) {
                    increasing = count == 0 || a[i] > last;
                    last = a[i];
                    count++;
                }
            }
            if (increasing) {
                best = Math.max(best, count);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] a = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("lis -> " + lisQuadratic(a) + " " + lisFast(a));

        // equal values do not count as increasing
        boolean ok = lisFast(new int[]{7, 7, 7}) == 1 && lisFast(new int[0]) == 0 && lisFast(a) == 4;

        // random arrays compared with brute force
        Random random = new Random(15);
        for (int t = 0; t < 300; t++) {
            int[] b = new int[random.nextInt(13)];
            for (int i = 0; i < b.length; i++) {
                b[i] = random.nextInt(10);
            }
            int expected = lisBrute(b);
            ok &= lisQuadratic(b) == expected && lisFast(b) == expected;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
