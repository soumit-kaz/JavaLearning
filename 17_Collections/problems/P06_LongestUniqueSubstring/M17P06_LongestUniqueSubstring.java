import java.util.HashMap;
import java.util.Map;

public class M17P06_LongestUniqueSubstring {

    static int longestUnique(String s) {
        // where each character was seen last
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            Integer previous = lastSeen.put(s.charAt(right), right);
            // a repeat inside the window: move the left edge past it
            if (previous != null && previous >= left) {
                left = previous + 1;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // print one PASS line, or stop with a FAIL line
    static void test(String s, int expected) {
        int got = longestUnique(s);
        String label = "\"" + s + "\" -> " + got;
        if (got != expected) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test("abcabcbb", 3);
        test("bbbbb", 1);
        test("pwwkew", 3);
        test("", 0);
        test("dvdf", 3);
        test("abba", 2);
    }
}
