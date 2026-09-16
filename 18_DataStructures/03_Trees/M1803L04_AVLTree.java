import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class M1803L04_AVLTree {

    // a BST that rotates so left and right heights differ by at most 1
    static class AVL {
        static class Node {
            int key;
            int height = 1;
            Node left;
            Node right;

            Node(int key) {
                this.key = key;
            }
        }

        Node root;
        List<Integer> inorder = new ArrayList<>();

        static int height(Node node) {
            return node == null ? 0 : node.height;
        }

        // positive means left-heavy, negative means right-heavy
        static int balance(Node node) {
            return height(node.left) - height(node.right);
        }

        static void update(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        //     y            x
        //    / \          / \
        //   x   C  -->   A   y
        //  / \              / \
        // A   B            B   C
        static Node rotateRight(Node y) {
            Node x = y.left;
            y.left = x.right;
            x.right = y;
            // y is now below x, so update y first
            update(y);
            update(x);
            return x;
        }

        static Node rotateLeft(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            update(x);
            update(y);
            return y;
        }

        // fix a node whose balance may have become +2 or -2
        static Node rebalance(Node node) {
            update(node);
            if (balance(node) > 1) {
                // left-right case: first turn it into left-left
                if (balance(node.left) < 0) {
                    node.left = rotateLeft(node.left);
                }
                return rotateRight(node);
            }
            if (balance(node) < -1) {
                // right-left case: first turn it into right-right
                if (balance(node.right) > 0) {
                    node.right = rotateRight(node.right);
                }
                return rotateLeft(node);
            }
            return node;
        }

        void insert(int key) {
            root = insert(root, key);
        }

        // normal BST insert, then rebalance every node on the way back up
        static Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node;
            }
            return rebalance(node);
        }

        // inorder walk that records keys and returns the height, or -1 if a node is unbalanced
        int check(Node node) {
            if (node == null) {
                return 0;
            }
            int left = check(node.left);
            inorder.add(node.key);
            int right = check(node.right);
            if (left < 0 || right < 0 || Math.abs(left - right) > 1 || node.height != 1 + Math.max(left, right)) {
                return -1;
            }
            return node.height;
        }
    }

    public static void main(String[] args) {
        // sorted input 1..7 would make a plain BST a chain of height 7
        AVL avl = new AVL();
        for (int key = 1; key <= 7; key++) {
            avl.insert(key);
        }
        System.out.println("root: " + avl.root.key);
        System.out.println("height: " + avl.root.height);
        boolean ok = avl.root.key == 4 && avl.root.height == 3;
        // LL, RR, LR and RL cases all end with 2 at the root
        for (int[] keys : new int[][]{{3, 2, 1}, {1, 2, 3}, {3, 1, 2}, {1, 3, 2}}) {
            AVL small = new AVL();
            for (int key : keys) {
                small.insert(key);
            }
            ok &= small.root.key == 2 && small.root.height == 2;
        }
        // 100000 sorted keys: height stays about 1.44 * log2(n)
        AVL big = new AVL();
        for (int key = 0; key < 100000; key++) {
            big.insert(key);
        }
        System.out.println("height of 100000: " + big.root.height);
        ok &= big.check(big.root) >= 0 && big.root.height <= 25;
        // random keys compared with TreeSet
        Random random = new Random(18);
        AVL tree = new AVL();
        TreeSet<Integer> reference = new TreeSet<>();
        for (int i = 0; i < 5000; i++) {
            int key = random.nextInt(2000);
            tree.insert(key);
            reference.add(key);
        }
        ok &= tree.check(tree.root) >= 0 && tree.inorder.equals(new ArrayList<>(reference));
        System.out.println(ok ? "OK" : "FAIL");
    }
}
