import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class M1806L02_SkipList {

    static class SkipList {
        static final int MAX_LEVEL = 16;

        static class Node {
            int value;
            // next[i] is the following node on level i
            Node[] next;

            Node(int value, int levels) {
                this.value = value;
                this.next = new Node[levels];
            }
        }

        // the head holds no real value and is as tall as any node can be
        private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        private final Random random;
        private int levels = 1;
        private int size;

        SkipList(long seed) {
            random = new Random(seed);
        }

        private int randomLevel() {
            // flip a coin: each extra level has a 50% chance
            int level = 1;
            while (level < MAX_LEVEL && random.nextBoolean()) {
                level++;
            }
            return level;
        }

        private Node[] findPredecessors(int value) {
            Node[] update = new Node[MAX_LEVEL];
            Node current = head;
            // start at the top level and drop down one level at a time
            for (int i = levels - 1; i >= 0; i--) {
                while (current.next[i] != null && current.next[i].value < value) {
                    current = current.next[i];
                }
                // remember the last node before 'value' on this level
                update[i] = current;
            }
            return update;
        }

        boolean search(int value) {
            Node candidate = findPredecessors(value)[0].next[0];
            return candidate != null && candidate.value == value;
        }

        boolean insert(int value) {
            Node[] update = findPredecessors(value);
            Node candidate = update[0].next[0];
            // this skip list is a set, so ignore duplicates
            if (candidate != null && candidate.value == value) {
                return false;
            }
            int level = randomLevel();
            // brand new levels start right after the head
            for (int i = levels; i < level; i++) {
                update[i] = head;
            }
            levels = Math.max(levels, level);
            Node node = new Node(value, level);
            // splice the node into each of its levels
            for (int i = 0; i < level; i++) {
                node.next[i] = update[i].next[i];
                update[i].next[i] = node;
            }
            size++;
            return true;
        }

        boolean erase(int value) {
            Node[] update = findPredecessors(value);
            Node target = update[0].next[0];
            if (target == null || target.value != value) {
                return false;
            }
            // unlink the node on every level it appears on
            for (int i = 0; i < target.next.length; i++) {
                update[i].next[i] = target.next[i];
            }
            // lower the height if the top levels are now empty
            while (levels > 1 && head.next[levels - 1] == null) {
                levels--;
            }
            size--;
            return true;
        }

        List<Integer> toList() {
            List<Integer> result = new ArrayList<>();
            for (Node n = head.next[0]; n != null; n = n.next[0]) {
                result.add(n.value);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList(2);
        for (int v : new int[]{30, 10, 50, 20, 40}) {
            list.insert(v);
        }
        System.out.println("list: " + list.toList());
        System.out.println("search(40): " + list.search(40));
        System.out.println("search(45): " + list.search(45));
        System.out.println("erase(10): " + list.erase(10));
        System.out.println("erase(99): " + list.erase(99));

        boolean ok = !list.insert(40) && list.toList().equals(List.of(20, 30, 40, 50));

        // random operations compared with TreeSet
        Random random = new Random(31337);
        SkipList mine = new SkipList(777);
        TreeSet<Integer> ref = new TreeSet<>();
        for (int op = 0; op < 20000; op++) {
            int v = random.nextInt(3000);
            int kind = random.nextInt(4);
            if (kind <= 1) {
                ok &= mine.insert(v) == ref.add(v);
            } else if (kind == 2) {
                ok &= mine.erase(v) == ref.remove(v);
            } else {
                ok &= mine.search(v) == ref.contains(v);
            }
        }
        ok &= mine.toList().equals(new ArrayList<>(ref)) && mine.size == ref.size();
        System.out.println(ok ? "OK" : "FAIL");
    }
}
