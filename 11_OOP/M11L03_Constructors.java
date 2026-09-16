public class M11L03_Constructors {

    // no constructor written: Java adds a hidden no-arg one
    static class Box {
        int size;
    }

    static class Cup {
        int ml;
        String color;

        // a constructor has the class name and no return type
        Cup(int amount, String paint) {
            ml = amount;
            color = paint;
        }
    }

    static class Counter {
        static int created;
        int id = step("field initializer");

        // runs once, the first time the class is used
        static {
            System.out.println("static block");
        }

        // runs for every object, before the constructor body
        {
            System.out.println("instance block");
        }

        Counter() {
            created++;
            System.out.println("constructor " + created);
        }

        static int step(String name) {
            System.out.println(name);
            return created + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("box.size = " + new Box().size);

        // once a constructor is written, the hidden one is gone: new Cup() does not compile
        Cup cup = new Cup(250, "red");
        System.out.println("cup = " + cup.ml + " " + cup.color);

        // order: static block (once), field initializers and instance blocks, constructor body
        new Counter();
        new Counter();
    }
}
