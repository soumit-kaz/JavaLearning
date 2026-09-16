import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class M1806P04_LFUCache {

    static class LFUCache {
        private final int capacity;
        private final Map<Integer, Integer> values = new HashMap<>();
        private final Map<Integer, Integer> counts = new HashMap<>();
        // use count -> keys with that count, oldest first
        private final Map<Integer, LinkedHashSet<Integer>> buckets = new HashMap<>();
        private int minCount;

        LFUCache(int capacity) {
            this.capacity = capacity;
        }

        private void addToBucket(int count, int key) {
            if (!buckets.containsKey(count)) {
                buckets.put(count, new LinkedHashSet<>());
            }
            buckets.get(count).add(key);
        }

        private void touch(int key) {
            int count = counts.get(key);
            LinkedHashSet<Integer> bucket = buckets.get(count);
            bucket.remove(key);
            if (bucket.isEmpty()) {
                buckets.remove(count);
                // the key moves to count + 1, so the minimum rises by at most one
                if (minCount == count) {
                    minCount = count + 1;
                }
            }
            counts.put(key, count + 1);
            addToBucket(count + 1, key);
        }

        int get(int key) {
            if (!values.containsKey(key)) {
                return -1;
            }
            touch(key);
            return values.get(key);
        }

        void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            if (values.containsKey(key)) {
                // updating counts as a use
                values.put(key, value);
                touch(key);
                return;
            }
            if (values.size() == capacity) {
                // the first key in the min bucket is the least recently used among the least used
                LinkedHashSet<Integer> bucket = buckets.get(minCount);
                int victim = bucket.iterator().next();
                bucket.remove(victim);
                if (bucket.isEmpty()) {
                    buckets.remove(minCount);
                }
                values.remove(victim);
                counts.remove(victim);
            }
            values.put(key, value);
            counts.put(key, 1);
            addToBucket(1, key);
            minCount = 1;
        }
    }

    static class SlowLFU {
        private final int capacity;
        private final Map<Integer, Integer> values = new HashMap<>();
        private final Map<Integer, Integer> counts = new HashMap<>();
        private final Map<Integer, Integer> lastUsed = new HashMap<>();
        private int clock;

        SlowLFU(int capacity) {
            this.capacity = capacity;
        }

        int get(int key) {
            if (!values.containsKey(key)) {
                return -1;
            }
            counts.put(key, counts.get(key) + 1);
            lastUsed.put(key, ++clock);
            return values.get(key);
        }

        void put(int key, int value) {
            if (capacity <= 0) {
                return;
            }
            if (values.containsKey(key)) {
                get(key);
                values.put(key, value);
                return;
            }
            if (values.size() == capacity) {
                // scan all keys for the smallest (count, lastUsed)
                int victim = -1;
                for (int k : values.keySet()) {
                    if (victim == -1 || counts.get(k) < counts.get(victim)
                            || (counts.get(k).equals(counts.get(victim)) && lastUsed.get(k) < lastUsed.get(victim))) {
                        victim = k;
                    }
                }
                values.remove(victim);
                counts.remove(victim);
                lastUsed.remove(victim);
            }
            values.put(key, value);
            counts.put(key, 1);
            lastUsed.put(key, ++clock);
        }
    }

    static List<Integer> run(int capacity, String ops) {
        // ops look like "p1=1 g1": p = put key=value, g = get key
        LFUCache cache = new LFUCache(capacity);
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
        String ops1 = "p1=1 p2=2 g1 p3=3 g2 g3 p4=4 g1 g3 g4";
        check("cap=2 " + ops1, run(2, ops1), List.of(1, -1, 3, -1, 3, 4));
        check("cap=0 p0=0 g0", run(0, "p0=0 g0"), List.of(-1));
        String ops3 = "p3=1 p2=1 p2=2 p4=4 g2 g3 g4";
        check("cap=2 " + ops3, run(2, ops3), List.of(2, -1, 4));
        String ops4 = "p1=1 g1 g1 p2=2 g1 g2";
        check("cap=1 " + ops4, run(1, ops4), List.of(1, 1, -1, 2));

        Random random = new Random(9);
        boolean allMatch = true;
        for (int t = 0; t < 60; t++) {
            int capacity = random.nextInt(8);
            LFUCache mine = new LFUCache(capacity);
            SlowLFU ref = new SlowLFU(capacity);
            for (int op = 0; op < 2000; op++) {
                int key = random.nextInt(12);
                if (random.nextBoolean()) {
                    mine.put(key, op);
                    ref.put(key, op);
                } else if (mine.get(key) != ref.get(key)) {
                    allMatch = false;
                }
            }
        }
        check("random 60 runs vs slow LFU", allMatch ? "match" : "differ", "match");
    }
}
