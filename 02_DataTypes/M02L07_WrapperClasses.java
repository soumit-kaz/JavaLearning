public class M02L07_WrapperClasses {

    public static void main(String[] args) {
        // each primitive has a wrapper class: Integer, Double, Character, Boolean...
        Integer boxed = 42;
        Double half = 0.5;
        System.out.println(boxed + " " + half);

        // autoboxing and unboxing happen automatically
        int plain = boxed;
        double result = half * plain;
        System.out.println(result);

        // a wrapper can hold null, a primitive cannot
        Integer unknown = null;
        System.out.println(unknown);

        // helpful static methods
        System.out.println(Integer.toBinaryString(42));
        System.out.println(Integer.toHexString(255));
        System.out.println(Character.isDigit('7'));
        System.out.println(Character.toUpperCase('b'));

        // equals compares the values
        Integer c = 128;
        Integer d = 128;
        System.out.println(c.equals(d));

        // different wrapper types are never equal
        Long longValue = 128L;
        System.out.println(longValue.equals(c));

        // text to number
        int count = Integer.parseInt("123");
        double price = Double.parseDouble("9.75");
        System.out.println(count + 1);
        System.out.println(price * 2);

        // other bases
        System.out.println(Integer.parseInt("ff", 16));

        // anything other than "true" (any case) is false
        System.out.println(Boolean.parseBoolean("TRUE"));
        System.out.println(Boolean.parseBoolean("yes"));

        // number to text
        String s1 = Integer.toString(255);
        String s2 = String.valueOf(255);
        System.out.println(s1 + s2);
    }
}
