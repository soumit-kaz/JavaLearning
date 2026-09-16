public class M01P04_MiniReceipt {

    public static void main(String[] args) {
        // header: item name on the left, numbers on the right
        System.out.printf("%-10s%5s%10s%n", "Item", "Qty", "Total");
        System.out.println("-------------------------");

        // %-10s pads the name, %5d and %10.2f line up the numbers on the right
        System.out.printf("%-10s%5d%10.2f%n", "Coffee", 2, 2 * 3.50);
        System.out.printf("%-10s%5d%10.2f%n", "Bagel", 1, 1 * 2.25);
        System.out.printf("%-10s%5d%10.2f%n", "Juice", 3, 3 * 1.99);

        System.out.println("-------------------------");

        // the grand total is calculated right inside printf
        System.out.printf("%-15s%10.2f%n", "TOTAL", 2 * 3.50 + 1 * 2.25 + 3 * 1.99);
    }
}
