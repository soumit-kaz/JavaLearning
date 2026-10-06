// OOP example: Encapsulation, Inheritance, Polymorphism

// Parent class: common data for every person
class Person
{
     private String NationalId;   // private: only Person can access it directly
     protected String name;       // protected: Person and its child classes can access it
     protected Integer age;
     protected String address;

     // Constructor: runs when we create an object with "new"
     Person(String NationalId, String name, Integer age, String address)
     {
          this.NationalId = NationalId;   // "this" means the current object
          this.name = name;
          this.age = age;
          this.address = address;
     }

     // Overloading: same constructor name, different parameters
     Person(String NationalId)
     {
          this.NationalId = NationalId;
     }

     // Getters: let other classes read the data
     public String getName() {
          return name;
     }

     public Integer getAge() {
          return age;
     }

     public String getAddress() {
          return address;
     }

     public String getNationalId() {
          return NationalId;
     }

     // Child classes will override this method
     public void displayDetails() {
          System.out.println("Name: " + name);
          System.out.println("Age: " + age);
          System.out.println("Address: " + address);
          System.out.println("National ID: " + NationalId);
     }
}

// Inheritance: Student gets everything from Person and adds its own data
class Student extends Person
{
     private String studentId;
     private String semester;

     Student(String NationalId, String name, Integer age, String address, String studentId, String semester)
     {
          super(NationalId, name, age, address); // Call the Person constructor
          this.studentId = studentId;
          this.semester = semester;
     }

     public String getStudentId() {
          return studentId;
     }

     public String getSemester() {
          return semester;
     }

     // Overriding: Student's own version of displayDetails()
     public void displayDetails() {
          super.displayDetails(); // Print Person details first
          System.out.println("Student ID: " + studentId);
          System.out.println("Semester: " + semester);
     }
}

// Inheritance: Employee also gets everything from Person
class Employee extends Person
{
     private String employeeId;
     private Double salary;

     Employee(String NationalId, String name, Integer age, String address, String employeeId, Double salary)
     {
          super(NationalId, name, age, address); // Call the Person constructor
          this.employeeId = employeeId;
          this.salary = salary;
     }

     public String getEmployeeId() {
          return employeeId;
     }

     public Double getSalary() {
          return salary;
     }

     // Overriding: Employee's own version of displayDetails()
     public void displayDetails() {
          super.displayDetails(); // Print Person details first
          System.out.println("Employee ID: " + employeeId);
          System.out.println("Salary: " + salary);
     }
}

public class OopAll
{
     public static void main(String[] args)
     {
          // Create objects
          Student student1 = new Student("N12345", "Jane Smith", 20, "456 Elm St", "S12345", "2nd");
          Student student2 = new Student("N67890", "Mike Johnson", 22, "789 Oak St", "S67890", "4th");
          Employee employee1 = new Employee("N11111", "John Doe", 30, "123 Main St", "E11111", 50000.0);
          Employee employee2 = new Employee("N22222", "Jane Doe", 28, "456 Secondary St", "E22222", 60000.0);

          // Each object calls its own version of displayDetails()
          student1.displayDetails();
          student2.displayDetails();
          employee1.displayDetails();
          employee2.displayDetails();
     }
}
