public class M14L02_DynamicBinding {

    static class Animal {
        String sound() {
            return "...";
        }

        // written once in the parent; sound() here is still dynamic
        String describe() {
            return getClass().getSimpleName() + " says " + sound();
        }
    }

    static class Dog extends Animal {
        @Override
        String sound() {
            return "Woof";
        }

        String fetch() {
            return "fetching";
        }
    }

    static class Cat extends Animal {
        @Override
        String sound() {
            return "Meow";
        }
    }

    enum Operation {
        // each constant can override the method with its own body
        PLUS {
            @Override
            int apply(int a, int b) {
                return a + b;
            }
        },
        TIMES {
            @Override
            int apply(int a, int b) {
                return a * b;
            }
        },
        // this constant keeps the default body
        FIRST;

        int apply(int a, int b) {
            return a;
        }
    }

    public static void main(String[] args) {
        // one line of code, three different methods run
        Animal[] animals = { new Animal(), new Dog(), new Cat() };
        for (Animal animal : animals) {
            System.out.println(animal.describe());
        }

        Animal a = new Dog();
        // a.fetch() does not compile: the declared type Animal has no fetch()
        System.out.println("cast: " + ((Dog) a).fetch());

        // instanceof checks the runtime type and casts in one step
        if (a instanceof Dog dog) {
            System.out.println("instanceof: " + dog.fetch());
        }

        // a wrong cast compiles but fails at runtime
        try {
            Animal plain = new Animal();
            System.out.println(((Dog) plain).fetch());
        } catch (ClassCastException e) {
            System.out.println("wrong cast: ClassCastException");
        }

        // the declared type is Operation; the constant's own body runs
        for (Operation op : Operation.values()) {
            System.out.println(op + "(6, 3) = " + op.apply(6, 3));
        }
    }
}
