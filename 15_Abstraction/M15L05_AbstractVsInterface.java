public class M15L05_AbstractVsInterface {

    // abstract class: can have fields and constructors
    abstract static class Bird {
        private final String name;

        Bird(String name) {
            this.name = name;
        }

        String name() {
            return name;
        }

        abstract String call();
    }

    // interface (next module): only a method signature here, no fields, no constructor
    interface Swimmer {
        String swim();
    }

    // a class extends only ONE class, but may also implement interfaces
    static class Duck extends Bird implements Swimmer {
        Duck() {
            super("Duck");
        }

        @Override
        String call() {
            return "Quack";
        }

        // interface methods are public
        @Override
        public String swim() {
            return "paddles";
        }
    }

    static class Penguin extends Bird {
        Penguin() {
            super("Penguin");
        }

        @Override
        String call() {
            return "Squawk";
        }
    }

    public static void main(String[] args) {
        Bird[] birds = {new Duck(), new Penguin()};
        for (Bird b : birds) {
            System.out.println(b.name() + " says " + b.call());
            // only some birds can swim
            if (b instanceof Swimmer s) {
                System.out.println(b.name() + " swim: " + s.swim());
            }
        }
        // class Duck extends Bird, Fish { } does not compile: only one parent class
    }
}
