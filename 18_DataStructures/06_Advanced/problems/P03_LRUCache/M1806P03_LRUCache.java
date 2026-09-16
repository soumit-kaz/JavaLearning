import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class M1806P03_LRUCache {

    static class LRUCache {

        static class Node {
            int key;
            int value;
            Node prev;
            Node next;
        }

        private final int capacity;
        private final Map<Integer, Node> map = new HashMap<>();
        // dummy ends: head.next is the newest, tail.prev is the oldest
        private final Node head = new Node();
        private final Node tail = new Node();

        LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            }
            // a read makes the key the newest
            remove(node);
            addToFront(node);
            return node.value;
        }

        void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                remove(node);
                addToFront(node);
                return;
            }
            if (map.size() == capacity) {
                // evict the oldest; its stored key lets us clean the map too
                Node oldest = tail.prev;
                remove(oldest);
                map.remove(oldest.key);
            }
            node = new Node();
            node.key = key;
            node.value = value;
            addToFront(node);
            map.put(key, node);
        }
    }

    static class ReferenceLRU extends LinkedHashMap<Integer, Integer> {
        @java.io.Serial
        private static final long serialVersionUID = 1L;
        private final int capacity;

        ReferenceLRU(int capacity) {
            super(16, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    static List<Integer> run(int capacity, String ops) {
        // ops look like "p1=1 p2=2 g1": p = put key=value, g = get key
        LRUCache cache = new LRUCache(capacity);
        List<Integer> gets = new ArrayList<>();
        for (String op : ops.split(" ")) {
            if (op.charAt(0) == 'p') {
                String[] parts = op.substring(1).split("=");
                cache.put(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } else {
                gets.add(cache.get(Integer.parseInt(op.substring(1))));
            }
        }
        return gets;
    }

    static void check(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        String ops1 = "p1=1 p2=2 g1 p3=3 g2 p4=4 g1 g3 g4";
        check("cap=2 " + ops1, run(2, ops1), List.of(1, -1, -1, 3, 4));
        String ops2 = "p2=1 g2 p3=2 g2 g3";
        check("cap=1 " + ops2, run(1, ops2), List.of(1, -1, 2));
        String ops3 = "p2=1 p2=2 g2 p1=1 p4=1 g2";
        check("cap=2 " + ops3, run(2, ops3), List.of(2, -1));
        check("cap=3 g7", run(3, "g7"), List.of(-1));

        Random random = new Random(5);
        boolean allMatch = true;
        for (int t = 0; t < 50; t++) {
            int capacity = 1 + random.nextInt(10);
            LRUCache mine = new LRUCache(capacity);
            ReferenceLRU ref = new ReferenceLRU(capacity);
            for (int op = 0; op < 3000; op++) {
                int key = random.nextInt(20);
                if (random.nextBoolean()) {
                    mine.put(key, op);
                    ref.put(key, op);
                } else if (mine.get(key) != ref.getOrDefault(key, -1)) {
                    allMatch = false;
                }
            }
        }
        check("random 50 runs vs LinkedHashMap", allMatch ? "match" : "differ", "match");
    }
}
