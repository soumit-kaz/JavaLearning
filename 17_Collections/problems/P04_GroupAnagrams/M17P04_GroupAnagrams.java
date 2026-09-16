import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class M17P04_GroupAnagrams {

    static List<List<String>> groupAnagrams(List<String> words) {
        // a TreeMap keeps the groups in sorted-key order
        Map<String, List<String>> groups = new TreeMap<>();
        for (String word : words) {
            // anagrams have the same sorted letters: "eat" and "tea" both become "aet"
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String key = new String(letters);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }

    // print one PASS line, or stop with a FAIL line
    static void test(List<String> words, List<List<String>> expected) {
        List<List<String>> got = groupAnagrams(words);
        String label = words + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test(List.of("eat", "tea", "tan", "ate", "nat", "bat"),
                List.of(List.of("bat"), List.of("eat", "tea", "ate"), List.of("tan", "nat")));
        test(List.of("abc", "cab", "xyz"), List.of(List.of("abc", "cab"), List.of("xyz")));
        test(List.of("a"), List.of(List.of("a")));
        test(List.of("listen", "silent", "google", "enlist"),
                List.of(List.of("google"), List.of("listen", "silent", "enlist")));
        test(List.of(), List.of());
    }
}
