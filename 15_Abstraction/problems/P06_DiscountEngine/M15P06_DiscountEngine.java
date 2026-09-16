public class M15P06_DiscountEngine {

    record Item(String name, String category, long price, int quantity) {
        long total() {
            return price * quantity;
        }
    }

    record Cart(Item[] items, String coupon, boolean member) {
        long subtotal() {
            long sum = 0;
            for (Item i : items) {
                sum += i.total();
            }
            return sum;
        }
    }

    abstract static class Rule {
        private final String name;

        Rule(String name) {
            this.name = name;
        }

        String name() {
            return name;
        }

        // shared safety: a discount is never more than what is left to pay
        final long discountFor(Cart cart, long remaining) {
            if (!appliesTo(cart)) {
                return 0;
            }
            return Math.min(amount(cart, remaining), remaining);
        }

        abstract boolean appliesTo(Cart cart);

        abstract long amount(Cart cart, long remaining);

        // hook: an exclusive rule replaces all other rules
        boolean exclusive() {
            return false;
        }
    }

    static class CategoryPercent extends Rule {
        private final String category;
        private final int percent;

        CategoryPercent(String name, String category, int percent) {
            super(name);
            this.category = category;
            this.percent = percent;
        }

        @Override
        boolean appliesTo(Cart cart) {
            return true;
        }

        @Override
        long amount(Cart cart, long remaining) {
            long sum = 0;
            for (Item i : cart.items()) {
                if (i.category().equals(category)) {
                    sum += i.total();
                }
            }
            return sum * percent / 100;
        }
    }

    static class SpendThreshold extends Rule {
        private final long minimum;
        private final long off;

        SpendThreshold(String name, long minimum, long off) {
            super(name);
            this.minimum = minimum;
            this.off = off;
        }

        @Override
        boolean appliesTo(Cart cart) {
            return cart.subtotal() >= minimum;
        }

        @Override
        long amount(Cart cart, long remaining) {
            return off;
        }
    }

    static class MemberPercent extends Rule {
        private final int percent;

        MemberPercent(String name, int percent) {
            super(name);
            this.percent = percent;
        }

        @Override
        boolean appliesTo(Cart cart) {
            return cart.member();
        }

        // works on what is left, so the order of rules matters
        @Override
        long amount(Cart cart, long remaining) {
            return remaining * percent / 100;
        }
    }

    static class Coupon extends Rule {
        private final int percent;

        Coupon(String code, int percent) {
            super(code);
            this.percent = percent;
        }

        @Override
        boolean appliesTo(Cart cart) {
            return name().equals(cart.coupon());
        }

        @Override
        long amount(Cart cart, long remaining) {
            return cart.subtotal() * percent / 100;
        }

        @Override
        boolean exclusive() {
            return true;
        }
    }

    static String checkout(Rule[] rules, Cart cart) {
        long subtotal = cart.subtotal();

        // an exclusive rule that applies wins alone
        for (Rule r : rules) {
            long d = r.exclusive() ? r.discountFor(cart, subtotal) : 0;
            if (d > 0) {
                return "subtotal=" + subtotal + " " + r.name() + "=-" + d + " total=" + (subtotal - d);
            }
        }

        // otherwise apply the normal rules one after another
        long remaining = subtotal;
        String applied = "";
        for (Rule r : rules) {
            long d = r.exclusive() ? 0 : r.discountFor(cart, remaining);
            if (d > 0) {
                applied += r.name() + "=-" + d + " ";
                remaining -= d;
            }
        }
        return "subtotal=" + subtotal + " " + applied + "total=" + remaining;
    }

    static final Rule[] RULES = {
        new Coupon("VIP30", 30),
        new CategoryPercent("BOOKS10", "books", 10),
        new SpendThreshold("SPEND100", 10000, 1500),
        new MemberPercent("MEMBER5", 5),
    };

    static final Item NOVEL = new Item("novel", "books", 2000, 1);
    static final Item TV = new Item("tv", "electronics", 12000, 1);
    static final Item PEN = new Item("pen", "office", 100, 3);

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("novel", checkout(RULES, new Cart(new Item[] {NOVEL}, "", false)),
                "subtotal=2000 BOOKS10=-200 total=1800");
        check("novel + pens, member", checkout(RULES, new Cart(new Item[] {NOVEL, PEN}, "", true)),
                "subtotal=2300 BOOKS10=-200 MEMBER5=-105 total=1995");
        check("tv + novel, member", checkout(RULES, new Cart(new Item[] {TV, NOVEL}, "", true)),
                "subtotal=14000 BOOKS10=-200 SPEND100=-1500 MEMBER5=-615 total=11685");
        check("tv + novel, coupon VIP30", checkout(RULES, new Cart(new Item[] {TV, NOVEL}, "VIP30", true)),
                "subtotal=14000 VIP30=-4200 total=9800");
        check("tv, wrong coupon", checkout(RULES, new Cart(new Item[] {TV}, "NOPE", false)),
                "subtotal=12000 SPEND100=-1500 total=10500");
        check("pens only", checkout(RULES, new Cart(new Item[] {PEN}, "", false)),
                "subtotal=300 total=300");

        Rule[] huge = {new SpendThreshold("BIG", 0, 999999)};
        check("pens, huge fixed discount", checkout(huge, new Cart(new Item[] {PEN}, "", false)),
                "subtotal=300 BIG=-300 total=0");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
