import java.util.ArrayList;
import java.util.List;

public class M20P05_PriceTable {

    // build a price table: the name on the left in 10 places, the price on the
    // right in 8 places with two decimals, then a TOTAL line at the end
    static List<String> table(String[] names, double[] prices) {
        List<String> lines = new ArrayList<>();
        double total = 0;
        for (int i = 0; i < names.length; i++) {
            lines.add(String.format("%-10s %8.2f", names[i], prices[i]));
            total += prices[i];
        }
        lines.add(String.format("%-10s %8.2f", "TOTAL", total));
        return lines;
    }

    static void test(String[] names, double[] prices, List<String> expected) {
        List<String> got = table(names, prices);

        for (String line : got) {
            System.out.println("| " + line);
        }
        if (!got.equals(expected)) {
            System.out.println("FAIL, expected:");
            for (String line : expected) {
                System.out.println("| " + line);
            }
            System.exit(1);
        }
        System.out.println("PASS");
    }

    public static void main(String[] args) {
        test(new String[] {"tea", "biscuits"}, new double[] {3.5, 12.25},
                List.of("tea            3.50", "biscuits      12.25", "TOTAL         15.75"));
        test(new String[] {"milk"}, new double[] {1.0},
                List.of("milk           1.00", "TOTAL          1.00"));
        test(new String[] {}, new double[] {},
                List.of("TOTAL          0.00"));
    }
}
