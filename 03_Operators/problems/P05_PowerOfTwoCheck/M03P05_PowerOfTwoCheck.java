public class M03P05_PowerOfTwoCheck {

    public static void main(String[] args) {
        int a = 64;
        int b = 96;
        int c = 0;

        // a power of two has one 1 bit; n & (n - 1) removes it and leaves 0
        System.out.println(a + " -> " + (a > 0 && (a & (a - 1)) == 0));
        System.out.println(b + " -> " + (b > 0 && (b & (b - 1)) == 0));

        // 0 is not a power of two, so the n > 0 check is needed
        System.out.println(c + " -> " + (c > 0 && (c & (c - 1)) == 0));

        // the same answer with bitCount
        System.out.println(a + " bitCount = " + Integer.bitCount(a));
        System.out.println(b + " bitCount = " + Integer.bitCount(b));

        // n & -n keeps only the lowest 1 bit
        System.out.println("lowest bit of " + b + " = " + (b & -b));

        // 1 << k is 2 to the power k
        System.out.println("2^10 = " + (1 << 10));
    }
}
