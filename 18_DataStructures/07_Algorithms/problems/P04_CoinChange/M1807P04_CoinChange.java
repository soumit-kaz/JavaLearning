import java.util.Arrays;
import java.util.Random;

public class M1807P04_CoinChange {

    // fewest coins that make amount, or -1 if impossible
    static int coinChange(int[] coins, int amount) {
        // amount + 1 works as "infinity": no answer needs more than amount coins
        int impossible = amount + 1;
        int[] fewest = new int[amount + 1];
        Arrays.fill(fewest, impossible);
        fewest[0] = 0;
        // small amounts first, so fewest[a - coin] is final when we read it
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a && fewest[a - coin] + 1 < fewest[a]) {
                    fewest[a] = fewest[a - coin] + 1;
                }
            }
        }
        return fewest[amount] == impossible ? -1 : fewest[amount];
    }

    // plain recursion over every coin choice (small amounts only)
    static int bruteForce(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int best = -1;
        for (int coin : coins) {
            if (coin <= amount) {
                int rest = bruteForce(coins, amount - coin);
                if (rest >= 0 && (best == -1 || rest + 1 < best)) {
                    best = rest + 1;
                }
            }
        }
        return best;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(int[] coins, int amount, int expected) {
        int got = coinChange(coins, amount);
        check(Arrays.toString(coins) + " " + amount + " -> " + got, got == expected);
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 5}, 11, 3);
        test(new int[]{2}, 3, -1);
        test(new int[]{1}, 0, 0);
        // greedy would take 4 + 1 + 1; the best is 3 + 3
        test(new int[]{1, 3, 4}, 6, 2);
        test(new int[]{186, 419, 83, 408}, 6249, 20);

        // random cases compared with brute force
        Random random = new Random(4);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int[] coins = {2 + random.nextInt(3), 3 + random.nextInt(5), 7 + random.nextInt(5)};
            int amount = random.nextInt(30);
            match &= coinChange(coins, amount) == bruteForce(coins, amount);
        }
        check("random 300", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
