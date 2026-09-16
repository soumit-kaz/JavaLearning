public class M12L06_MostSpecificAndNull {

    static class Animal {
    }

    static class Dog extends Animal {
    }

    static class Puppy extends Dog {
    }

    static class Cat {
    }

    static String text(String s) { return "String"; }
    static String text(Object o) { return "Object"; }

    static String feed(Object o) { return "Object"; }
    static String feed(Animal a) { return "Animal"; }
    static String feed(Dog d) { return "Dog"; }

    static String pet(Cat c) { return "Cat"; }
    static String pet(Dog d) { return "Dog"; }

    static String arr(Object o) { return "Object"; }
    static String arr(Object[] o) { return "Object[]"; }
    static String arr(String[] o) { return "String[]"; }

    static String nums(Object o) { return "Object"; }
    static String nums(int... xs) { return xs == null ? "int... (null array)" : "int..."; }

    static String pair(Object a, String b) { return "(Object, String)"; }
    static String pair(String a, Object b) { return "(String, Object)"; }
    static String pair(String a, String b) { return "(String, String)"; }

    public static void main(String[] args) {
        // null fits any reference type; the most specific type wins
        System.out.println("text(null) -> " + text(null));
        // a cast changes which method is chosen
        System.out.println("text((Object) null) -> " + text((Object) null));

        // Dog is the most specific of the three
        System.out.println("feed(null) -> " + feed(null));
        System.out.println("feed(new Animal()) -> " + feed(new Animal()));
        // no feed(Puppy), so the closest parent (Dog) wins
        System.out.println("feed(new Puppy()) -> " + feed(new Puppy()));

        // pet(null) does not compile: Cat and Dog are unrelated
        System.out.println("pet((Cat) null) -> " + pet((Cat) null));
        // a typed variable also removes the ambiguity
        Dog nobody = null;
        System.out.println("pet(nobody) -> " + pet(nobody));

        // String[] is more specific than Object[], which beats Object
        System.out.println("arr(null) -> " + arr(null));
        System.out.println("arr(new Integer[0]) -> " + arr(new Integer[0]));
        // int[] is not an Object[], only an Object
        System.out.println("arr(new int[0]) -> " + arr(new int[0]));

        // surprise: null fits int[], and int[] is more specific than Object
        System.out.println("nums(null) -> " + nums(null));

        // without pair(String, String) this call would be ambiguous
        System.out.println("pair(\"a\", \"b\") -> " + pair("a", "b"));
        System.out.println("pair(1, \"b\") -> " + pair(1, "b"));
    }
}
