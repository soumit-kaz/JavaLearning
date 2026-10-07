public class M20L07_FormattedOutput {

    public static void main(String[] args) {
        // printf puts values into a pattern. %s is text, %d a whole number,
        // %f a decimal number, and %n ends the line.
        System.out.printf("%s is %d years old%n", "Ann", 30);

        // %.2f means two digits after the point
        System.out.printf("price: %.2f%n", 3.5);

        // a number after % is the width, which lines columns up.
        // %-10s pushes text to the left, %10s to the right.
        System.out.printf("[%10s]%n", "right");
        System.out.printf("[%-10s]%n", "left");

        // a price list with columns that line up
        String[] names = {"tea", "biscuits", "milk"};
        double[] prices = {3.5, 12.25, 1.0};
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-10s %8.2f%n", names[i], prices[i]);
        }

        // String.format builds the same text without printing it
        String line = String.format("%-6s %3d", "ann", 90);
        System.out.println("[" + line + "]");
    }
}
