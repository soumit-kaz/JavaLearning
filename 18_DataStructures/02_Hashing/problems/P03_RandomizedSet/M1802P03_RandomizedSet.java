import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public class M1802P03_RandomizedSet {

    static class RandomizedSet {
        // value -> its position in the list
        private final Map<Integer, Integer> indexOf = new HashMap<>();
        // the values packed with no gaps, so we can pick a random position
        private final List<Integer> values = new ArrayList<>();
        private final Random random;

        RandomizedSet(long seed) {
            random = new Random(seed);
        }

        boolean insert(int value) {
            if (indexOf.containsKey(value)) {
                return false;
            }
            indexOf.put(value, values.size());
            values.add(value);
            return true;
        }

        boolean remove(int value) {
            Integer index = indexOf.remove(value);
            if (index == null) {
                return false;
            }
            // removing from the end of a list is O(1), so move the last value into the hole
            int lastValue = values.remove(values.size() - 1);
            if (index < values.size()) {
                values.set(index, lastValue);
                indexOf.put(lastValue, index);
            }
            return true;
        }

        int getRandom() {
            if (values.isEmpty()) {
                throw new NoSuchElementException("set is empty");
            }
            return values.get(random.nextInt(values.size()));
        }
    }

    static void test(String action, Object got, Object expected) {
        System.out.println(action + " -> " + got + "  " + (got.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet(9);
        test("insert(1)", set.insert(1), true);
        test("remove(2)", set.remove(2), false);
        test("insert(2)", set.insert(2), true);
        test("insert(2) again", set.insert(2), false);
        test("remove(1)", set.remove(1), true);
        test("getRandom() with only 2 left", set.getRandom(), 2);
        test("remove(2) (the last value)", set.remove(2), true);
        String result;
        try {
            set.getRandom();
            result = "no exception";
        } catch (NoSuchElementException e) {
            result = "NoSuchElementException";
        }
        test("getRandom() on empty", result, "NoSuchElementException");

        // compare with java.util.HashSet and make sure getRandom never returns a removed value
        RandomizedSet mine = new RandomizedSet(10);
        Set<Integer> jdk = new HashSet<>();
        Random rnd = new Random(11);
        boolean match = true;
        for (int step = 0; step < 50_000; step++) {
            int v = rnd.nextInt(300);
            if (rnd.nextBoolean()) {
                match &= mine.insert(v) == jdk.add(v);
            } else {
                match &= mine.remove(v) == jdk.remove(v);
            }
            if (!jdk.isEmpty()) {
                match &= jdk.contains(mine.getRandom());
            }
        }
        test("random 50000 operations", match ? "match" : "differ", "match");
    }
}
