// a top-level class is public or has no modifier
public class M11L06_AccessModifiers {

    static class Account {
        // public: visible everywhere
        public String owner = "Ann";
        // protected: same package and subclasses
        protected String type = "savings";
        // no modifier (package-private): same package only
        String branch = "Dhaka";
        // private: this class only
        private String pin = "1234";

        // a private helper is for use inside the class
        private boolean looksValid(String guess) {
            return guess != null && guess.length() == 4;
        }

        public boolean checkPin(String guess) {
            return looksValid(guess) && pin.equals(guess);
        }
    }

    public static void main(String[] args) {
        Account a = new Account();
        System.out.println("owner = " + a.owner);
        System.out.println("type = " + a.type);
        System.out.println("branch = " + a.branch);

        // good style: reach private data only through methods
        System.out.println("pin ok = " + a.checkPin("1234"));

        // nested classes share one file, so private is visible here; in separate files it is not
        System.out.println("pin = " + a.pin);
    }
}
