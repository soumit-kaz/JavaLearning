public class M11L14_Composition {

    static class Engine {
        int horsePower;

        Engine(int horsePower) {
            this.horsePower = horsePower;
        }

        // copy constructor
        Engine(Engine other) {
            this.horsePower = other.horsePower;
        }
    }

    // composition: a Car HAS-A Engine
    static class Car {
        String model;
        Engine engine;

        Car(String model, Engine engine) {
            this.model = model;
            this.engine = engine;
        }

        // the car hands the work to its engine (delegation)
        String drive() {
            return model + " " + engine.horsePower + " hp";
        }

        // shallow copy: shares the same Engine
        Car shallowCopy() {
            return new Car(model, engine);
        }

        // deep copy: makes a new Engine too
        Car deepCopy() {
            return new Car(model, new Engine(engine));
        }
    }

    public static void main(String[] args) {
        Car family = new Car("Corolla", new Engine(120));
        System.out.println("family: " + family.drive());

        Car shallow = family.shallowCopy();
        Car deep = family.deepCopy();

        // the shallow copy sees the change, the deep copy does not
        family.engine.horsePower = 150;
        System.out.println("shallow: " + shallow.drive());
        System.out.println("deep: " + deep.drive());

        // a part can be swapped at run time
        family.engine = new Engine(200);
        System.out.println("family: " + family.drive());
        System.out.println("shallow: " + shallow.drive());
    }
}
