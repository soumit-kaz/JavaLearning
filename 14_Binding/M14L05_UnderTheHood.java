import java.lang.reflect.Method;

public class M14L05_UnderTheHood {

    // a pretend method table: slot 0 = sound, slot 1 = move
    static String[] animalTable = { "Animal.sound", "Animal.move" };
    // a child copies the parent's table and replaces what it overrides
    static String[] dogTable = { "Dog.sound", animalTable[1] };

    // the compiler stores only the slot; the object's table gives the body
    static String call(String[] objectTable, int slot) {
        return objectTable[slot];
    }

    public static class Animal {
        public String sound() {
            return "Animal.sound";
        }
    }

    public static class Dog extends Animal {
        @Override
        public String sound() {
            return "Dog.sound";
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("animal slot 0: " + call(animalTable, 0));
        System.out.println("dog slot 0: " + call(dogTable, 0));
        System.out.println("dog slot 1: " + call(dogTable, 1));

        // reflection (advanced): find a method by its name at runtime
        Animal pet = new Dog();
        Method sound = Animal.class.getMethod("sound");
        // the Method comes from Animal, but invoke() still runs Dog.sound()
        System.out.println("invoke: " + sound.invoke(pet));

        // a typo is only found at runtime
        try {
            Animal.class.getMethod("sond");
        } catch (NoSuchMethodException e) {
            System.out.println("typo: NoSuchMethodException");
        }
    }
}
