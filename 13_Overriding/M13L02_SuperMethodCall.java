public class M13L02_SuperMethodCall {

    static class Animal {
        String describe() {
            return "animal";
        }
    }

    static class Dog extends Animal {
        // super.describe() runs the parent's version, then we add to it
        @Override
        String describe() {
            return super.describe() + " > dog";
        }
    }

    static class Puppy extends Dog {
        // super goes to Dog, and Dog's super goes to Animal (there is no super.super)
        @Override
        String describe() {
            return super.describe() + " > puppy";
        }
    }

    static class Cat extends Animal {
        // without super, the parent's version is replaced completely
        @Override
        String describe() {
            return "cat";
        }
    }

    public static void main(String[] args) {
        Animal[] animals = {new Animal(), new Dog(), new Puppy(), new Cat()};
        for (Animal a : animals) {
            System.out.println(a.describe());
        }
    }
}
