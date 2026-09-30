// Template
class Car {
    // Attributes
    // Access Modifier

    private Integer length;
    private String ownerName;
    private String numberPlate;

    private boolean isRunning;

    // constructor
    Car() // Default Constructor
    {
        this.length = 20;
        this.ownerName = "Alex";
        this.numberPlate = "DH-1234";
        this.isRunning = false;
    }
    Car(Integer length, String ownerName) {
        this.length = length;
        this.ownerName = ownerName;
    }

    // polymorphysm
    // funtion/method overloading
    // setter
    Car(Integer length, String ownerName, String numberPlate) {
        this.length = length;
        this.ownerName = ownerName;
        this.numberPlate = numberPlate;
    }
    // normal function --> inside a class
    // getter method
    Integer getLength() // method ta nije public
    {
        return this.length; // ei method len ke dekhte pay
    }
    String getOwnerName()
    {
        return this.ownerName;
    }
    String getNumberPlate()
    {
        return this.numberPlate;

    }
    boolean getRunningStatus()
    {
        return this.isRunning;

    }
    void printAllProperty()
    {
        System.out.println("+++++++++++++++++++++++");
        System.out.println(getLength());
        System.out.println(getNumberPlate());
        System.out.println(getOwnerName());
        System.out.println(getRunningStatus());
        System.out.println("+++++++++++++++++++++++");
        System.out.println();
    }

    void start() {
        if (this.isRunning == false) {
            System.out.println("Car started.");
            this.isRunning = true;
        } else {
            System.out.println("Already running");
        }

    }

    void stop() {
        if (this.isRunning == true) {
            System.out.println("Car Stop.");
            this.isRunning = false;
        }

        else
        {
            System.out.println("Start the car first.");
        }
    }
}

public class Extra {

    public static void main(String[] args) {
        // Instantiate
        Car carObj1 = new Car(50, "John"); //
        Car carObj2 = new Car(100, "Alice", "AL-789");
        Car carObj3 = new Car();

        

        carObj1.start();
        
        carObj1.printAllProperty();
        carObj2.printAllProperty();
        carObj3.printAllProperty();

    }
}
