public class M13L08_ConstructorTrap {

    static class Base {
        Base() {
            // calling an overridable method from a constructor is dangerous
            init();
        }

        void init() {
            System.out.println("Base.init");
        }
    }

    static class Broken extends Base {
        private String name = "broken";
        private int size = 5;

        Broken() {
            // super() runs first; our fields are set only after it returns
            super();
            System.out.println("Broken() name=" + name + " size=" + size);
        }

        @Override
        void init() {
            // runs before our fields are set, so we see null and 0
            System.out.println("Broken.init name=" + name + " size=" + size);
        }
    }

    static class Fixed extends Base {
        private final String name;

        Fixed(String name) {
            // fix 1 (Java 25): set our own field BEFORE calling super()
            this.name = name;
            super();
        }

        @Override
        void init() {
            System.out.println("Fixed.init name=" + name);
        }
    }

    static class Widget {
        private final String label;

        Widget() {
            // fix 2: call only private, final or static methods from a constructor
            label = makeLabel();
        }

        private String makeLabel() {
            return "widget";
        }
    }

    public static void main(String[] args) {
        new Base();
        new Broken();
        new Fixed("ready");
        System.out.println("label: " + new Widget().label);
    }
}
