import java.util.Random;

public class M1807L14_DPOnStrings {

    // longest common subsequence, rebuilt as a string
    static String lcs(String a, String b) {
        int n = a.length();
        int m = b.length();
        // len[i][j] = LCS length of the first i chars of a and first j chars of b
        int[][] len = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    // matching last characters extend the answer
                    len[i][j] = len[i - 1][j - 1] + 1;
                } else {
                    // otherwise drop the last char of one string
                    len[i][j] = Math.max(len[i - 1][j], len[i][j - 1]);
                }
            }
        }
        // walk back from the corner to collect the characters
        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                sb.append(a.charAt(i - 1));
                i--;
                j--;
            } else if (len[i - 1][j] >= len[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    // fewest inserts, deletes and replaces to turn a into b
    static int editDistance(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dist = new int[n + 1][m + 1];
        // turning a prefix into the empty string (or back) takes one step per char
        for (int i = 0; i <= n; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    // 1 + best of replace, delete, insert
                    dist[i][j] = 1 + Math.min(dist[i - 1][j - 1], Math.min(dist[i - 1][j], dist[i][j - 1]));
                }
            }
        }
        return dist[n][m];
    }

    // brute force: plain recursion on the last characters
    static int lcsBrute(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            return 0;
        }
        String a1 = a.substring(0, a.length() - 1);
        String b1 = b.substring(0, b.length() - 1);
        if (a.charAt(a.length() - 1) == b.charAt(b.length() - 1)) {
            return 1 + lcsBrute(a1, b1);
        }
        return Math.max(lcsBrute(a1, b), lcsBrute(a, b1));
    }

    static int editBrute(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            return a.length() + b.length();
        }
        String a1 = a.substring(0, a.length() - 1);
        String b1 = b.substring(0, b.length() - 1);
        if (a.charAt(a.length() - 1) == b.charAt(b.length() - 1)) {
            return editBrute(a1, b1);
        }
        return 1 + Math.min(editBrute(a1, b1), Math.min(editBrute(a1, b), editBrute(a, b1)));
    }

    // true if s can be made by deleting characters from t
    static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; j < t.length() && i < s.length(); j++) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }
        return i == s.length();
    }

    static String randomWord(Random random) {
        StringBuilder sb = new StringBuilder();
        for (int i = random.nextInt(8); i > 0; i--) {
            sb.append((char) ('a' + random.nextInt(3)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("lcs(ABCBDAB, BDCABA) -> " + lcs("ABCBDAB", "BDCABA"));
        System.out.println("edit(kitten, sitting) -> " + editDistance("kitten", "sitting"));

        boolean ok = lcs("ABCBDAB", "BDCABA").length() == 4 && editDistance("horse", "ros") == 3;
        ok &= lcs("", "abc").isEmpty() && editDistance("", "abc") == 3;

        // random short words compared with plain recursion
        Random random = new Random(14);
        for (int t = 0; t < 300; t++) {
            String a = randomWord(random);
            String b = randomWord(random);
            String common = lcs(a, b);
            ok &= common.length() == lcsBrute(a, b) && isSubsequence(common, a) && isSubsequence(common, b);
            ok &= editDistance(a, b) == editBrute(a, b);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
