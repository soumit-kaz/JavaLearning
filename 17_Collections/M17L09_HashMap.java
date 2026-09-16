import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M17L09_HashMap {

    public static void main(String[] args) {
        // HashMap maps keys to values, with no order
        Map<String, Integer> ages = new HashMap<>();

        // put returns the old value, or null if the key was new
        System.out.println("put ann: " + ages.put("ann", 30));
        System.out.println("put ann again: " + ages.put("ann", 31));
        ages.put("bob", 25);
        ages.put("cy", 40);

        System.out.println("get ann: " + ages.get("ann"));
        System.out.println("size: " + ages.size());

        // get returns null for a missing key (it never adds the key)
        System.out.println("get zed: " + ages.get("zed"));
        // getOrDefault avoids the null
        System.out.println("getOrDefault zed: " + ages.getOrDefault("zed", 0));
        System.out.println("containsKey bob: " + ages.containsKey("bob"));

        // remove returns the removed value
        System.out.println("remove cy: " + ages.remove("cy"));

        // entrySet gives key and value together
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // keySet gives only the keys, values gives only the values
        System.out.println("keys: " + ages.keySet().size());
        int total = 0;
        for (int age : ages.values()) {
            total += age;
        }
        System.out.println("total age: " + total);

        // forEach takes a lambda with (key, value)
        ages.forEach((name, age) -> System.out.println(name + ": " + age));

        // counting with merge: start at 1, otherwise add 1
        String[] words = {"red", "blue", "red", "green", "blue", "red"};
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }
        System.out.println("red count: " + counts.get("red"));

        // find the most common word
        String best = null;
        int bestCount = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > bestCount) {
                best = entry.getKey();
                bestCount = entry.getValue();
            }
        }
        System.out.println("most common: " + best);

        // a map of lists: create the list the first time, then add to it
        Map<Character, List<String>> byLetter = new HashMap<>();
        for (String name : new String[] {"alice", "bob", "anna", "bill"}) {
            byLetter.computeIfAbsent(name.charAt(0), key -> new ArrayList<>()).add(name);
        }
        System.out.println("a group: " + byLetter.get('a'));
        // a missing group: use an empty list instead of null
        System.out.println("z group size: " + byLetter.getOrDefault('z', List.of()).size());

        // putIfAbsent only writes when the key is missing
        ages.putIfAbsent("ann", 99);
        System.out.println("putIfAbsent ann: " + ages.get("ann"));
    }
}
