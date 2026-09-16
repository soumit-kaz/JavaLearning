import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class M1806P01_NumberOfProvinces {

    static class DisjointSet {
        private final int[] parent;
        private final int[] rank;
        private int sets;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                // path halving: skip one level on the way up
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            // attach the shorter tree under the taller one
            if (rank[rootA] < rank[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            if (rank[rootA] == rank[rootB]) {
                rank[rootA]++;
            }
            sets--;
        }
    }

    static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet ds = new DisjointSet(n);
        // the matrix is symmetric, so the upper half is enough
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    ds.union(i, j);
                }
            }
        }
        return ds.sets;
    }

    static int bfsCount(int[][] graph) {
        int n = graph.length;
        boolean[] seen = new boolean[n];
        int count = 0;
        for (int start = 0; start < n; start++) {
            if (seen[start]) {
                continue;
            }
            count++;
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            seen[start] = true;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] == 1 && !seen[v]) {
                        seen[v] = true;
                        queue.add(v);
                    }
                }
            }
        }
        return count;
    }

    static void check(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        int[][] a = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] b = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        int[][] c = {{1}};
        int[][] d = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        check(Arrays.deepToString(a), findCircleNum(a), 2);
        check(Arrays.deepToString(b), findCircleNum(b), 3);
        check(Arrays.deepToString(c), findCircleNum(c), 1);
        check(Arrays.deepToString(d), findCircleNum(d), 1);
        check("[]", findCircleNum(new int[0][0]), 0);

        Random random = new Random(3);
        boolean allMatch = true;
        for (int t = 0; t < 300; t++) {
            int n = 1 + random.nextInt(40);
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                graph[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (random.nextInt(20) == 0) {
                        graph[i][j] = 1;
                        graph[j][i] = 1;
                    }
                }
            }
            if (findCircleNum(graph) != bfsCount(graph)) {
                allMatch = false;
            }
        }
        check("random 300 graphs vs BFS", allMatch ? "match" : "differ", "match");
    }
}
