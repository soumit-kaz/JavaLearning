public class M16L05_DiamondRules {

    interface Animal {
        default String sound() {
            return "Animal";
        }
    }

    interface Dog extends Animal {
        @Override
        default String sound() {
            return "Dog";
        }
    }

    interface Cat {
        default String sound() {
            return "Cat";
        }
    }

    static class Robot {
        public String sound() {
            return "Robot";
        }
    }

    // rule 1: a method written in a class beats any interface default
    static class RoboDog extends Robot implements Dog {
    }

    // rule 2: Dog extends Animal, so Dog is more specific and wins
    static class Puppy implements Animal, Dog {
    }

    // rule 3: two unrelated defaults clash, so the class MUST override
    // without the override: "types Dog and Cat are incompatible"
    static class CatDog implements Dog, Cat {
        @Override
        public String sound() {
            // pick or combine versions with X.super (X must be a direct parent)
            return Dog.super.sound() + "+" + Cat.super.sound();
        }
    }

    interface Named {
        String name();
    }

    interface DefaultNamed {
        default String name() {
            return "unknown";
        }
    }

    // an abstract method and a default with the same name also force an override
    static class Item implements Named, DefaultNamed {
        @Override
        public String name() {
            return "item/" + DefaultNamed.super.name();
        }
    }

    // a sub-interface can make a default abstract again
    interface StrictDog extends Dog {
        @Override
        String sound();
    }

    // so this class is forced to write its own sound()
    static class Wolf implements StrictDog {
        @Override
        public String sound() {
            return "Wolf";
        }
    }

    public static void main(String[] args) {
        Animal[] animals = {new RoboDog(), new Puppy(), new CatDog(), new Wolf()};
        for (Animal a : animals) {
            System.out.println(a.getClass().getSimpleName() + ": " + a.sound());
        }
        System.out.println("Item: " + new Item().name());
    }
}
