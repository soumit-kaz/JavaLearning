import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class M17P08_SalesReport {

    record Sale(String rep, String region, String product, int amount) {
    }

    static final List<Sale> SALES = List.of(
            new Sale("alice", "north", "laptop", 2400),
            new Sale("bob", "south", "mouse", 250),
            new Sale("alice", "north", "monitor", 300),
            new Sale("carol", "east", "laptop", 1180),
            new Sale("bob", "south", "laptop", 1250),
            new Sale("dave", "west", "keyboard", 200));

    // total amount per region, sorted by region
    static Map<String, Integer> totalByRegion(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::region, TreeMap::new, Collectors.summingInt(Sale::amount)));
    }

    // number of sales per rep, sorted by rep
    static Map<String, Long> countByRep(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::rep, TreeMap::new, Collectors.counting()));
    }

    // the rep with the highest total (empty when there are no sales)
    static Optional<String> topRep(List<Sale> sales) {
        Map<String, Integer> totals = sales.stream()
                .collect(Collectors.groupingBy(Sale::rep, Collectors.summingInt(Sale::amount)));
        return totals.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    // distinct product names, sorted, as one string
    static String productList(List<Sale> sales) {
        return sales.stream().map(Sale::product).distinct().sorted().collect(Collectors.joining(", "));
    }

    // the biggest single sale amount, or 0
    static int biggestSale(List<Sale> sales) {
        return sales.stream().max(Comparator.comparingInt(Sale::amount)).map(Sale::amount).orElse(0);
    }

    // print one PASS line, or stop with a FAIL line
    static void check(String query, Object got, Object expected) {
        String label = query + " -> " + got;
        if (!Objects.equals(got, expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        check("totalByRegion", totalByRegion(SALES),
                Map.of("east", 1180, "north", 2700, "south", 1500, "west", 200));
        check("countByRep", countByRep(SALES),
                Map.of("alice", 2L, "bob", 2L, "carol", 1L, "dave", 1L));
        check("topRep", topRep(SALES), Optional.of("alice"));
        check("topRep(empty)", topRep(List.of()), Optional.empty());
        check("productList", productList(SALES), "keyboard, laptop, monitor, mouse");
        check("biggestSale", biggestSale(SALES), 2400);
        check("biggestSale(empty)", biggestSale(List.of()), 0);
    }
}
