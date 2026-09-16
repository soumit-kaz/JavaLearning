public class M03L04_ComparisonOperators {

    public static void main(String[] args) {
        int a = 7;
        int b = 10;

        // every comparison gives a boolean
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a <= 7: " + (a <= 7));
        System.out.println("a >= 8: " + (a >= 8));

        // the result can be stored
        boolean isBigger = b > a;
        System.out.println("isBigger: " + isBigger);

        // chars are compared by their codes
        System.out.println("'Z' < 'a': " + ('Z' < 'a'));

        // decimals are not exact, so == can fail
        double sum = 0.1 + 0.2;
        System.out.println("sum == 0.3: " + (sum == 0.3));

        // NaN is not equal to anything, not even itself
        double nan = 0.0 / 0.0;
        System.out.println("NaN == NaN: " + (nan == nan));

        // == on Strings compares objects; equals compares the letters
        String first = "java";
        String built = "ja";
        built += "va";
        System.out.println("first == built: " + (first == built));
        System.out.println("first.equals(built): " + first.equals(built));
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase(first));

        // compareTo: negative if before, 0 if equal, positive if after
        System.out.println("apple vs banana: " + "apple".compareTo("banana"));

        // wrappers: == works for -128 to 127 only because they are cached
        Integer small1 = 127;
        Integer small2 = 127;
        Integer big1 = 128;
        Integer big2 = 128;
        System.out.println("127 == 127: " + (small1 == small2));
        System.out.println("128 == 128: " + (big1 == big2));
        System.out.println("128 equals 128: " + big1.equals(big2));
    }
}
