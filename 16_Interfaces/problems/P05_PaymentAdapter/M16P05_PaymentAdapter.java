public class M16P05_PaymentAdapter {

    record Receipt(String provider, boolean success, String text) {
        @Override
        public String toString() {
            return provider + (success ? " OK " : " FAILED ") + text;
        }
    }

    // the interface our shop expects
    interface PaymentProcessor {
        Receipt charge(String account, long cents);

        String name();

        // by default a processor accepts every account
        default boolean supports(String account) {
            return true;
        }
    }

    // old bank code we cannot change: works in taka and returns error codes
    static class LegacyBankApi {
        private int counter = 100;

        int transferTaka(String iban, double taka) {
            if (!iban.startsWith("BD")) {
                return -1;
            }
            if (taka > 50_000) {
                return -2;
            }
            counter++;
            return counter;
        }
    }

    static class WalletException extends Exception {
        // exceptions are Serializable; this id avoids a compiler warning
        private static final long serialVersionUID = 1L;

        WalletException(String message) {
            super(message);
        }
    }

    // old wallet code we cannot change: throws a checked exception
    static class WalletSdk {
        private long balance = 10_000;

        String pay(String phone, long paisa) throws WalletException {
            if (!phone.startsWith("017")) {
                throw new WalletException("unknown operator " + phone.substring(0, 3));
            }
            if (balance < paisa) {
                throw new WalletException("insufficient balance");
            }
            balance -= paisa;
            return "W-" + paisa;
        }
    }

    // adapter: converts cents to taka and error codes to receipts
    static class BankAdapter implements PaymentProcessor {
        private final LegacyBankApi bank = new LegacyBankApi();

        @Override
        public Receipt charge(String account, long cents) {
            int code = bank.transferTaka(account, cents / 100.0);
            return switch (code) {
                case -1 -> new Receipt(name(), false, "invalid IBAN");
                case -2 -> new Receipt(name(), false, "limit exceeded");
                default -> new Receipt(name(), true, "BANK-" + code);
            };
        }

        @Override
        public String name() {
            return "bank";
        }

        @Override
        public boolean supports(String account) {
            return account.startsWith("BD") || account.startsWith("US");
        }
    }

    // adapter: turns the checked exception into a failed receipt
    static class WalletAdapter implements PaymentProcessor {
        private final WalletSdk wallet = new WalletSdk();

        @Override
        public Receipt charge(String account, long cents) {
            try {
                return new Receipt(name(), true, wallet.pay(account, cents));
            } catch (WalletException e) {
                return new Receipt(name(), false, e.getMessage());
            }
        }

        @Override
        public String name() {
            return "wallet";
        }

        @Override
        public boolean supports(String account) {
            return account.length() == 11;
        }
    }

    // the router is itself a PaymentProcessor that tries the others in order
    static class Router implements PaymentProcessor {
        private final PaymentProcessor[] processors;

        Router(PaymentProcessor... processors) {
            this.processors = processors;
        }

        @Override
        public Receipt charge(String account, long cents) {
            if (cents <= 0) {
                return new Receipt(name(), false, "amount must be positive");
            }
            String reasons = "";
            for (PaymentProcessor p : processors) {
                if (!p.supports(account)) {
                    continue;
                }
                Receipt receipt = p.charge(account, cents);
                if (receipt.success()) {
                    return receipt;
                }
                // remember why this processor failed
                reasons += (reasons.isEmpty() ? "" : "; ") + p.name() + ": " + receipt.text();
            }
            return new Receipt(name(), false, reasons.isEmpty() ? "no processor for " + account : reasons);
        }

        @Override
        public String name() {
            return "router";
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
        // a card processor written on the spot, without touching Router
        PaymentProcessor card = new PaymentProcessor() {
            @Override
            public Receipt charge(String account, long cents) {
                return new Receipt(name(), true, "CARD-" + cents);
            }

            @Override
            public String name() {
                return "card";
            }

            @Override
            public boolean supports(String account) {
                return account.startsWith("US");
            }
        };
        PaymentProcessor router = new Router(new BankAdapter(), new WalletAdapter(), card);

        check("BD001 150000", router.charge("BD001", 150000), "bank OK BANK-101");
        check("BD002 9000000", router.charge("BD002", 9000000), "router FAILED bank: limit exceeded");
        check("01712345678 9999", router.charge("01712345678", 9999), "wallet OK W-9999");
        check("01712345678 5", router.charge("01712345678", 5), "router FAILED wallet: insufficient balance");
        check("01912345678 1", router.charge("01912345678", 1), "router FAILED wallet: unknown operator 019");
        check("US777 6000", router.charge("US777", 6000), "card OK CARD-6000");
        check("XX1 10", router.charge("XX1", 10), "router FAILED no processor for XX1");
        check("BD003 0", router.charge("BD003", 0), "router FAILED amount must be positive");
        check("router.supports(anything)", router.supports("anything"), true);

        if (failures > 0) {
            System.exit(1);
        }
    }
}
