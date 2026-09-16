public class M11L10_SuperKeyword {

    static class Vehicle {
        String brand;
        int speed = 50;

        Vehicle(String brand) {
            this.brand = brand;
        }

        String describe() {
            return "Vehicle " + brand;
        }
    }

    static class Car extends Vehicle {
        // a field with the same name hides the parent's field
        int speed = 120;
        int doors;

        // super(...) calls the parent constructor
        // required here: Vehicle has no no-arg constructor
        Car(String brand, int doors) {
            super(brand);
            this.doors = doors;
        }

        // Car replaces describe(); super.describe() still runs the parent's version
        @Override
        String describe() {
            return super.describe() + ", " + doors + " doors";
        }

        // super.speed reads the hidden parent field
        String speeds() {
            return speed + " / " + super.speed;
        }
    }

    public static void main(String[] args) {
        Car car = new Car("Toyota", 4);
        System.out.println("describe = " + car.describe());
        System.out.println("speeds = " + car.speeds());
    }
}
