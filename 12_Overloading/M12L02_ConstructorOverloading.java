public class M12L02_ConstructorOverloading {

    static class Money {
        long cents;

        // constructors are overloaded just like methods
        Money(long cents) {
            this.cents = cents;
            System.out.println("Money(long)");
        }

        Money(int dollars, int cents) {
            // this(...) calls another overload
            this(dollars * 100L + cents);
            System.out.println("Money(int, int)");
        }

        Money(String text) {
            this(Integer.parseInt(text), 0);
            System.out.println("Money(String)");
        }
    }

    public static void main(String[] args) {
        // there is no Money(int), so 250 widens to long
        System.out.println("cents = " + new Money(250).cents);

        System.out.println("cents = " + new Money(3, 50).cents);

        // the last constructor in the chain finishes first
        System.out.println("cents = " + new Money("19").cents);
    }
}
