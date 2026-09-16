import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1805P04_NetworkDelayTime {

    static int networkDelayTime(int[][] times, int n, int k) {
        // nodes are numbered 1..n, so use size n + 1
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] t : times) {
            adj.get(t[0]).add(new int[]{t[1], t[2]});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        // Dijkstra: always continue from the closest unfinished node
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.add(new int[]{0, k});
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int node = top[1];
            // skip outdated heap entries
            if (top[0] > dist[node]) {
                continue;
            }
            for (int[] e : adj.get(node)) {
                int arrive = top[0] + e[1];
                if (arrive < dist[e[0]]) {
                    dist[e[0]] = arrive;
                    heap.add(new int[]{arrive, e[0]});
                }
            }
        }
        // everyone has the signal when the slowest node gets it
        int answer = 0;
        for (int v = 1; v <= n; v++) {
            if (dist[v] == Integer.MAX_VALUE) {
                return -1;
            }
            answer = Math.max(answer, dist[v]);
        }
        return answer;
    }

    static int bellmanFord(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        for (int round = 1; round < n; round++) {
            for (int[] t : times) {
                if (dist[t[0]] != Integer.MAX_VALUE && dist[t[0]] + t[2] < dist[t[1]]) {
                    dist[t[1]] = dist[t[0]] + t[2];
                }
            }
        }
        int answer = 0;
        for (int v = 1; v <= n; v++) {
            if (dist[v] == Integer.MAX_VALUE) {
                return -1;
            }
            answer = Math.max(answer, dist[v]);
        }
        return answer;
    }

    static void test(int[][] times, int n, int k, int expected) {
        int got = networkDelayTime(times, n, k);
        System.out.println("times=" + Arrays.deepToString(times) + " n=" + n + " k=" + k + " -> " + got
                + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2, 2);
        test(new int[][]{{1, 2, 1}}, 2, 1, 1);
        test(new int[][]{{1, 2, 1}}, 2, 2, -1);
        test(new int[][]{}, 1, 1, 0);
        test(new int[][]{{1, 2, 10}, {1, 3, 1}, {3, 2, 1}}, 3, 1, 2);

        // Dijkstra must agree with Bellman-Ford
        Random rnd = new Random(6);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(10);
            int[][] times = new int[rnd.nextInt(3 * n)][];
            for (int i = 0; i < times.length; i++) {
                times[i] = new int[]{1 + rnd.nextInt(n), 1 + rnd.nextInt(n), rnd.nextInt(101)};
            }
            int k = 1 + rnd.nextInt(n);
            match &= networkDelayTime(times, n, k) == bellmanFord(times, n, k);
        }
        System.out.println("random 300 cases Dijkstra vs Bellman-Ford -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
