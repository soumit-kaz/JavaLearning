public class M04P03_LeapYear {

    public static void main(String[] args) {
        int year;
        boolean expected;
        boolean leap;

        // test 1: divisible by 4 but not by 100
        year = 2024;
        expected = true;
        // leap if divisible by 400, or by 4 but not by 100
        leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        System.out.println(year + " -> " + leap + (leap == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 2: divisible by 100 but not by 400
        year = 1900;
        expected = false;
        leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        System.out.println(year + " -> " + leap + (leap == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 3: divisible by 400
        year = 2000;
        expected = true;
        leap = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        System.out.println(year + " -> " + leap + (leap == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));

        // test 4: the same rule written as an else-if ladder
        year = 2023;
        expected = false;
        if (year % 400 == 0) {
            leap = true;
        } else if (year % 100 == 0) {
            leap = false;
        } else if (year % 4 == 0) {
            leap = true;
        } else {
            leap = false;
        }
        System.out.println(year + " -> " + leap + (leap == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
