public class M12L01_OverloadingBasics {

    // same name, different number of parameters
    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // same name, different parameter types; return types may differ too
    static double add(double a, double b) {
        return a + b;
    }

    static String add(String a, String b) {
        return a + b;
    }

    // a different return type alone is NOT enough:
    // long add(int a, int b) would not compile

    // same types in a different order is also a valid overload
    static String describe(String name, int age) {
        return "name first: " + name + " " + age;
    }

    static String describe(int age, String name) {
        return "age first: " + age + " " + name;
    }

    // the JVM only starts main(String[]); this is an ordinary overload
    static void main(int number) {
        System.out.println("main(int) " + number);
    }

    public static void main(String[] args) {
        // the compiler picks the method from the arguments
        System.out.println("add(1, 2) = " + add(1, 2));
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));
        System.out.println("add(1.5, 2.0) = " + add(1.5, 2.0));
        System.out.println("add(\"a\", \"b\") = " + add("a", "b"));

        System.out.println(describe("Ann", 30));
        System.out.println(describe(30, "Ann"));

        main(7);
    }
}
