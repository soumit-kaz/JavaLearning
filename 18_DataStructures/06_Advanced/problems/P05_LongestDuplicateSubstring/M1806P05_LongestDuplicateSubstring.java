import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class M1806P05_LongestDuplicateSubstring {

    static int[] suffixArray(String s) {
        int n = s.length();
        int[] order = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];
        int[] bySecond = new int[n];
        if (n == 0) {
            return order;
        }
        // counting sort by first character
        int[] count = new int[Math.max(128, n) + 1];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)]++;
        }
        for (int c = 1; c < count.length; c++) {
            count[c] += count[c - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            order[--count[s.charAt(i)]] = i;
        }
        int classes = 1;
        rank[order[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(order[i]) != s.charAt(order[i - 1])) {
                classes++;
            }
            rank[order[i]] = classes - 1;
        }
        // each round doubles the sorted prefix length h
        for (int h = 1; h < n && classes < n; h *= 2) {
            // order by second half: suffixes with no second half come first
            int p = 0;
            for (int i = n - h; i < n; i++) {
                bySecond[p++] = i;
            }
            for (int i = 0; i < n; i++) {
                if (order[i] >= h) {
                    bySecond[p++] = order[i] - h;
                }
            }
            // stable counting sort by first half keeps the second-half order for ties
            Arrays.fill(count, 0);
            for (int i = 0; i < n; i++) {
                count[rank[i]]++;
            }
            for (int c = 1; c < classes; c++) {
                count[c] += count[c - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                order[--count[rank[bySecond[i]]]] = bySecond[i];
            }
            // new ranks: a pair that differs from the previous pair gets a new class
            temp[order[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                int a = order[i - 1];
                int b = order[i];
                int secondA = a + h < n ? rank[a + h] : -1;
                int secondB = b + h < n ? rank[b + h] : -1;
                if (rank[a] != rank[b] || secondA != secondB) {
                    classes++;
                }
                temp[b] = classes - 1;
            }
            int[] swap = rank;
            rank = temp;
            temp = swap;
        }
        return order;
    }

    static int[] lcpArray(String s, int[] order) {
        int n = s.length();
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[order[i]] = i;
        }
        int[] lcp = new int[n];
        int k = 0;
        // Kasai: k drops by at most one between neighbouring start positions
        for (int i = 0; i < n; i++) {
            if (position[i] == 0) {
                k = 0;
                continue;
            }
            int j = order[position[i] - 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            lcp[position[i]] = k;
            if (k > 0) {
                k--;
            }
        }
        return lcp;
    }

    static String longestDupSubstring(String s) {
        int[] order = suffixArray(s);
        int[] lcp = lcpArray(s, order);
        int best = 0;
        int start = 0;
        // the first maximum in sorted order is also the smallest alphabetically
        for (int i = 1; i < s.length(); i++) {
            if (lcp[i] > best) {
                best = lcp[i];
                start = order[i];
            }
        }
        return s.substring(start, start + best);
    }

    static String slow(String s) {
        String best = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                // it repeats if it also appears starting after i
                boolean repeats = s.indexOf(sub, i + 1) >= 0;
                boolean better = sub.length() > best.length()
                        || (sub.length() == best.length() && sub.compareTo(best) < 0);
                if (repeats && better) {
                    best = sub;
                }
            }
        }
        return best;
    }

    static void check(String input, Object output, Object expected) {
        // quote text answers so an empty answer is still visible
        String shown = output instanceof String ? "\"" + output + "\"" : String.valueOf(output);
        System.out.println(input + " -> " + shown + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        check("banana", longestDupSubstring("banana"), "ana");
        check("abcd", longestDupSubstring("abcd"), "");
        check("aaaaa", longestDupSubstring("aaaaa"), "aaaa");
        check("mississippi", longestDupSubstring("mississippi"), "issi");
        check("abcabxyzxy", longestDupSubstring("abcabxyzxy"), "ab");
        check("a", longestDupSubstring("a"), "");
        check("(empty)", longestDupSubstring(""), "");

        Random random = new Random(12);
        boolean allMatch = true;
        for (int t = 0; t < 300; t++) {
            StringBuilder sb = new StringBuilder();
            int n = random.nextInt(40);
            int alphabet = 1 + random.nextInt(3);
            for (int i = 0; i < n; i++) {
                sb.append((char) ('a' + random.nextInt(alphabet)));
            }
            String s = sb.toString();
            if (!longestDupSubstring(s).equals(slow(s))) {
                allMatch = false;
            }
        }
        check("random 300 strings vs brute force", allMatch ? "match" : "differ", "match");

        // a long random text with one planted repeat
        StringBuilder big = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            big.append((char) ('a' + random.nextInt(26)));
        }
        String planted = "thequickbrownfoxjumpsoverthelazydog";
        big.insert(1000, planted);
        big.insert(20000, planted);
        String answer = longestDupSubstring(big.toString());
        check("30070 chars with a planted repeat", answer.contains(planted), true);
    }
}
