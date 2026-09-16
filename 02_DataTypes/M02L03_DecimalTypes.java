public class M02L03_DecimalTypes {

    public static void main(String[] args) {
        // a decimal literal is a double; float needs the f suffix
        double d = 3.14;
        float f = 3.14f;
        System.out.println(d + " " + f);

        // float keeps about 7 digits, double about 15
        float piFloat = 3.14159265358979f;
        double piDouble = 3.14159265358979;
        System.out.println(piFloat + " " + piDouble);

        // e means "times 10 to the power"
        System.out.println(1e3);
        System.out.println(2.5e-3);

        // big and tiny doubles print in scientific form
        System.out.println(12345678.0);
        System.out.println(0.0001);

        // for decimals, MIN_VALUE is the smallest POSITIVE value
        System.out.println("double min = " + Double.MIN_VALUE);
        System.out.println("double max = " + Double.MAX_VALUE);

        // many decimals cannot be stored exactly
        System.out.println(0.1 + 0.2);
        System.out.println(2.00 - 1.10);

        // big doubles cannot store every whole number
        System.out.println(1e16 + 1);

        // for money, count cents in a long instead
        long priceInCents = 110;
        long paidInCents = 200;
        System.out.println("change in cents = " + (paidInCents - priceInCents));

        // dividing a double by zero gives Infinity, not a crash
        double zero = 0.0;
        double infinity = 1 / zero;
        System.out.println(infinity);
        System.out.println(-1 / zero);

        // NaN means "not a number", and it spreads
        double nan = zero / zero;
        System.out.println(nan);
        System.out.println(nan + 1);

        // negative zero exists
        double negativeZero = -0.0;
        System.out.println(negativeZero);
        System.out.println(1 / negativeZero);
    }
}
