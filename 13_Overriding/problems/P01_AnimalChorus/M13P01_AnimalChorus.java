public class M13P01_AnimalChorus {

    static class Animal {
        String sound() {
            return "...";
        }
    }

    static class Dog extends Animal {
        @Override
        String sound() {
            return "Woof";
        }
    }

    static class Cat extends Animal {
        @Override
        String sound() {
            return "Meow";
        }
    }

    static class Puppy extends Dog {
        // build on the parent's sound instead of repeating it
        @Override
        String sound() {
            return super.sound().toLowerCase() + "!";
        }
    }

    // works for any Animal: the real object picks its own sound()
    static String chorus(Animal[] animals) {
        String result = "";
        for (int i = 0; i < animals.length; i++) {
            if (i > 0) {
                result += " ";
            }
            result += animals[i].sound();
        }
        return result;
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("new Dog() as Animal", chorus(new Animal[] {new Dog()}), "Woof");
        check("[Animal, Dog, Cat]", chorus(new Animal[] {new Animal(), new Dog(), new Cat()}), "... Woof Meow");
        check("[Puppy, Cat]", chorus(new Animal[] {new Puppy(), new Cat()}), "woof! Meow");
        check("[] (empty)", chorus(new Animal[0]), "");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
