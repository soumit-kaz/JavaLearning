import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M1807L13_DynamicProgramming {

    // tabulation: fill fib[0..n] from small to large
    static long fibTable(int n) {
        if (n < 2) {
            return n;
        }
        long[] fib = new long[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    // only the last two values are ever read, so two variables are enough
    static long fibTwoVars(int n) {
        long previous = 0;
        long current = 1;
        for (int i = 0; i < n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return previous;
    }

    // plain recursion: exponential time, used only as a check
    static long fibSlow(int n) {
        return n < 2 ? n : fibSlow(n - 1) + fibSlow(n - 2);
    }

    // 0/1 knapsack: best value within capacity; indexes of chosen items go into chosen
    static int knapsack(int[] weight, int[] value, int capacity, List<Integer> chosen) {
        int n = weight.length;
        // best[i][c] = best value using the first i items with capacity c
        int[][] best = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 0; c <= capacity; c++) {
                // skip item i - 1
                best[i][c] = best[i - 1][c];
                // or take it, if it fits
                if (weight[i - 1] <= c) {
                    best[i][c] = Math.max(best[i][c], best[i - 1][c - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        // walk back: a changed value means the item was taken
        int c = capacity;
        for (int i = n; i > 0; i--) {
            if (best[i][c] != best[i - 1][c]) {
                chosen.add(0, i - 1);
                c -= weight[i - 1];
            }
        }
        return best[n][capacity];
    }

    // one-row version: go through capacities backwards so each item is used at most once
    static int knapsackOneRow(int[] weight, int[] value, int capacity) {
        int[] best = new int[capacity + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int c = capacity; c >= weight[i]; c--) {
                best[c] = Math.max(best[c], best[c - weight[i]] + value[i]);
            }
        }
        return best[capacity];
    }

    // brute force: try every subset of items
    static int knapsackBrute(int[] weight, int[] value, int capacity) {
        int best = 0;
        for (int mask = 0; mask < (1 << weight.length); mask++) {
            int w = 0;
            int v = 0;
            for (int i = 0; i < weight.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    w += weight[i];
                    v += value[i];
                }
            }
            if (w <= capacity) {
                best = Math.max(best, v);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("fib(50) -> " + fibTable(50));
        int[] weight = {1, 3, 4, 5};
        int[] value = {1, 4, 5, 7};
        List<Integer> chosen = new ArrayList<>();
        System.out.println("knapsack cap 7 -> " + knapsack(weight, value, 7, chosen) + " items " + chosen);

        boolean ok = fibTable(90) == 2880067194370816120L && fibTwoVars(90) == fibTable(90);
        for (int n = 0; n <= 25; n++) {
            ok &= fibTable(n) == fibSlow(n) && fibTwoVars(n) == fibSlow(n);
        }

        // random item sets compared with brute force
        Random random = new Random(13);
        for (int t = 0; t < 300; t++) {
            int n = random.nextInt(10);
            int[] w = new int[n];
            int[] v = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = 1 + random.nextInt(10);
                v[i] = random.nextInt(20);
            }
            int capacity = random.nextInt(30);
            List<Integer> picked = new ArrayList<>();
            int best = knapsack(w, v, capacity, picked);
            ok &= best == knapsackBrute(w, v, capacity) && best == knapsackOneRow(w, v, capacity);
            // the chosen items must fit and add up to the best value
            int totalW = 0;
            int totalV = 0;
            for (int i : picked) {
                totalW += w[i];
                totalV += v[i];
            }
            ok &= totalW <= capacity && totalV == best;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
