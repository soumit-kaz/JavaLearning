public class M11L04_ThisKeyword {

    static class Pizza {
        String size;
        String topping;

        // this means "the current object": this.size is the field, size is the parameter
        Pizza(String size, String topping) {
            this.size = size;
            this.topping = topping;
        }

        // this(...) calls another constructor of the same class
        Pizza(String size) {
            this(size, "cheese");
        }

        void resizeWrong(String size) {
            // bug: without this, both names mean the parameter
            size = size + "!";
        }

        // returning this lets calls be chained
        Pizza withTopping(String topping) {
            this.topping = topping;
            return this;
        }

        // this is a reference like any other
        boolean isSame(Pizza other) {
            return this == other;
        }
    }

    public static void main(String[] args) {
        Pizza p = new Pizza("small", "mushroom");
        System.out.println("p = " + p.size + " " + p.topping);

        p.resizeWrong("huge");
        System.out.println("size = " + p.size);

        Pizza plain = new Pizza("medium");
        System.out.println("plain = " + plain.size + " " + plain.topping);

        System.out.println("chained = " + new Pizza("large").withTopping("corn").topping);
        System.out.println("same = " + p.isSame(p) + ", " + p.isSame(plain));
    }
}
