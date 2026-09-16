public class M02L01_Variables {

    public static void main(String[] args) {
        // declare a variable and give it a value
        int age = 25;
        System.out.println("age = " + age);

        // a variable can get a new value, which may use the old one
        age = age + 1;
        System.out.println("age = " + age);

        // declare first, assign later
        int year;
        year = 2025;

        // copying takes the value; later changes do not affect the copy
        int copy = year;
        year = 3000;
        System.out.println("copy = " + copy + ", year = " + year);

        // names are case-sensitive: two different variables
        int total = 1;
        int Total = 2;
        System.out.println(total + " " + Total);

        // String holds text; length() counts its characters
        String name = "Ada";
        System.out.println("Hello, " + name);
        System.out.println("length = " + name.length());

        // final means the value can be set only once
        final int DAYS_IN_WEEK = 7;
        final int HOURS_PER_DAY = 24;
        System.out.println("hours in week = " + DAYS_IN_WEEK * HOURS_PER_DAY);

        // var lets the compiler pick the type from the value
        var count = 10;
        var price = 9.99;
        var city = "Paris";
        System.out.println(count / 4);
        System.out.println(price * 2);
        System.out.println(city);
    }
}
