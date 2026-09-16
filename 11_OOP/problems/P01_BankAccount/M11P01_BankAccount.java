public class M11P01_BankAccount {

    static class BankAccount {
        private final String owner;
        private double balance;

        BankAccount(String owner, double opening) {
            if (opening < 0) {
                throw new IllegalArgumentException("negative opening balance");
            }
            this.owner = owner;
            this.balance = opening;
        }

        double getBalance() {
            return balance;
        }

        void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("invalid amount");
            }
            balance += amount;
        }

        void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("invalid amount");
            }
            // The balance may never go below zero
            if (amount > balance) {
                throw new IllegalStateException("insufficient funds");
            }
            balance -= amount;
        }

        void transferTo(BankAccount other, double amount) {
            // Withdraw first, so a failed withdraw changes nothing
            withdraw(amount);
            other.deposit(amount);
        }
    }

    // Runs one operation and returns the balance or the error message
    static String run(BankAccount acc, String op, double amount) {
        try {
            if (op.equals("deposit")) {
                acc.deposit(amount);
            } else {
                acc.withdraw(amount);
            }
            return String.valueOf(acc.getBalance());
        } catch (RuntimeException e) {
            return "error: " + e.getMessage();
        }
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Ann", 100);
        check("deposit 50", run(acc, "deposit", 50), "150.0");
        check("withdraw 30", run(acc, "withdraw", 30), "120.0");
        check("withdraw 500", run(acc, "withdraw", 500), "error: insufficient funds");
        check("deposit -5", run(acc, "deposit", -5), "error: invalid amount");
        check("withdraw 0", run(acc, "withdraw", 0), "error: invalid amount");
        check("balance unchanged", acc.getBalance(), "120.0");

        BankAccount bob = new BankAccount("Bob", 0);
        acc.transferTo(bob, 20);
        check("transfer 20 Ann->Bob", acc.getBalance() + " " + bob.getBalance(), "100.0 20.0");
        try {
            bob.transferTo(acc, 999);
        } catch (IllegalStateException e) {
            check("transfer 999 Bob->Ann", acc.getBalance() + " " + bob.getBalance(), "100.0 20.0");
        }
        try {
            new BankAccount("Eve", -1);
            check("open with -1", "created", "error");
        } catch (IllegalArgumentException e) {
            check("open with -1", "error", "error");
        }

        if (failed) {
            System.exit(1);
        }
    }
}
