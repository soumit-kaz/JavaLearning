public class M09L04_InitializationOrder {

    // a small class inside our file, used to show the order of steps
    static class Box {
        // static parts run once, the first time Box is used
        static {
            System.out.println("Box static block");
        }

        // instance block: runs for every new object, before the constructor body
        {
            System.out.println("Box instance block");
        }

        // constructor: runs every time we write new Box()
        Box() {
            System.out.println("Box constructor");
        }
    }

    static class Later {
        static final int SIZE = 3;
        static int value = 42;

        static {
            System.out.println("Later static block");
        }

        static void ping() {
            System.out.println("Later ping");
        }
    }

    public static void main(String[] args) {
        System.out.println("main");

        // first use of Box: static block, then instance block, then constructor
        new Box();

        // second object: the static block does not run again
        new Box();

        // a compile-time constant is copied in, so Later is not set up yet
        System.out.println("SIZE = " + Later.SIZE);

        // reading a normal static variable sets Later up first
        System.out.println("value = " + Later.value);

        // already set up, so the static block does not run again
        Later.ping();
    }
}
