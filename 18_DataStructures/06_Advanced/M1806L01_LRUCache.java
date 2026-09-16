import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class M1806L01_LRUCache {

    static class LRUCache<K, V> {

        static class Node<K, V> {
            K key;
            V value;
            Node<K, V> prev;
            Node<K, V> next;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<K, Node<K, V>> map = new HashMap<>();
        // sentinels: head.next is the newest, tail.prev is the oldest
        private final Node<K, V> head = new Node<>(null, null);
        private final Node<K, V> tail = new Node<>(null, null);

        LRUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("capacity must be positive");
            }
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        private void unlink(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToFront(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        V get(K key) {
            Node<K, V> node = map.get(key);
            if (node == null) {
                return null;
            }
            // a read makes the key the most recently used
            unlink(node);
            addToFront(node);
            return node.value;
        }

        void put(K key, V value) {
            Node<K, V> node = map.get(key);
            // existing key: update the value and move it to the front
            if (node != null) {
                node.value = value;
                unlink(node);
                addToFront(node);
                return;
            }
            // full: evict the oldest; its stored key lets us clean the map too
            if (map.size() == capacity) {
                Node<K, V> oldest = tail.prev;
                unlink(oldest);
                map.remove(oldest.key);
            }
            Node<K, V> fresh = new Node<>(key, value);
            addToFront(fresh);
            map.put(key, fresh);
        }

        List<K> keys() {
            List<K> result = new ArrayList<>();
            for (Node<K, V> n = head.next; n != tail; n = n.next) {
                result.add(n.key);
            }
            return result;
        }
    }

    // JDK reference: LinkedHashMap in access order with an eviction rule
    static class ReferenceLRU<K, V> extends LinkedHashMap<K, V> {
        @java.io.Serial
        private static final long serialVersionUID = 1L;
        private final int capacity;

        ReferenceLRU(int capacity) {
            super(16, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        System.out.println("get(1): " + cache.get(1));
        cache.put(4, "four");
        System.out.println("after put(4): " + cache.keys());
        System.out.println("get(2): " + cache.get(2));

        boolean ok = cache.keys().equals(List.of(4, 1, 3)) && cache.get(2) == null;
        try {
            new LRUCache<Integer, Integer>(0);
            ok = false;
        } catch (IllegalArgumentException e) {
            // expected
        }

        // random operations compared with LinkedHashMap
        Random random = new Random(7);
        for (int trial = 0; trial < 20; trial++) {
            int capacity = 1 + random.nextInt(8);
            LRUCache<Integer, Integer> mine = new LRUCache<>(capacity);
            ReferenceLRU<Integer, Integer> ref = new ReferenceLRU<>(capacity);
            for (int op = 0; op < 2000; op++) {
                int key = random.nextInt(15);
                if (random.nextBoolean()) {
                    mine.put(key, op);
                    ref.put(key, op);
                } else if (!Objects.equals(mine.get(key), ref.get(key))) {
                    ok = false;
                }
            }
            // LinkedHashMap lists oldest first, ours lists newest first
            List<Integer> expected = new ArrayList<>(ref.keySet());
            Collections.reverse(expected);
            ok &= expected.equals(mine.keys());
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
