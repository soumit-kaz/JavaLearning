public class M14L01_StaticBinding {

    static class Animal {
        String name = "Animal";

        // private: not inherited, so never overridden
        private String secret() {
            return "Animal.secret";
        }

        static String kind() {
            return "Animal.kind";
        }

        // final: exactly one version exists
        final String tag() {
            return "Animal.tag";
        }

        String sound() {
            return "Animal.sound";
        }

        String describe() {
            // secret() and kind() are fixed to Animal's versions at compile time
            return secret() + " " + kind();
        }
    }

    static class Dog extends Animal {
        // a second, separate field that hides Animal.name
        String name = "Dog";

        // a brand new method, not an override (@Override would not compile)
        @SuppressWarnings("unused")
        private String secret() {
            return "Dog.secret";
        }

        // hides Animal.kind(); static methods are not overridden
        static String kind() {
            return "Dog.kind";
        }

        @Override
        String sound() {
            return "Dog.sound";
        }

        String parentSound() {
            // super.sound() always means Animal.sound()
            return super.sound();
        }
    }

    // a static call through a variable uses the declared type
    @SuppressWarnings("static")
    static String kindOf(Animal ref) {
        return ref.kind();
    }

    static String greet(Animal a) {
        return "greet(Animal)";
    }

    static String greet(Dog d) {
        return "greet(Dog)";
    }

    public static void main(String[] args) {
        Animal a = new Dog();

        System.out.println("describe: " + a.describe());
        System.out.println("kindOf(a): " + kindOf(a));
        // no object is needed, so even null works
        System.out.println("kindOf(null): " + kindOf(null));
        System.out.println("tag: " + a.tag());
        System.out.println("parentSound: " + new Dog().parentSound());

        // fields use the declared type
        System.out.println("a.name: " + a.name);
        System.out.println("((Dog) a).name: " + ((Dog) a).name);

        // the overload is chosen from the declared argument type
        System.out.println("greet(a): " + greet(a));
        System.out.println("greet((Dog) a): " + greet((Dog) a));
    }
}
