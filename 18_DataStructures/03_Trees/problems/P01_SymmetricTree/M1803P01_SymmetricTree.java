import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class M1803P01_SymmetricTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    // two subtrees are mirrors if their outer and inner children match crosswise
    static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    // same idea with a queue: nodes are added and removed in pairs
    static boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        // LinkedList is used because ArrayDeque does not accept null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();
            if (a == null && b == null) {
                continue;
            }
            if (a == null || b == null || a.val != b.val) {
                return false;
            }
            queue.add(a.left);
            queue.add(b.right);
            queue.add(a.right);
            queue.add(b.left);
        }
        return true;
    }

    static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
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
        TreeNode node = new TreeNode(random.nextInt(3));
        node.left = randomTree(random, depth - 1);
        node.right = randomTree(random, depth - 1);
        return node;
    }

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Object[][] tests = {
                {new Integer[]{1, 2, 2, 3, 4, 4, 3}, true},
                {new Integer[]{1, 2, 2, null, 3, null, 3}, false},
                {new Integer[]{}, true},
                {new Integer[]{7}, true},
                {new Integer[]{1, 2, 3}, false},
                {new Integer[]{1, 2, 2, null, 3, 3, null}, true},
        };
        for (Object[] test : tests) {
            TreeNode root = build((Integer[]) test[0]);
            boolean answer = isSymmetric(root);
            // both versions must agree before we report the answer
            report(Arrays.toString((Integer[]) test[0]), answer == isSymmetricIterative(root) ? answer : "disagree", test[1]);
        }
        Random random = new Random(1);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            TreeNode tree = randomTree(random, 5);
            match &= isSymmetric(tree) == isSymmetricIterative(tree);
        }
        report("random 300 cases", match ? "match" : "differ", "match");
    }
}
