public class M09P03_SharedInterestRate {

    static class Account {
        // one rate for every account
        static double rate = 0.10;

        // each account has its own balance
        double balance;

        Account(double balance) {
            this.balance = balance;
        }

        void addInterest() {
            balance = balance + balance * rate;
        }

        static void changeRate(double newRate) {
            rate = newRate;
        }
    }

    static void check(String input, double actual, String expected) {
        // round to two decimal places
        String text = String.valueOf(Math.round(actual * 100) / 100.0);
        String status = text.equals(expected) ? "PASS" : "FAIL";
        System.out.println(input + " -> " + text + "  " + status);
    }

    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(500);

        a.addInterest();
        check("a after 10%", a.balance, "1100.0");
        check("b untouched", b.balance, "500.0");

        // the new rate applies to every account
        Account.changeRate(0.05);
        a.addInterest();
        b.addInterest();
        check("a after 5%", a.balance, "1155.0");
        check("b after 5%", b.balance, "525.0");
        check("rate", Account.rate, "0.05");
    }
}
