public class M11L05_Encapsulation {

    static class BankAccount {
        // private: the balance can change only through the methods below
        private double balance;

        // a getter gives read access
        double getBalance() {
            return balance;
        }

        void deposit(double amount) {
            // validation keeps the object in a valid state
            if (amount <= 0) {
                throw new IllegalArgumentException("amount must be positive");
            }
            balance += amount;
        }

        void withdraw(double amount) {
            if (amount > balance) {
                throw new IllegalStateException("insufficient funds");
            }
            balance -= amount;
        }
    }

    static class Temperature {
        private double celsius;

        double getCelsius() {
            return celsius;
        }

        // a setter can reject bad values
        void setCelsius(double celsius) {
            if (celsius < -273.15) {
                throw new IllegalArgumentException("below absolute zero");
            }
            this.celsius = celsius;
        }

        // a getter can compute its value
        double getFahrenheit() {
            return celsius * 9 / 5 + 32;
        }

        // boolean getters are usually named isXxx
        boolean isFreezing() {
            return celsius <= 0;
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.withdraw(30);
        System.out.println("balance = " + account.getBalance());
        try {
            account.withdraw(500);
        } catch (IllegalStateException e) {
            System.out.println("error: " + e.getMessage());
        }

        Temperature t = new Temperature();
        t.setCelsius(25);
        System.out.println("fahrenheit = " + t.getFahrenheit() + ", freezing = " + t.isFreezing());

        // a bad value is rejected and the old value stays
        try {
            t.setCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }
        System.out.println("celsius = " + t.getCelsius());
    }
}
