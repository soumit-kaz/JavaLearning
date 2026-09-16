public class M12L04_Boxing {

    static String phases(long x) { return "long"; }
    static String phases(Integer x) { return "Integer"; }

    static String objectOrLong(Object x) { return "Object"; }
    static String objectOrLong(long x) { return "long"; }

    static String onlyLong(Long x) { return "Long"; }

    static int square(int x) {
        return x * x;
    }

    public static void main(String[] args) {
        // phase 1: exact type or widening, no boxing
        // phase 2: boxing (int -> Integer) and unboxing (Integer -> int)
        System.out.println("phases(Integer) -> " + phases(Integer.valueOf(1)));

        // widening (phase 1) beats boxing (phase 2)
        System.out.println("phases(5) -> " + phases(5));

        // Integer -> Object needs no unboxing, so phase 1 picks Object
        System.out.println("objectOrLong(Integer) -> " + objectOrLong(Integer.valueOf(5)));

        // a double cannot become a long, so it is boxed to Double, then widened to Object
        System.out.println("objectOrLong(5.0) -> " + objectOrLong(5.0));

        // widening then boxing is not allowed: onlyLong(5) does not compile
        System.out.println("onlyLong(5L) -> " + onlyLong(5L));

        // unboxing: an Integer argument for an int parameter
        System.out.println("square(Integer) -> " + square(Integer.valueOf(4)));

        // unboxing then widening: Short -> short -> long
        System.out.println("phases(Short) -> " + phases(Short.valueOf((short) 1)));

        // this compiles, but unboxing null fails at run time
        Integer missing = null;
        try {
            System.out.println(square(missing));
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        }
    }
}
