public class M03L06_BitwiseOperators {

    public static void main(String[] args) {
        int a = 0b1100;
        int b = 0b1010;

        // & keeps bits set in both, | in either, ^ in only one
        System.out.println("a & b = " + Integer.toBinaryString(a & b));
        System.out.println("a | b = " + Integer.toBinaryString(a | b));
        System.out.println("a ^ b = " + Integer.toBinaryString(a ^ b));

        // ~ flips every bit, so ~a is -a - 1
        System.out.println("~a = " + (~a));

        // negative numbers have the top bit set (two's complement)
        System.out.println("-1 = " + Integer.toBinaryString(-1));

        // n & 1 is 1 for odd numbers, even negative ones
        System.out.println("-7 & 1 = " + (-7 & 1));

        // == comes before &, so the parentheses are required
        int flags = 6;
        System.out.println("bit 4 set: " + ((flags & 4) != 0));

        // x ^ x is 0 and x ^ 0 is x
        System.out.println("5 ^ 5 = " + (5 ^ 5));
        System.out.println("5 ^ 0 = " + (5 ^ 0));

        // << doubles for each step, >> halves
        System.out.println("1 << 3 = " + (1 << 3));
        System.out.println("40 >> 3 = " + (40 >> 3));

        // >> keeps the sign, >>> fills with zeros
        System.out.println("-16 >> 2 = " + (-16 >> 2));
        System.out.println("-16 >>> 2 = " + (-16 >>> 2));

        // >> rounds down, while / cuts toward zero
        System.out.println("-7 >> 1 = " + (-7 >> 1));
        System.out.println("-7 / 2 = " + (-7 / 2));

        // an int uses only the lowest 5 bits of the distance, so 32 means 0
        int distance = 32;
        System.out.println("1 << 32 = " + (1 << distance));
        System.out.println("1L << 32 = " + (1L << distance));

        // shifting into the sign bit makes the number negative
        System.out.println("1 << 31 = " + (1 << 31));

        // + comes before <<
        System.out.println("1 << 2 + 1 = " + (1 << 2 + 1));
    }
}
