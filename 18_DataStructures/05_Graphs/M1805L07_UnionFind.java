import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class M1805L07_UnionFind {

    // disjoint set union with path compression and union by rank
    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private final int[] size;
        private int count;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            size = new int[n];
            // every element starts in its own set
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            count = n;
        }

        int find(int x) {
            // path compression: point x straight at the root
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // returns false if a and b were already in the same set
        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return false;
            }
            // union by rank: hang the shorter tree under the taller one
            if (rank[ra] < rank[rb]) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent[rb] = ra;
            size[ra] += size[rb];
            if (rank[ra] == rank[rb]) {
                rank[ra]++;
            }
            count--;
            return true;
        }

        boolean connected(int a, int b) {
            return find(a) == find(b);
        }

        int sizeOf(int x) {
            return size[find(x)];
        }

        int count() {
            return count;
        }
    }

    // an undirected graph has a cycle if an edge joins two vertices already connected
    static boolean hasCycle(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            if (!uf.union(e[0], e[1])) {
                return true;
            }
        }
        return false;
    }

    // check only: BFS to test if b is reachable from a
    static boolean reachable(List<List<Integer>> adj, int a, int b) {
        boolean[] seen = new boolean[adj.size()];
        Deque<Integer> queue = new ArrayDeque<>();
        seen[a] = true;
        queue.add(a);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (!seen[v]) {
                    seen[v] = true;
                    queue.add(v);
                }
            }
        }
        return seen[b];
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        System.out.println("sets: " + uf.count());
        System.out.println("size of 0: " + uf.sizeOf(0));
        System.out.println("0~2: " + uf.connected(0, 2));
        System.out.println("0~3: " + uf.connected(0, 3));
        int[][] tree = {{0, 1}, {1, 2}, {1, 3}};
        int[][] loop = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("tree has cycle: " + hasCycle(4, tree));
        System.out.println("loop has cycle: " + hasCycle(3, loop));

        boolean ok = uf.count() == 3 && uf.sizeOf(2) == 3 && !uf.union(0, 2)
                && !hasCycle(4, tree) && hasCycle(3, loop);
        // self-check: connectivity, sizes and set count match BFS on random graphs
        Random rnd = new Random(7);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(12);
            UnionFind u = new UnionFind(n);
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int k = rnd.nextInt(2 * n); k > 0; k--) {
                int a = rnd.nextInt(n);
                int b = rnd.nextInt(n);
                u.union(a, b);
                adj.get(a).add(b);
                adj.get(b).add(a);
            }
            int roots = 0;
            for (int a = 0; a < n; a++) {
                roots += u.find(a) == a ? 1 : 0;
                int same = 0;
                for (int b = 0; b < n; b++) {
                    boolean r = reachable(adj, a, b);
                    ok &= u.connected(a, b) == r;
                    same += r ? 1 : 0;
                }
                ok &= u.sizeOf(a) == same;
            }
            ok &= roots == u.count();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
