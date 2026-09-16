public class M02P02_TemperatureConversion {

    public static void main(String[] args) {
        double celsius = 25.0;

        // 9.0 makes the division happen in double
        double fahrenheit = celsius * 9.0 / 5 + 32;
        System.out.println(celsius + " C = " + fahrenheit + " F");

        // 9 / 5 is integer division and gives 1, so this answer is wrong
        double wrong = celsius * (9 / 5) + 32;
        System.out.println("with 9 / 5: " + wrong + " F");

        double bodyF = 98.6;
        // convert back with the reverse formula
        double bodyC = (bodyF - 32) * 5.0 / 9;
        System.out.println(bodyF + " F = " + bodyC + " C");

        double freezing = -40.0;
        System.out.println(freezing + " C = " + (freezing * 9.0 / 5 + 32) + " F");
    }
}
