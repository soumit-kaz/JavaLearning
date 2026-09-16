public class M13L01_OverridingBasics {

    static class Animal {
        String sound() {
            return "...";
        }
    }

    static class Dog extends Animal {
        // same name and same parameters: this overrides Animal.sound()
        // @Override asks the compiler to check that it really overrides something
        @Override
        String sound() {
            return "Woof";
        }

        // different parameters: an OVERLOAD, a separate new method
        String sound(int times) {
            return sound().repeat(times);
        }
    }

    static class Cow extends Animal {
        // typo: capital S makes a NEW method; @Override here would not compile
        String Sound() {
            return "Moo";
        }
    }

    public static void main(String[] args) {
        // the variable type is Animal, but the real object decides which method runs
        Animal[] animals = {new Animal(), new Dog(), new Cow()};
        for (Animal a : animals) {
            System.out.println(a.getClass().getSimpleName() + ": " + a.sound());
        }

        Dog dog = new Dog();
        System.out.println("overload: " + dog.sound(2));
    }
}
