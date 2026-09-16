public class M01L06_Printf {

    public static void main(String[] args) {
        // %s is text, %d a whole number, %n ends the line
        System.out.printf("%s is %d years old%n", "Ada", 36);

        // %f shows 6 decimals, %.2f rounds to 2
        System.out.printf("%f%n", 3.14159);
        System.out.printf("%.2f%n", 3.14159);

        // values fill the placeholders in order
        System.out.printf("%d + %d = %d%n", 2, 3, 2 + 3);

        // %% prints a percent sign
        System.out.printf("Score: %d%%%n", 95);

        // printf does not end the line by itself
        System.out.printf("no newline, ");
        System.out.printf("same line%n");

        // %5d is 5 wide on the right, %-5d on the left, %05d pads with zeros
        System.out.printf("[%5d]%n", 42);
        System.out.printf("[%-5d]%n", 42);
        System.out.printf("[%05d]%n", 42);

        // widths also work for text
        System.out.printf("[%8s]%n", "Java");

        // a comma groups the thousands
        System.out.printf("%,d%n", 1234567);

        // widths make neat columns
        System.out.printf("%-8s%6s%n", "Item", "Price");
        System.out.printf("%-8s%6.2f%n", "Tea", 2.5);
        System.out.printf("%-8s%6.2f%n", "Cake", 12.75);
    }
}
