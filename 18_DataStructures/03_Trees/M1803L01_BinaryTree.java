import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class M1803L01_BinaryTree {

    // each node has at most two children
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // build a tree from level order values, null means no child
    static Node build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        Node root = new Node(values[0]);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            Node parent = queue.poll();
            if (values[i] != null) {
                parent.left = new Node(values[i]);
                queue.add(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new Node(values[i]);
                queue.add(parent.right);
            }
            i++;
        }
        return root;
    }

    // number of nodes
    static int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    // height counted in nodes: empty tree 0, single node 1
    static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // longest path in edges between any two nodes
    static int diameter(Node root) {
        int[] best = new int[1];
        depth(root, best);
        return best[0];
    }

    // returns the height and records the best path that turns at each node
    static int depth(Node node, int[] best) {
        if (node == null) {
            return 0;
        }
        int left = depth(node.left, best);
        int right = depth(node.right, best);
        best[0] = Math.max(best[0], left + right);
        return 1 + Math.max(left, right);
    }

    // lowest common ancestor; assumes both nodes are in the tree
    static Node lca(Node node, Node a, Node b) {
        if (node == null || node == a || node == b) {
            return node;
        }
        Node left = lca(node.left, a, b);
        Node right = lca(node.right, a, b);
        // one found on each side: the paths split here
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    // slow lca check: fills the root-to-goal path
    static boolean pathTo(Node node, Node goal, List<Node> path) {
        if (node == null) {
            return false;
        }
        path.add(node);
        if (node == goal || pathTo(node.left, goal, path) || pathTo(node.right, goal, path)) {
            return true;
        }
        path.removeLast();
        return false;
    }

    // slow lca: last common node of the two paths
    static Node lcaByPaths(Node root, Node a, Node b) {
        List<Node> pa = new ArrayList<>();
        List<Node> pb = new ArrayList<>();
        pathTo(root, a, pa);
        pathTo(root, b, pb);
        int i = 0;
        while (i < pa.size() && i < pb.size() && pa.get(i) == pb.get(i)) {
            i++;
        }
        return pa.get(i - 1);
    }

    // random tree: each new node hangs under a random free slot
    static List<Node> randomTree(Random random, int n) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0));
        for (int v = 1; v < n; v++) {
            Node child = new Node(v);
            while (true) {
                Node p = nodes.get(random.nextInt(nodes.size()));
                if (p.left == null && random.nextBoolean()) {
                    p.left = child;
                    break;
                }
                if (p.right == null) {
                    p.right = child;
                    break;
                }
            }
            nodes.add(child);
        }
        return nodes;
    }

    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        //   /     \
        //  6       7
        Node root = build(1, 2, 3, 4, 5, null, null, 6, null, null, 7);
        Node n2 = root.left;
        Node n3 = root.right;
        Node n4 = n2.left;
        Node n6 = n4.left;
        Node n7 = n2.right.right;
        System.out.println("size: " + size(root));
        System.out.println("height: " + height(root));
        System.out.println("diameter: " + diameter(root));
        System.out.println("lca(6, 7): " + lca(root, n6, n7).val);
        System.out.println("lca(6, 3): " + lca(root, n6, n3).val);

        // the longest path 6-4-2-5-7 does not pass the root
        boolean ok = size(root) == 7 && height(root) == 4 && diameter(root) == 4;
        ok &= size(null) == 0 && height(null) == 0 && diameter(null) == 0 && build() == null;
        ok &= n6.val == 6 && n7.val == 7;
        ok &= lca(root, n6, n7) == n2 && lca(root, n4, n6) == n4 && lca(root, n7, n7) == n7;

        // random trees: recursive lca must match the path version
        Random random = new Random(18);
        for (int t = 0; t < 200; t++) {
            List<Node> nodes = randomTree(random, 1 + random.nextInt(50));
            Node a = nodes.get(random.nextInt(nodes.size()));
            Node b = nodes.get(random.nextInt(nodes.size()));
            ok &= lca(nodes.getFirst(), a, b) == lcaByPaths(nodes.getFirst(), a, b);
            ok &= size(nodes.getFirst()) == nodes.size();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
