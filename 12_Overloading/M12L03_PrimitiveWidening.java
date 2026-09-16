public class M12L03_PrimitiveWidening {

    static String all(byte x) { return "byte"; }
    static String all(short x) { return "short"; }
    static String all(char x) { return "char"; }
    static String all(int x) { return "int"; }
    static String all(long x) { return "long"; }
    static String all(float x) { return "float"; }
    static String all(double x) { return "double"; }

    static String longOrDouble(long x) { return "long"; }
    static String longOrDouble(double x) { return "double"; }

    static String floatOrDouble(float x) { return "float"; }
    static String floatOrDouble(double x) { return "double"; }

    static String charOrInt(char x) { return "char"; }
    static String charOrInt(int x) { return "int"; }

    static String shortOrInt(short x) { return "short"; }
    static String shortOrInt(int x) { return "int"; }

    public static void main(String[] args) {
        // each literal has its own type; there are no byte or short literals
        System.out.println("(byte) 1 -> " + all((byte) 1));
        System.out.println("'a' -> " + all('a'));
        System.out.println("1 -> " + all(1) + ", 1L -> " + all(1L));
        System.out.println("1.0f -> " + all(1.0f) + ", 1.0 -> " + all(1.0));

        // no exact match: the closest wider type wins
        System.out.println("longOrDouble(1) -> " + longOrDouble(1));
        System.out.println("longOrDouble('a') -> " + longOrDouble('a'));
        System.out.println("longOrDouble(1.5f) -> " + longOrDouble(1.5f));

        // long -> float also counts as widening
        System.out.println("floatOrDouble(1L) -> " + floatOrDouble(1L));

        // byte cannot widen to char, and an int is never narrowed for a call
        System.out.println("charOrInt((byte) 1) -> " + charOrInt((byte) 1));
        System.out.println("charOrInt(120) -> " + charOrInt(120));

        // byte widens to short, but char cannot widen to short
        System.out.println("shortOrInt((byte) 1) -> " + shortOrInt((byte) 1));
        System.out.println("shortOrInt('x') -> " + shortOrInt('x'));

        // the type of an expression decides
        byte a = 1;
        byte b = 2;
        System.out.println("a + b -> " + all(a + b));
        // ++ keeps the type byte
        System.out.println("a++ -> " + all(a++));
        System.out.println("'a' + 1 -> " + all('a' + 1));
        System.out.println("5 / 2.0 -> " + all(5 / 2.0));
        // an int constant that fits in a char makes this ternary a char
        System.out.println("true ? 1 : 'a' -> " + all(true ? 1 : 'a'));

        // println has no byte version, so a byte widens to int
        System.out.println(b);
    }
}
