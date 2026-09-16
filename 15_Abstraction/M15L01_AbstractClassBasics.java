public class M15L01_AbstractClassBasics {

    // abstract class: a general idea that is not complete on its own
    abstract static class Animal {
        // abstract method: no body, ends with a semicolon
        abstract String sound();

        // concrete method: shared by all animals
        String describe() {
            return getClass().getSimpleName() + " says " + sound();
        }
    }

    // a concrete subclass must implement every abstract method
    static class Dog extends Animal {
        @Override
        String sound() {
            return "Woof";
        }
    }

    static class Cow extends Animal {
        // access may be widened (package-private -> public), never reduced
        @Override
        public String sound() {
            return "Moo";
        }
    }

    // abstract with no abstract methods: it only forbids "new BaseController()"
    abstract static class BaseController {
        String handle(String request) {
            return getClass().getSimpleName() + " handles " + request;
        }
    }

    static class HomeController extends BaseController {
    }

    public static void main(String[] args) {
        // new Animal() does not compile, but a variable of the abstract type is fine
        Animal pet = new Dog();
        System.out.println(pet.describe());
        pet = new Cow();
        System.out.println(pet.describe());

        // an array of an abstract type only holds references, so it is allowed
        Animal[] farm = new Animal[3];
        farm[0] = new Dog();
        System.out.println("farm[1] = " + farm[1]);

        // new Animal() { ... } creates an unnamed subclass, not an Animal itself
        Animal cat = new Animal() {
            @Override
            String sound() {
                return "Meow";
            }
        };
        System.out.println("anonymous: " + cat.sound());

        System.out.println(new HomeController().handle("/home"));
    }
}
