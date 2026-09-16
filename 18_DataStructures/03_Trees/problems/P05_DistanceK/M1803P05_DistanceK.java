import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class M1803P05_DistanceK {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // remember each node's parent so we can also walk upward
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            for (TreeNode child : new TreeNode[]{node.left, node.right}) {
                if (child != null) {
                    parent.put(child, node);
                    queue.add(child);
                }
            }
        }
        // BFS from the target, one level (one edge of distance) at a time
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        queue.add(target);
        for (int distance = 0; distance < k && !queue.isEmpty(); distance++) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                for (TreeNode next : new TreeNode[]{node.left, node.right, parent.get(node)}) {
                    // visited stops us from walking back where we came from
                    if (next != null && visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (TreeNode node : queue) {
            result.add(node.val);
        }
        Collections.sort(result);
        return result;
    }

    static TreeNode build(Integer... values) {
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode parent = queue.poll();
            if (i < values.length && values[i] != null) {
                parent.left = new TreeNode(values[i]);
                queue.add(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new TreeNode(values[i]);
                queue.add(parent.right);
            }
            i++;
        }
        return root;
    }

    static TreeNode find(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        }
        TreeNode left = find(node.left, val);
        return left != null ? left : find(node.right, val);
    }

    // slow check: distance(a, b) = depth(a) + depth(b) - 2 * depth(lowest common ancestor)
    static List<Integer> bruteForce(TreeNode root, TreeNode target, int k, List<TreeNode> all) {
        List<TreeNode> targetPath = new ArrayList<>();
        pathTo(root, target, targetPath);
        List<Integer> result = new ArrayList<>();
        for (TreeNode node : all) {
            List<TreeNode> path = new ArrayList<>();
            pathTo(root, node, path);
            int common = 0;
            while (common < path.size() && common < targetPath.size() && path.get(common) == targetPath.get(common)) {
                common++;
            }
            if (path.size() + targetPath.size() - 2 * common == k) {
                result.add(node.val);
            }
        }
        Collections.sort(result);
        return result;
    }

    static boolean pathTo(TreeNode node, TreeNode goal, List<TreeNode> path) {
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

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Integer[] sample = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        int[][] tests = {{5, 2}, {5, 0}, {3, 3}, {7, 4}, {5, 9}};
        List<List<Integer>> expected = List.of(List.of(1, 4, 7), List.of(5), List.of(4, 7), List.of(1), List.of());
        for (int i = 0; i < tests.length; i++) {
            TreeNode root = build(sample);
            List<Integer> answer = distanceK(root, find(root, tests[i][0]), tests[i][1]);
            report("[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4] target=" + tests[i][0] + " k=" + tests[i][1], answer, expected.get(i));
        }
        TreeNode single = build(1);
        report("[1] target=1 k=3", distanceK(single, single, 3), List.of());

        Random random = new Random(5);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int n = 1 + random.nextInt(40);
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(new TreeNode(0));
            for (int v = 1; v < n; v++) {
                TreeNode child = new TreeNode(v);
                while (true) {
                    TreeNode p = nodes.get(random.nextInt(nodes.size()));
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
            TreeNode target = nodes.get(random.nextInt(n));
            int k = random.nextInt(8);
            match &= distanceK(nodes.getFirst(), target, k).equals(bruteForce(nodes.getFirst(), target, k, nodes));
        }
        report("random 300 cases", match ? "match" : "differ", "match");
    }
}
