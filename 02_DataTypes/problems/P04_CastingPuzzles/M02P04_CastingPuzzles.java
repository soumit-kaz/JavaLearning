public class M02P04_CastingPuzzles {

    public static void main(String[] args) {
        // double to int cuts toward zero
        System.out.println("(int) 3.99 = " + (int) 3.99);
        System.out.println("(int) -3.99 = " + (int) -3.99);

        // byte keeps only the low 8 bits: 200 - 256 = -56
        System.out.println("(byte) 200 = " + (byte) 200);
        System.out.println("(short) 70000 = " + (short) 70000);

        // numbers and chars convert both ways
        System.out.println("(char) 66 = " + (char) 66);
        System.out.println("(int) 'a' = " + (int) 'a');

        // int / int is integer division
        System.out.println("7 / 2 = " + 7 / 2);
        System.out.println("7 / 2.0 = " + 7 / 2.0);
        // the cast happens before the division
        System.out.println("(double) 7 / 2 = " + (double) 7 / 2);
        System.out.println("(double) (7 / 2) = " + (double) (7 / 2));

        // too-large doubles are clamped to the biggest long
        System.out.println("(long) 1e19 = " + (long) 1e19);
        System.out.println("(int) Double.NaN = " + (int) Double.NaN);
    }
}
