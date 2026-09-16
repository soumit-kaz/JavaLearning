public class M11L09_Inheritance {

    static class Vehicle {
        String brand = "generic";
        // protected: visible to subclasses
        protected int wheels;

        Vehicle() {
            System.out.println("Vehicle constructor");
        }

        void honk() {
            System.out.println(brand + " honks");
        }
    }

    // Car IS-A Vehicle: it inherits brand, wheels and honk()
    static class Car extends Vehicle {
        int doors = 4;

        // Java runs Vehicle() first, automatically
        Car() {
            wheels = 4;
            System.out.println("Car constructor");
        }

        // a child can add its own methods
        void open() {
            System.out.println(brand + " opens " + doors + " doors");
        }
    }

    // inheritance can go down many levels
    static class SportsCar extends Car {
        SportsCar() {
            brand = "Ferrari";
            doors = 2;
            System.out.println("SportsCar constructor");
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.brand = "Toyota";
        car.honk();
        car.open();
        System.out.println("wheels = " + car.wheels);

        // parents are always built first
        SportsCar fast = new SportsCar();
        fast.honk();
        fast.open();
    }
}
