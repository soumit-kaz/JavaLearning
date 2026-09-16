import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class M1802L03_HashMapOpenAddressing {

    static class LinearProbingHashMap<K, V> {
        // marker left by remove so searches keep walking past it
        private static final Object TOMBSTONE = new Object();

        private Object[] keys = new Object[16];
        private Object[] values = new Object[16];
        private int size;
        private int tombstones;

        private static int home(Object key, int capacity) {
            int h = key.hashCode();
            return (h ^ (h >>> 16)) & (capacity - 1);
        }

        // slot holding this key, or -1; an empty slot ends the search, a tombstone does not
        private int find(Object key) {
            int i = home(key, keys.length);
            while (keys[i] != null) {
                if (keys[i] != TOMBSTONE && keys[i].equals(key)) {
                    return i;
                }
                i = (i + 1) & (keys.length - 1);
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        V put(K key, V value) {
            Objects.requireNonNull(key, "null keys are not supported");
            // rehash early: a nearly full table makes probe runs long
            if ((size + tombstones + 1) * 2 > keys.length) {
                rehash();
            }
            int existing = find(key);
            if (existing >= 0) {
                V old = (V) values[existing];
                values[existing] = value;
                return old;
            }
            // reuse the first tombstone or empty slot from the home slot on
            int i = home(key, keys.length);
            while (keys[i] != null && keys[i] != TOMBSTONE) {
                i = (i + 1) & (keys.length - 1);
            }
            if (keys[i] == TOMBSTONE) {
                tombstones--;
            }
            keys[i] = key;
            values[i] = value;
            size++;
            return null;
        }

        @SuppressWarnings("unchecked")
        V get(Object key) {
            int i = find(key);
            return i < 0 ? null : (V) values[i];
        }

        @SuppressWarnings("unchecked")
        V remove(Object key) {
            int i = find(key);
            if (i < 0) {
                return null;
            }
            V old = (V) values[i];
            // setting null here would cut the probe chain of later keys
            keys[i] = TOMBSTONE;
            values[i] = null;
            size--;
            tombstones++;
            return old;
        }

        // rebuild (bigger only if needed); this also drops every tombstone
        private void rehash() {
            Object[] oldKeys = keys;
            Object[] oldValues = values;
            int capacity = size * 4 > oldKeys.length ? oldKeys.length * 2 : oldKeys.length;
            keys = new Object[capacity];
            values = new Object[capacity];
            tombstones = 0;
            for (int j = 0; j < oldKeys.length; j++) {
                if (oldKeys[j] != null && oldKeys[j] != TOMBSTONE) {
                    int i = home(oldKeys[j], capacity);
                    while (keys[i] != null) {
                        i = (i + 1) & (capacity - 1);
                    }
                    keys[i] = oldKeys[j];
                    values[i] = oldValues[j];
                }
            }
        }

        int size() {
            return size;
        }

        // first n slots: . = empty, X = tombstone, otherwise the key
        String slots(int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                Object k = keys[i];
                sb.append(k == null ? "." : k == TOMBSTONE ? "X" : k.toString()).append(' ');
            }
            return sb.toString().trim();
        }
    }

    public static void main(String[] args) {
        // 1, 17 and 33 all have home slot 1 in a 16-slot table
        LinearProbingHashMap<Integer, String> map = new LinearProbingHashMap<>();
        map.put(1, "a");
        map.put(17, "b");
        map.put(33, "c");
        System.out.println("slots: " + map.slots(6));
        System.out.println("remove 17: " + map.remove(17));
        System.out.println("slots: " + map.slots(6));
        System.out.println("get 33: " + map.get(33));
        // 49 also starts at slot 1 and reuses the tombstone
        map.put(49, "d");
        System.out.println("slots: " + map.slots(6));

        // self-check with many removes (lots of tombstones) against java.util.HashMap
        boolean ok = "c".equals(map.get(33)) && map.get(17) == null && map.size() == 3;
        Random rnd = new Random(7);
        LinearProbingHashMap<Integer, Integer> mine = new LinearProbingHashMap<>();
        Map<Integer, Integer> jdk = new HashMap<>();
        for (int step = 0; step < 100_000; step++) {
            int k = rnd.nextInt(1000);
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
