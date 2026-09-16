public class M03P04_SwapWithoutTemp {

    public static void main(String[] args) {
        int a = 5;
        int b = 9;

        // swap with + and -
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + ", b = " + b);

        // swap back with XOR: each step cancels one value
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a = " + a + ", b = " + b);

        // the + and - trick still works when a + b overflows, because the overflow cancels out
        int big = Integer.MAX_VALUE;
        int other = 1;
        big = big + other;
        other = big - other;
        big = big - other;
        System.out.println("big = " + big + ", other = " + other);
    }
}
