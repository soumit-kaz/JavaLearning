public class M13L04_MethodHiding {

    static class Animal {
        static String kind() {
            return "Animal.kind";
        }

        String name() {
            return "Animal.name";
        }

        // private methods are not inherited, so they are never overridden
        private String secret() {
            return "Animal.secret";
        }

        String reveal() {
            // inside Animal this always calls Animal.secret()
            return secret();
        }
    }

    static class Dog extends Animal {
        // a static method with the same signature HIDES the parent's one
        static String kind() {
            return "Dog.kind";
        }

        @Override
        String name() {
            return "Dog.name";
        }

        // a brand new method; @Override here would not compile
        String secret() {
            return "Dog.secret";
        }
    }

    @SuppressWarnings("static")
    public static void main(String[] args) {
        Animal a = new Dog();

        // instance method: the OBJECT type decides
        System.out.println("a.name(): " + a.name());

        // static method: the VARIABLE type decides
        System.out.println("a.kind(): " + a.kind());

        // better: call static methods on the class
        System.out.println("Dog.kind(): " + Dog.kind());

        // private: Animal's code never reaches Dog.secret()
        System.out.println("a.reveal(): " + a.reveal());
        System.out.println("dog.secret(): " + new Dog().secret());
    }
}
