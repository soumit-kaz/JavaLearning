public class M13P06_ReportTemplate {

    record Sale(String region, String item, int qty, int price) {
        int total() {
            return qty * price;
        }
    }

    static class Report {
        // the fixed algorithm: subclasses cannot change the order
        final String build(Sale[] sales) {
            String out = header();
            int sum = 0;
            int rows = 0;
            for (Sale s : sales) {
                if (!include(s)) {
                    continue;
                }
                out += (rows > 0 ? separator() : "") + row(s);
                sum += s.total();
                rows++;
            }
            return out + footer(sum);
        }

        // hooks with default behaviour
        String header() {
            return "";
        }

        boolean include(Sale s) {
            return s.qty() > 0;
        }

        String row(Sale s) {
            return s.item();
        }

        String separator() {
            return ", ";
        }

        String footer(int sum) {
            return "";
        }
    }

    static class CsvReport extends Report {
        @Override
        String header() {
            return "item,total;";
        }

        @Override
        String row(Sale s) {
            return s.item() + "," + s.total();
        }

        @Override
        String separator() {
            return ";";
        }
    }

    static class RegionReport extends CsvReport {
        final String region;

        RegionReport(String region) {
            this.region = region;
        }

        // keep the parent's rule and add one more
        @Override
        boolean include(Sale s) {
            return super.include(s) && s.region().equals(region);
        }

        @Override
        String footer(int sum) {
            return ";" + region + " total=" + sum;
        }
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        Sale[] sales = {
            new Sale("EU", "pen", 3, 2),
            new Sale("US", "book", 1, 12),
            new Sale("EU", "book", 2, 10),
            new Sale("EU", "cup", 0, 5)
        };
        check("plain Report", new Report().build(sales), "pen, book, book");
        check("CsvReport", new CsvReport().build(sales), "item,total;pen,6;book,12;book,20");
        check("RegionReport EU", new RegionReport("EU").build(sales), "item,total;pen,6;book,20;EU total=26");
        check("RegionReport ASIA", new RegionReport("ASIA").build(sales), "item,total;;ASIA total=0");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
