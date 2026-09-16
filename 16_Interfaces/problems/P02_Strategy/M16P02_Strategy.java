public class M16P02_Strategy {

    record Item(String name, int price, int quantity) {
    }

    static int subtotal(Item[] cart) {
        int sum = 0;
        for (Item item : cart) {
            sum += item.price() * item.quantity();
        }
        return sum;
    }

    // a strategy returns how much to take off
    @FunctionalInterface
    interface PricingStrategy {
        int discount(Item[] cart);

        // add two discounts together
        default PricingStrategy plus(PricingStrategy other) {
            return cart -> discount(cart) + other.discount(cart);
        }

        static PricingStrategy none() {
            return cart -> 0;
        }

        static PricingStrategy percentOff(int percent) {
            return cart -> subtotal(cart) * percent / 100;
        }

        static PricingStrategy flatOver(int minimum, int off) {
            return cart -> subtotal(cart) >= minimum ? off : 0;
        }

        // every (x + 1)th unit of the product is free
        static PricingStrategy buyXGetOneFree(String product, int x) {
            return cart -> {
                int off = 0;
                for (Item item : cart) {
                    if (item.name().equals(product)) {
                        off += item.quantity() / (x + 1) * item.price();
                    }
                }
                return off;
            };
        }

        // pick the bigger discount
        static PricingStrategy best(PricingStrategy first, PricingStrategy second) {
            return cart -> Math.max(first.discount(cart), second.discount(cart));
        }
    }

    // checkout depends only on the interface
    static int payable(Item[] cart, PricingStrategy strategy) {
        int subtotal = subtotal(cart);
        // keep the discount between 0 and the subtotal
        int discount = Math.min(subtotal, Math.max(0, strategy.discount(cart)));
        return subtotal - discount;
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
        Item[] cart = {new Item("apple", 50, 7), new Item("bread", 300, 1), new Item("milk", 150, 2)};
        PricingStrategy tenOff = PricingStrategy.percentOff(10);
        PricingStrategy apples = PricingStrategy.buyXGetOneFree("apple", 2);
        PricingStrategy flat = PricingStrategy.flatOver(800, 200);

        check("subtotal", subtotal(cart), 950);
        check("none", payable(cart, PricingStrategy.none()), 950);
        check("10% off", payable(cart, tenOff), 855);
        check("apple buy 2 get 1", payable(cart, apples), 850);
        check("200 off over 800", payable(cart, flat), 750);
        check("10% plus apple deal", payable(cart, tenOff.plus(apples)), 755);
        check("best of 10% and 200 off", payable(cart, PricingStrategy.best(tenOff, flat)), 750);
        check("lambda 5000 off is capped", payable(cart, c -> 5000), 0);
        check("negative discount is ignored", payable(cart, c -> -50), 950);

        if (failures > 0) {
            System.exit(1);
        }
    }
}
