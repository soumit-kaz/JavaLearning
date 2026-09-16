public class M13L06_OverridingToString {

    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        // Object.toString() is public, so the override must be public too
        // getClass() gives the real class, so subclasses print their own name
        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + name + ")";
        }
    }

    static class Dog extends Animal {
        int age;

        Dog(String name, int age) {
            super(name);
            this.age = age;
        }

        // build on the parent's text with super.toString()
        @Override
        public String toString() {
            return super.toString() + " age " + age;
        }
    }

    // no override: Cat uses Animal's toString
    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }
    }

    public static void main(String[] args) {
        Animal[] animals = {new Animal("generic"), new Dog("Rex", 3), new Cat("Tom")};
        for (Animal a : animals) {
            // println calls the real object's toString
            System.out.println(a);
        }

        // the variable type does not matter
        Object o = new Dog("Max", 5);
        System.out.println("o = " + o);
    }
}
