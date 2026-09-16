import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class M1802P04_MinimumWindow {

    static String minWindow(String s, String t) {
        if (t.isEmpty()) {
            return "";
        }
        // how many of each character we still need
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.merge(t.charAt(i), 1, Integer::sum);
        }
        Map<Character, Integer> have = new HashMap<>();
        // number of distinct characters whose count is fully covered
        int covered = 0;
        int left = 0;
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (!need.containsKey(c)) {
                continue;
            }
            have.merge(c, 1, Integer::sum);
            if (have.get(c).equals(need.get(c))) {
                covered++;
            }
            // while the window is valid, record it and try to shrink it from the left
            while (covered == need.size()) {
                if (right - left + 1 < bestLength) {
                    bestLength = right - left + 1;
                    bestStart = left;
                }
                char out = s.charAt(left);
                left++;
                if (need.containsKey(out)) {
                    have.merge(out, -1, Integer::sum);
                    // dropping below the needed count makes the window invalid again
                    if (have.get(out) < need.get(out)) {
                        covered--;
                    }
                }
            }
        }
        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLength);
    }

    // does window w contain all characters of t (with counts)?
    static boolean covers(String w, String t) {
        int[] count = new int[128];
        for (int i = 0; i < w.length(); i++) {
            count[w.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--count[t.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }

    // try every window from the shortest to the longest, left to right
    static String bruteForce(String s, String t) {
        if (t.isEmpty()) {
            return "";
        }
        for (int length = t.length(); length <= s.length(); length++) {
            for (int i = 0; i + length <= s.length(); i++) {
                if (covers(s.substring(i, i + length), t)) {
                    return s.substring(i, i + length);
                }
            }
        }
        return "";
    }

    static String randomString(Random rnd, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + rnd.nextInt(4)));
        }
        return sb.toString();
    }

    static void test(String s, String t, String expected) {
        String got = minWindow(s, t);
        System.out.println("\"" + s + "\" \"" + t + "\" -> \"" + got + "\"  " + (got.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("ADOBECODEBANC", "ABC", "BANC");
        test("a", "a", "a");
        test("a", "aa", "");
        test("acbbaca", "aba", "baca");
        test("abc", "", "");

        Random rnd = new Random(10);
        boolean match = true;
        for (int k = 0; k < 300; k++) {
            String s = randomString(rnd, rnd.nextInt(25));
            String t = randomString(rnd, 1 + rnd.nextInt(5));
            match &= minWindow(s, t).equals(bruteForce(s, t));
        }
        System.out.println("random 300 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
