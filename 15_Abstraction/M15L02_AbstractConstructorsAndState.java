public class M15L02_AbstractConstructorsAndState {

    abstract static class Employee {
        // an abstract class can hold fields (state)
        private final String name;
        private int daysWorked;

        // an abstract class has constructors; subclasses call them with super(...)
        protected Employee(String name) {
            // one check here protects every kind of employee
            if (name.isEmpty()) {
                throw new IllegalArgumentException("name is empty");
            }
            System.out.println("Employee constructor: " + name);
            this.name = name;
        }

        // concrete methods work on the shared state
        void workDay() {
            daysWorked++;
        }

        double salary() {
            return daysWorked * dailyRate();
        }

        String name() {
            return name;
        }

        // only this part differs between employees
        abstract double dailyRate();
    }

    static class Developer extends Employee {
        Developer(String name) {
            // super(...) runs first, before the rest of this constructor
            super(name);
            System.out.println("Developer constructor");
        }

        @Override
        double dailyRate() {
            return 300;
        }
    }

    // pitfall: the parent constructor calls an abstract method too early
    abstract static class Widget {
        Widget() {
            System.out.println("Widget sees label = " + label());
        }

        abstract String label();
    }

    static class Button extends Widget {
        private final String label;

        Button(String label) {
            // this field is set only after Widget() has finished
            this.label = label;
            System.out.println("Button sees label = " + label());
        }

        @Override
        String label() {
            return label;
        }
    }

    public static void main(String[] args) {
        Employee dev = new Developer("Ana");
        dev.workDay();
        dev.workDay();
        System.out.println(dev.name() + " salary = " + dev.salary());

        try {
            new Developer("");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Widget sees null: the fix is to pass the value up with super(label)
        new Button("OK");
    }
}
