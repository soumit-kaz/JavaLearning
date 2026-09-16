import java.util.Arrays;
import java.util.Random;

public class M1807P06_TrappingRainWater {

    static long trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        long water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                // a taller wall exists on the right, so only leftMax limits the water here
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                // mirror case: only rightMax limits the water here
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    // simple check: water at i rises to the lower of the tallest bars on each side
    static long bruteForce(int[] height) {
        long water = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            water += Math.min(leftMax, rightMax) - height[i];
        }
        return water;
    }

    static boolean allPassed = true;

    static void check(String label, boolean pass) {
        allPassed &= pass;
        System.out.println(label + " " + (pass ? "PASS" : "FAIL"));
    }

    static void test(int[] height, long expected) {
        long got = trap(height);
        check(Arrays.toString(height) + " -> " + got, got == expected);
    }

    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6);
        test(new int[]{4, 2, 0, 3, 2, 5}, 9);
        test(new int[]{}, 0);
        test(new int[]{1, 2, 3, 4, 5}, 0);
        test(new int[]{5, 0, 5}, 5);

        // the total is bigger than an int can hold
        int n = 100_000;
        int[] wide = new int[n];
        wide[0] = n;
        wide[n - 1] = n;
        long got = trap(wide);
        check("wide basin -> " + got, got == (long) n * (n - 2));

        // random terrains compared with brute force
        Random random = new Random(6);
        boolean match = true;
        for (int t = 0; t < 500; t++) {
            int[] height = new int[random.nextInt(40)];
            for (int i = 0; i < height.length; i++) {
                height[i] = random.nextInt(12);
            }
            match &= trap(height) == bruteForce(height);
        }
        check("random 500", match);

        if (!allPassed) {
            System.exit(1);
        }
    }
}
