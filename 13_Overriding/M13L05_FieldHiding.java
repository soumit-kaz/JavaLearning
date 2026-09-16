public class M13L05_FieldHiding {

    static class Animal {
        String name = "animal";

        String getName() {
            return name;
        }
    }

    static class Dog extends Animal {
        // a field with the same name HIDES the parent's field: the object has both
        String name = "dog";

        @Override
        String getName() {
            return name;
        }

        // super.name reaches the hidden field
        String bothNames() {
            return name + " / " + super.name;
        }
    }

    public static void main(String[] args) {
        Dog d = new Dog();
        Animal a = d;

        // fields: the VARIABLE type decides
        System.out.println("a.name: " + a.name);
        System.out.println("d.name: " + d.name);

        // methods: the OBJECT type decides
        System.out.println("a.getName(): " + a.getName());

        System.out.println("both: " + d.bothNames());

        // tip: keep fields private and use getters, so hiding never surprises you
    }
}
