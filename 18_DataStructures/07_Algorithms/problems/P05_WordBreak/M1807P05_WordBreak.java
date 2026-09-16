import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class M1807P05_WordBreak {

    static boolean wordBreak(String s, List<String> dictionary) {
        Set<String> words = new HashSet<>(dictionary);
        int longestWord = 0;
        for (String word : words) {
            longestWord = Math.max(longestWord, word.length());
        }
        int n = s.length();
        // canSplit[i] = the first i characters can be split into words
        boolean[] canSplit = new boolean[n + 1];
        canSplit[0] = true;
        for (int end = 1; end <= n; end++) {
            // a piece longer than the longest word can never match
            for (int start = end - 1; start >= Math.max(0, end - longestWord); start--) {
                if (canSplit[start] && words.contains(s.substring(start, end))) {
                    canSplit[end] = true;
                    break;
                }
            }
        }
        return canSplit[n];
    }

    // plain recursion (exponential in the worst case), for small random tests
    static boolean bruteForce(String s, Set<String> words) {
        if (s.isEmpty()) {
            return true;
        }
        for (int i = 1; i <= s.length(); i++) {
            if (words.contains(s.substring(0, i)) && bruteForce(s.substring(i), words)) {
                return true;
            }
        }
        return false;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(String s, List<String> dictionary, boolean expected) {
        boolean got = wordBreak(s, dictionary);
        String shown = s.length() > 20 ? s.substring(0, 20) + "..." : s;
        check("\"" + shown + "\" " + dictionary + " -> " + got, got == expected);
    }

    public static void main(String[] args) {
        test("leetcode", List.of("leet", "code"), true);
        test("applepenapple", List.of("apple", "pen"), true);
        test("catsandog", List.of("cats", "dog", "sand", "and", "cat"), false);
        test("", List.of("a"), true);
        // "car" is a word, but it leaves "s", a dead end
        test("cars", List.of("car", "ca", "rs"), true);
        // plain recursion takes forever here; the table answers at once
        test("a".repeat(40) + "b", List.of("a", "aa", "aaa", "aaaa"), false);

        // random cases compared with brute force
        List<String> pool = List.of("a", "b", "ab", "ba", "aba", "c");
        Random random = new Random(5);
        boolean match = true;
        for (int t = 0; t < 400; t++) {
            StringBuilder sb = new StringBuilder();
            for (int i = random.nextInt(12); i > 0; i--) {
                sb.append("abc".charAt(random.nextInt(3)));
            }
            Set<String> dictionary = new HashSet<>();
            for (String word : pool) {
                if (random.nextBoolean()) {
                    dictionary.add(word);
                }
            }
            String s = sb.toString();
            match &= wordBreak(s, List.copyOf(dictionary)) == bruteForce(s, dictionary);
        }
        check("random 400", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
