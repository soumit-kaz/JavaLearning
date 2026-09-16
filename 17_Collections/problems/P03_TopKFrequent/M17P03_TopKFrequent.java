import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M17P03_TopKFrequent {

    static List<String> topKFrequent(String[] words, int k) {
        // count each word
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }

        // sort the distinct words: higher count first, then alphabetically
        List<String> unique = new ArrayList<>(counts.keySet());
        unique.sort((a, b) -> {
            int byCount = Integer.compare(counts.get(b), counts.get(a));
            return byCount != 0 ? byCount : a.compareTo(b);
        });

        // keep the first k (or fewer, if there are not enough words)
        return unique.subList(0, Math.min(k, unique.size()));
    }

    // print one PASS line, or stop with a FAIL line
    static void test(String[] words, int k, List<String> expected) {
        List<String> got = topKFrequent(words, k);
        String label = Arrays.toString(words) + " k " + k + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test(new String[]{"i", "love", "code", "i", "love", "java"}, 2, List.of("i", "love"));
        test(new String[]{"the", "day", "is", "sunny", "the", "the", "sunny", "is", "is"}, 3,
                List.of("is", "the", "sunny"));
        test(new String[]{"b", "a", "c"}, 2, List.of("a", "b"));
        test(new String[]{"x"}, 1, List.of("x"));
        test(new String[]{"a", "b", "a"}, 5, List.of("a", "b"));
    }
}
