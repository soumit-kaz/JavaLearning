import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;

public class M1803P02_SortedArrayToBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    // the middle value becomes the root, so both halves have almost the same size
    static TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, lo, mid - 1);
        node.right = build(nums, mid + 1, hi);
        return node;
    }

    // level order with "null" gaps, trailing nulls removed
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

    // returns the height, or -1 if some node is unbalanced
    static int balancedHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = balancedHeight(node.left);
        int right = balancedHeight(node.right);
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    static void inorder(TreeNode node, List<Integer> out) {
        if (node != null) {
            inorder(node.left, out);
            out.add(node.val);
            inorder(node.right, out);
        }
    }

    static void report(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Object[][] tests = {
                {new int[]{-10, -3, 0, 5, 9}, "[0, -10, 5, null, -3, null, 9]"},
                {new int[]{1, 3}, "[1, null, 3]"},
                {new int[]{}, "[]"},
                {new int[]{42}, "[42]"},
                {new int[]{1, 2, 3, 4, 5, 6, 7}, "[4, 2, 6, 1, 3, 5, 7]"},
        };
        for (Object[] test : tests) {
            int[] nums = (int[]) test[0];
            report(Arrays.toString(nums), levelOrder(sortedArrayToBST(nums)).toString(), test[1]);
        }
        // random sorted arrays: the result must be balanced and keep the same order
        Random random = new Random(2);
        boolean match = true;
        for (int t = 0; t < 200; t++) {
            TreeSet<Integer> unique = new TreeSet<>();
            int count = random.nextInt(300);
            for (int i = 0; i < count; i++) {
                unique.add(random.nextInt(20001) - 10000);
            }
            int[] nums = new int[unique.size()];
            List<Integer> expected = new ArrayList<>();
            int k = 0;
            for (int value : unique) {
                nums[k++] = value;
                expected.add(value);
            }
            TreeNode root = sortedArrayToBST(nums);
            List<Integer> actual = new ArrayList<>();
            inorder(root, actual);
            match &= balancedHeight(root) >= 0 && actual.equals(expected);
        }
        report("random 200 cases", match ? "match" : "differ", "match");
    }
}
