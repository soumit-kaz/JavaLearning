import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class M1802L02_HashMapChaining {

    // one key/value pair inside a bucket's linked list
    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    static class ChainingHashMap<K, V> {
        private Node<K, V>[] buckets = newTable(4);
        private int size;

        // Java cannot create a generic array directly, so create a raw one and cast
        @SuppressWarnings("unchecked")
        private static <K, V> Node<K, V>[] newTable(int capacity) {
            return (Node<K, V>[]) new Node<?, ?>[capacity];
        }

        // mix high bits down, then keep the low bits (capacity is a power of two)
        private static int indexFor(Object key, int capacity) {
            int h = Objects.hashCode(key);
            return (h ^ (h >>> 16)) & (capacity - 1);
        }

        private Node<K, V> findNode(Object key) {
            for (Node<K, V> n = buckets[indexFor(key, buckets.length)]; n != null; n = n.next) {
                if (Objects.equals(n.key, key)) {
                    return n;
                }
            }
            return null;
        }

        V put(K key, V value) {
            // existing key: just replace the value
            Node<K, V> found = findNode(key);
            if (found != null) {
                V old = found.value;
                found.value = value;
                return old;
            }
            // new key: add at the front of its chain
            int i = indexFor(key, buckets.length);
            buckets[i] = new Node<>(key, value, buckets[i]);
            size++;
            // keep chains short: grow when load factor passes 0.75
            if (size > buckets.length * 3 / 4) {
                resize();
            }
            return null;
        }

        V get(Object key) {
            Node<K, V> n = findNode(key);
            return n == null ? null : n.value;
        }

        V remove(Object key) {
            int i = indexFor(key, buckets.length);
            Node<K, V> prev = null;
            for (Node<K, V> n = buckets[i]; n != null; prev = n, n = n.next) {
                if (Objects.equals(n.key, key)) {
                    // unlink: head of the chain or a later node
                    if (prev == null) {
                        buckets[i] = n.next;
                    } else {
                        prev.next = n.next;
                    }
                    size--;
                    return n.value;
                }
            }
            return null;
        }

        // double the table and move every node, since each index depends on capacity
        private void resize() {
            Node<K, V>[] old = buckets;
            buckets = newTable(old.length * 2);
            for (Node<K, V> head : old) {
                Node<K, V> n = head;
                while (n != null) {
                    Node<K, V> next = n.next;
                    int i = indexFor(n.key, buckets.length);
                    n.next = buckets[i];
                    buckets[i] = n;
                    n = next;
                }
            }
        }

        int size() {
            return size;
        }

        int capacity() {
            return buckets.length;
        }
    }

    public static void main(String[] args) {
        ChainingHashMap<String, Integer> map = new ChainingHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put(null, 0);
        System.out.println("put one=11, old: " + map.put("one", 11));
        System.out.println("get one: " + map.get("one"));
        System.out.println("get null: " + map.get(null));
        System.out.println("remove two: " + map.remove("two"));
        System.out.println("size: " + map.size());
        System.out.println("capacity: " + map.capacity());

        // self-check against java.util.HashMap
        boolean ok = map.get("missing") == null && map.get("two") == null;
        Random rnd = new Random(42);
        ChainingHashMap<Integer, Integer> mine = new ChainingHashMap<>();
        Map<Integer, Integer> jdk = new HashMap<>();
        for (int step = 0; step < 100_000; step++) {
            int k = rnd.nextInt(2000);
            switch (rnd.nextInt(3)) {
                case 0 -> ok &= Objects.equals(mine.put(k, step), jdk.put(k, step));
                case 1 -> ok &= Objects.equals(mine.remove(k), jdk.remove(k));
                default -> ok &= Objects.equals(mine.get(k), jdk.get(k));
            }
            ok &= mine.size() == jdk.size();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
