public class M11L18_InnerClasses {

    static class Bank {
        String name;
        int accountsOpened = 0;

        Bank(String name) {
            this.name = name;
        }

        // a static nested class does not need a Bank object
        static class Rate {
            double percent;

            Rate(double percent) {
                this.percent = percent;
            }
        }

        // an inner class (not static) belongs to one Bank object
        class Account {
            String name = "account";
            int number;

            Account() {
                // it can use the outer object's fields
                accountsOpened++;
                number = accountsOpened;
            }

            String show() {
                // Bank.this means the outer object
                return name + " " + number + " at " + Bank.this.name;
            }
        }
    }

    public static void main(String[] args) {
        Bank.Rate rate = new Bank.Rate(2.5);
        System.out.println("rate = " + rate.percent);

        // outside the outer class, use outer.new Inner()
        Bank city = new Bank("CityBank");
        Bank.Account a1 = city.new Account();
        Bank.Account a2 = city.new Account();
        System.out.println(a1.show());
        System.out.println(a2.show());

        double taxRate = 0.1;

        // a local class exists only inside this method
        class Line {
            double price;

            Line(double price) {
                this.price = price;
            }

            // it can read effectively final local variables
            double withTax() {
                return price + price * taxRate;
            }
        }
        System.out.println("with tax = " + new Line(20).withTax());
    }
}
