import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;

public class M1803P03_KthSmallestBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // inorder visits a BST in sorted order, so stop at the k-th visited node
    static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            k--;
            if (k == 0) {
                return current.val;
            }
            current = current.right;
        }
        throw new IllegalArgumentException("k is larger than the tree");
    }

    // follow-up: store subtree sizes so each query is O(height)
    static class SizedNode {
        int val;
        int size = 1;
        SizedNode left;
        SizedNode right;

        SizedNode(int val) {
            this.val = val;
        }
    }

    static int size(SizedNode node) {
        return node == null ? 0 : node.size;
    }

    static SizedNode insert(SizedNode node, int val) {
        if (node == null) {
            return new SizedNode(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    static int kthWithSizes(SizedNode node, int k) {
        while (true) {
            int leftSize = size(node.left);
            if (k == leftSize + 1) {
                return node.val;
            }
            if (k <= leftSize) {
                node = node.left;
            } else {
                // skip the left subtree and this node
                k -= leftSize + 1;
                node = node.right;
            }
        }
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

    static TreeNode insertPlain(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insertPlain(node.left, val);
        } else if (val > node.val) {
            node.right = insertPlain(node.right, val);
        }
        return node;
    }

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Object[][] tests = {
                {new Integer[]{3, 1, 4, null, 2}, 1, 1},
                {new Integer[]{5, 3, 6, 2, 4, null, null, 1}, 3, 3},
                {new Integer[]{5, 3, 6, 2, 4, null, null, 1}, 6, 6},
                {new Integer[]{7}, 1, 7},
        };
        for (Object[] test : tests) {
            Integer[] values = (Integer[]) test[0];
            int k = (Integer) test[1];
            report(Arrays.toString(values) + " k=" + k, kthSmallest(build(values), k), test[2]);
        }
        String outcome;
        try {
            kthSmallest(build(2, 1, 3), 4);
            outcome = "no exception";
        } catch (IllegalArgumentException e) {
            outcome = "IllegalArgumentException";
        }
        report("[2, 1, 3] k=4", outcome, "IllegalArgumentException");

        // compare both methods with the sorted order kept by a TreeSet
        Random random = new Random(3);
        boolean match = true;
        for (int t = 0; t < 100; t++) {
            TreeNode plain = null;
            SizedNode sized = null;
            TreeSet<Integer> reference = new TreeSet<>();
            int count = 1 + random.nextInt(100);
            for (int i = 0; i < count; i++) {
                int val = random.nextInt(1000);
                plain = insertPlain(plain, val);
                sized = insert(sized, val);
                reference.add(val);
            }
            List<Integer> sorted = new ArrayList<>(reference);
            for (int k = 1; k <= sorted.size(); k++) {
                match &= kthSmallest(plain, k) == sorted.get(k - 1) && kthWithSizes(sized, k) == sorted.get(k - 1);
            }
        }
        report("random 100 trees, every k", match ? "match" : "differ", "match");
    }
}
