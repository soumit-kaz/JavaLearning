public class M02L06_Casting {

    public static void main(String[] args) {
        // widening: a smaller type fits into a bigger one automatically
        int i = 100;
        long l = i;
        double d = l;
        System.out.println(l + " " + d);

        // int to float can lose digits without a warning
        float asFloat = 123_456_789;
        System.out.println(asFloat);

        // narrowing needs a cast; decimals are cut, not rounded
        double price = 3.99;
        System.out.println((int) price);
        System.out.println((int) -price);

        // storing in a double is too late: int / int already dropped the fraction
        int total = 7;
        int count = 2;
        double tooLate = total / count;
        System.out.println(tooLate);

        // cast first, then divide
        System.out.println((double) total / count);

        // a smaller whole type keeps only the low bits
        System.out.println((byte) 300);
        System.out.println((byte) 128);

        // a too-big double is clamped to the int limit; NaN becomes 0
        System.out.println((int) 1e20);
        System.out.println((int) Double.NaN);

        // going past the limit wraps around silently (overflow)
        int max = Integer.MAX_VALUE;
        System.out.println(max + 1);

        // a byte wraps too
        byte b = 127;
        b = (byte) (b + 1);
        System.out.println(b);

        // int * int overflows before it is stored in the long
        int a = 1_000_000;
        long wrong = a * a;
        long right = (long) a * a;
        System.out.println(wrong);
        System.out.println(right);

        // same trap with constants: write 24L
        long microsWrong = 24 * 60 * 60 * 1000 * 1000;
        long microsRight = 24L * 60 * 60 * 1000 * 1000;
        System.out.println(microsWrong);
        System.out.println(microsRight);
    }
}
