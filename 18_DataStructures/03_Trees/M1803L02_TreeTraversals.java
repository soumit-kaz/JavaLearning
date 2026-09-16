import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class M1803L02_TreeTraversals {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // node, left, right
    static void preorder(Node node, List<Integer> out) {
        if (node != null) {
            out.add(node.val);
            preorder(node.left, out);
            preorder(node.right, out);
        }
    }

    // left, node, right
    static void inorder(Node node, List<Integer> out) {
        if (node != null) {
            inorder(node.left, out);
            out.add(node.val);
            inorder(node.right, out);
        }
    }

    // left, right, node
    static void postorder(Node node, List<Integer> out) {
        if (node != null) {
            postorder(node.left, out);
            postorder(node.right, out);
            out.add(node.val);
        }
    }

    // preorder with a stack: push right first so left comes out first
    static List<Integer> preorderIterative(Node root) {
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            out.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return out;
    }

    // inorder with a stack: no stack overflow on deep trees
    static List<Integer> inorderIterative(Node root) {
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            // go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            out.add(current.val);
            // then handle the right subtree
            current = current.right;
        }
        return out;
    }

    // postorder with a stack: node, right, left reversed is left, right, node
    static List<Integer> postorderIterative(Node root) {
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            out.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(out);
        return out;
    }

    // breadth-first, one inner list per level
    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // everything in the queue now is on the same level
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }

    // random tree for the self-check
    static Node randomTree(Random random, int n) {
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
        return nodes.getFirst();
    }

    public static void main(String[] args) {
        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        preorder(root, pre);
        inorder(root, in);
        postorder(root, post);
        System.out.println("preorder: " + pre);
        System.out.println("inorder: " + in);
        System.out.println("postorder: " + post);
        System.out.println("level order: " + levelOrder(root));

        boolean ok = pre.equals(List.of(1, 2, 4, 5, 3, 6))
                && in.equals(List.of(4, 2, 5, 1, 3, 6))
                && post.equals(List.of(4, 5, 2, 6, 3, 1))
                && levelOrder(root).equals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6)))
                && levelOrder(null).isEmpty()
                && inorderIterative(null).isEmpty();

        // random trees: stack versions must match the recursive ones
        Random random = new Random(18);
        for (int t = 0; t < 200; t++) {
            Node tree = randomTree(random, 1 + random.nextInt(50));
            List<Integer> p = new ArrayList<>();
            List<Integer> i = new ArrayList<>();
            List<Integer> q = new ArrayList<>();
            preorder(tree, p);
            inorder(tree, i);
            postorder(tree, q);
            ok &= preorderIterative(tree).equals(p) && inorderIterative(tree).equals(i)
                    && postorderIterative(tree).equals(q);
        }

        // a 100000-node left chain would overflow recursion; the stack version is fine
        Node deep = null;
        for (int i = 100000; i >= 1; i--) {
            Node node = new Node(i);
            node.left = deep;
            deep = node;
        }
        List<Integer> deepOrder = inorderIterative(deep);
        ok &= deepOrder.size() == 100000 && deepOrder.getFirst() == 100000;
        System.out.println(ok ? "OK" : "FAIL");
    }
}
