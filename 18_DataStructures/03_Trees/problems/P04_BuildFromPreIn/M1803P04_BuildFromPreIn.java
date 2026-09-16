import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

public class M1803P04_BuildFromPreIn {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Builder {
        int[] preorder;
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        int next;

        Builder(int[] preorder, int[] inorder) {
            if (preorder.length != inorder.length) {
                throw new IllegalArgumentException("different lengths");
            }
            this.preorder = preorder;
            for (int i = 0; i < inorder.length; i++) {
                // a repeated value would make the root position ambiguous
                if (inorderIndex.put(inorder[i], i) != null) {
                    throw new IllegalArgumentException("duplicate value");
                }
            }
        }

        // build the subtree made of inorder[lo..hi]
        TreeNode build(int lo, int hi) {
            if (lo > hi) {
                return null;
            }
            // the next preorder value is always the root of the current subtree
            int value = preorder[next++];
            Integer mid = inorderIndex.get(value);
            if (mid == null || mid < lo || mid > hi) {
                throw new IllegalArgumentException("traversals do not match");
            }
            TreeNode node = new TreeNode(value);
            node.left = build(lo, mid - 1);
            node.right = build(mid + 1, hi);
            return node;
        }
    }

    static TreeNode buildTree(int[] preorder, int[] inorder) {
        return new Builder(preorder, inorder).build(0, inorder.length - 1);
    }

    static List<String> levelOrder(TreeNode root) {
        List<String> out = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.getLast().equals("null")) {
            out.removeLast();
        }
        return out;
    }

    static void preorder(TreeNode node, List<Integer> out) {
        if (node != null) {
            out.add(node.val);
            preorder(node.left, out);
            preorder(node.right, out);
        }
    }

    static void inorder(TreeNode node, List<Integer> out) {
        if (node != null) {
            inorder(node.left, out);
            out.add(node.val);
            inorder(node.right, out);
        }
    }

    static int[] toArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    static TreeNode randomTree(Random random, int size) {
        TreeNode root = null;
        for (int v = 0; v < size; v++) {
            TreeNode fresh = new TreeNode(v);
            if (root == null) {
                root = fresh;
                continue;
            }
            TreeNode current = root;
            while (true) {
                if (random.nextBoolean()) {
                    if (current.left == null) {
                        current.left = fresh;
                        break;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = fresh;
                        break;
                    }
                    current = current.right;
                }
            }
        }
        return root;
    }

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Object[][] tests = {
                {new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}, "[3, 9, 20, null, null, 15, 7]"},
                {new int[]{-1}, new int[]{-1}, "[-1]"},
                {new int[]{}, new int[]{}, "[]"},
                {new int[]{1, 2, 3}, new int[]{3, 2, 1}, "[1, 2, null, 3]"},
                {new int[]{1, 2, 3}, new int[]{1, 2, 3}, "[1, null, 2, null, 3]"},
                {new int[]{1, 1}, new int[]{1, 1}, "IllegalArgumentException"},
                {new int[]{1, 2}, new int[]{1, 3}, "IllegalArgumentException"},
        };
        for (Object[] test : tests) {
            int[] pre = (int[]) test[0];
            int[] in = (int[]) test[1];
            String output;
            try {
                output = levelOrder(buildTree(pre, in)).toString();
            } catch (IllegalArgumentException e) {
                output = "IllegalArgumentException";
            }
            report("pre=" + Arrays.toString(pre) + " in=" + Arrays.toString(in), output, test[2]);
        }
        // flatten random trees into two traversals and rebuild them
        Random random = new Random(4);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            TreeNode tree = randomTree(random, random.nextInt(80));
            List<Integer> pre = new ArrayList<>();
            List<Integer> in = new ArrayList<>();
            preorder(tree, pre);
            inorder(tree, in);
            match &= levelOrder(tree).equals(levelOrder(buildTree(toArray(pre), toArray(in))));
        }
        report("random 300 cases", match ? "match" : "differ", "match");
    }
}
