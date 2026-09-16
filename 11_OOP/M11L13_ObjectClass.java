public class M11L13_ObjectClass {

    // no extends written: Java adds "extends Object"
    static class Pen {
    }

    static class FancyPen extends Pen {
    }

    // an Object parameter accepts any object
    static String describe(Object x) {
        return "a " + x.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        // an Object variable can hold any object
        Object o = new Pen();
        System.out.println("class = " + o.getClass().getSimpleName());
        o = "hello";
        System.out.println("class = " + o.getClass().getSimpleName());

        // every class inherits toString, equals and hashCode from Object
        Pen a = new Pen();
        Pen b = new Pen();
        System.out.println("toString has Pen@: " + a.toString().contains("Pen@"));
        // Object's equals works like ==
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("a.equals(a): " + a.equals(a));
        System.out.println("same hash: " + (a.hashCode() == a.hashCode()));

        // getClass is exact; instanceof also accepts subclasses
        Pen fancy = new FancyPen();
        System.out.println("getClass == Pen: " + (fancy.getClass() == Pen.class));
        System.out.println("instanceof Pen: " + (fancy instanceof Pen));

        System.out.println(describe(fancy));
        System.out.println(describe("text"));
    }
}
