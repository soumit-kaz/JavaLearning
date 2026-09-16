import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class M1803P06_MaxPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static long best;

    static long maxPathSum(TreeNode root) {
        // start very low: in an all-negative tree the answer is negative
        best = Long.MIN_VALUE;
        gain(root);
        return best;
    }

    // best sum of a path that starts at node and goes down one side only
    static long gain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // a negative branch is never worth taking
        long left = Math.max(0, gain(node.left));
        long right = Math.max(0, gain(node.right));
        // the best path that turns at this node may use both sides
        best = Math.max(best, node.val + left + right);
        // but the parent can only extend one side
        return node.val + Math.max(left, right);
    }

    // slow check: try every pair of nodes as the two ends of the path
    static long bruteForce(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        collect(root, nodes);
        long result = Long.MIN_VALUE;
        for (TreeNode a : nodes) {
            for (TreeNode b : nodes) {
                List<TreeNode> pathA = new ArrayList<>();
                List<TreeNode> pathB = new ArrayList<>();
                pathTo(root, a, pathA);
                pathTo(root, b, pathB);
                int common = 0;
                while (common < pathA.size() && common < pathB.size() && pathA.get(common) == pathB.get(common)) {
                    common++;
                }
                // the turning node is pathA[common-1]; count it once
                long sum = pathA.get(common - 1).val;
                for (int i = common; i < pathA.size(); i++) {
                    sum += pathA.get(i).val;
                }
                for (int i = common; i < pathB.size(); i++) {
                    sum += pathB.get(i).val;
                }
                result = Math.max(result, sum);
            }
        }
        return result;
    }

    static void collect(TreeNode node, List<TreeNode> out) {
        if (node != null) {
            out.add(node);
            collect(node.left, out);
            collect(node.right, out);
        }
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

    static TreeNode randomTree(Random random, int depth) {
        if (depth == 0 || random.nextInt(4) == 0) {
            return null;
        }
        TreeNode node = new TreeNode(random.nextInt(41) - 20);
        node.left = randomTree(random, depth - 1);
        node.right = randomTree(random, depth - 1);
        return node;
    }

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Object[][] tests = {
                {new Integer[]{1, 2, 3}, 6L},
                {new Integer[]{-10, 9, 20, null, null, 15, 7}, 42L},
                {new Integer[]{-3}, -3L},
                {new Integer[]{-2, -1, -3}, -1L},
                {new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1}, 48L},
                {new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 3L * Integer.MAX_VALUE},
        };
        for (Object[] test : tests) {
            report(Arrays.toString((Integer[]) test[0]), maxPathSum(build((Integer[]) test[0])), test[1]);
        }
        Random random = new Random(6);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            TreeNode tree = randomTree(random, 5);
            if (tree != null) {
                match &= maxPathSum(tree) == bruteForce(tree);
            }
        }
        report("random 300 cases", match ? "match" : "differ", "match");
    }
}
