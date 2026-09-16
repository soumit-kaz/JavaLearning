import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class M1803L03_BinarySearchTree {

    // smaller keys go left, larger keys go right, no duplicates
    static class BST {
        static class Node {
            int key;
            Node left;
            Node right;

            Node(int key) {
                this.key = key;
            }
        }

        Node root;

        void insert(int key) {
            root = insert(root, key);
        }

        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }
            return node;
        }

        // every step discards one subtree
        boolean contains(int key) {
            Node node = root;
            while (node != null) {
                if (key == node.key) {
                    return true;
                }
                node = key < node.key ? node.left : node.right;
            }
            return false;
        }

        // min is the leftmost node, max is the rightmost
        int min() {
            Node node = root;
            while (node.left != null) {
                node = node.left;
            }
            return node.key;
        }

        int max() {
            Node node = root;
            while (node.right != null) {
                node = node.right;
            }
            return node.key;
        }

        void delete(int key) {
            root = delete(root, key);
        }

        Node delete(Node node, int key) {
            if (node == null) {
                return null;
            }
            if (key < node.key) {
                node.left = delete(node.left, key);
            } else if (key > node.key) {
                node.right = delete(node.right, key);
            } else if (node.left == null) {
                // leaf or only a right child
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // two children: copy the successor (smallest on the right), then delete it
                Node successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
            return node;
        }

        // inorder of a BST is sorted
        List<Integer> inorder() {
            List<Integer> out = new ArrayList<>();
            inorder(root, out);
            return out;
        }

        void inorder(Node node, List<Integer> out) {
            if (node != null) {
                inorder(node.left, out);
                out.add(node.key);
                inorder(node.right, out);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        for (int key : new int[]{50, 30, 70, 20, 40, 60, 80, 65}) {
            bst.insert(key);
        }
        System.out.println("inorder: " + bst.inorder());
        System.out.println("min: " + bst.min());
        System.out.println("max: " + bst.max());
        System.out.println("contains 60: " + bst.contains(60));
        System.out.println("contains 55: " + bst.contains(55));
        // 20 is a leaf, 60 has one child, 50 (the root) has two
        bst.delete(20);
        bst.delete(60);
        bst.delete(50);
        System.out.println("delete 20, 60, 50: " + bst.inorder());
        boolean ok = bst.inorder().equals(List.of(30, 40, 65, 70, 80)) && bst.root.key == 65;

        // random inserts and deletes compared with TreeSet
        Random random = new Random(18);
        BST tree = new BST();
        TreeSet<Integer> reference = new TreeSet<>();
        for (int i = 0; i < 5000; i++) {
            int key = random.nextInt(300);
            if (random.nextInt(3) == 0) {
                tree.delete(key);
                reference.remove(key);
            } else {
                tree.insert(key);
                reference.add(key);
            }
            ok &= tree.contains(key) == reference.contains(key);
        }
        ok &= tree.inorder().equals(new ArrayList<>(reference));
        ok &= tree.min() == reference.first() && tree.max() == reference.last();
        System.out.println(ok ? "OK" : "FAIL");
    }
}
