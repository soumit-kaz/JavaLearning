import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class M1807L11_SlidingWindow {

    // fixed window: largest sum of k consecutive values
    static long maxSumOfK(int[] a, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += a[i];
        }
        long best = sum;
        for (int i = k; i < a.length; i++) {
            // slide: add the new value, drop the one that left
            sum += a[i] - a[i - k];
            best = Math.max(best, sum);
        }
        return best;
    }

    // variable window: shortest run with sum >= target (positive values only); 0 if none
    static int minLengthWithSum(int[] a, int target) {
        int best = Integer.MAX_VALUE;
        long sum = 0;
        int left = 0;
        for (int right = 0; right < a.length; right++) {
            sum += a[right];
            // shrink while the window is still valid
            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= a[left];
                left++;
            }
        }
        return best == Integer.MAX_VALUE ? 0 : best;
    }

    // variable window: longest substring with no repeated character
    static int longestUnique(String s) {
        // last index where each character was seen
        Map<Character, Integer> lastSeen = new HashMap<>();
        int best = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            Integer previous = lastSeen.get(c);
            // a repeat inside the window: jump left past it
            if (previous != null && previous >= left) {
                left = previous + 1;
            }
            lastSeen.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // brute force versions: check every window
    static long maxSumOfKBrute(int[] a, int k) {
        long best = Long.MIN_VALUE;
        for (int i = 0; i + k <= a.length; i++) {
            long sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += a[j];
            }
            best = Math.max(best, sum);
        }
        return best;
    }

    static int minLengthBrute(int[] a, int target) {
        int best = 0;
        for (int i = 0; i < a.length; i++) {
            long sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum >= target && (best == 0 || j - i + 1 < best)) {
                    best = j - i + 1;
                }
            }
        }
        return best;
    }

    static int longestUniqueBrute(String s) {
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                boolean unique = true;
                for (int p = i; p < j; p++) {
                    if (s.charAt(p) == s.charAt(j)) {
                        unique = false;
                    }
                }
                if (!unique) {
                    break;
                }
                best = Math.max(best, j - i + 1);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("max sum of 3 -> " + maxSumOfK(new int[]{2, 1, 5, 1, 3, 2}, 3));
        System.out.println("min length sum>=7 -> " + minLengthWithSum(new int[]{2, 3, 1, 2, 4, 3}, 7));
        System.out.println("longest unique in abcabcbb -> " + longestUnique("abcabcbb"));

        boolean ok = minLengthWithSum(new int[]{1, 1}, 5) == 0 && longestUnique("") == 0;

        // random inputs compared with brute force
        Random random = new Random(11);
        for (int t = 0; t < 500; t++) {
            int[] a = new int[1 + random.nextInt(20)];
            for (int i = 0; i < a.length; i++) {
                a[i] = 1 + random.nextInt(9);
            }
            int k = 1 + random.nextInt(a.length);
            int target = 1 + random.nextInt(60);
            ok &= maxSumOfK(a, k) == maxSumOfKBrute(a, k);
            ok &= minLengthWithSum(a, target) == minLengthBrute(a, target);

            StringBuilder sb = new StringBuilder();
            for (int i = random.nextInt(15); i > 0; i--) {
                sb.append((char) ('a' + random.nextInt(5)));
            }
            ok &= longestUnique(sb.toString()) == longestUniqueBrute(sb.toString());
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
