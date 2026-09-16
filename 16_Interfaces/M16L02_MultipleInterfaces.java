public class M16L02_MultipleInterfaces {

    interface Flyer {
        String move();

        String fly();
    }

    interface Swimmer {
        String move();

        String swim();
    }

    // an interface can extend several interfaces (never a class)
    interface Amphibious extends Flyer, Swimmer {
    }

    static class Bird {
        String name() {
            return getClass().getSimpleName();
        }
    }

    // a class extends ONE class but can implement MANY interfaces
    static class Duck extends Bird implements Flyer, Swimmer {
        // move() is in both interfaces; one method satisfies both
        @Override
        public String move() {
            return "waddles";
        }

        @Override
        public String fly() {
            return "flaps";
        }

        @Override
        public String swim() {
            return "paddles";
        }
    }

    // implementing Amphibious means implementing Flyer and Swimmer too
    static class Seaplane implements Amphibious {
        @Override
        public String move() {
            return "taxis";
        }

        @Override
        public String fly() {
            return "cruises";
        }

        @Override
        public String swim() {
            return "floats";
        }
    }

    // an empty "marker" interface only tags a class (like Cloneable)
    interface Deletable {
    }

    static class Invoice implements Deletable {
    }

    public static void main(String[] args) {
        // the same object fits every type it has
        Duck duck = new Duck();
        Bird bird = duck;
        Flyer flyer = duck;
        Swimmer swimmer = duck;
        System.out.println("bird: " + bird.name());
        System.out.println("flyer: " + flyer.fly());
        System.out.println("swimmer: " + swimmer.swim());

        Swimmer plane = new Seaplane();
        System.out.println("plane is Flyer: " + (plane instanceof Flyer));
        // false: Duck has both parts but never said "implements Amphibious"
        System.out.println("duck is Amphibious: " + (flyer instanceof Amphibious));

        // instanceof checks a marker interface
        Object[] items = {new Invoice(), duck};
        for (Object item : items) {
            System.out.println(item.getClass().getSimpleName() + " deletable: " + (item instanceof Deletable));
        }
    }
}
