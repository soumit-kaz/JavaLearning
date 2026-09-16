public class M15P05_VendingStateMachine {

    static class Machine {
        final String[] names;
        final int[] prices;
        final int[] stock;
        State state = new Idle();
        int credit;
        int bank;

        Machine(String[] names, int[] prices, int[] stock) {
            this.names = names;
            this.prices = prices;
            this.stock = stock;
        }

        int indexOf(String item) {
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(item)) {
                    return i;
                }
            }
            return -1;
        }

        boolean anyStock() {
            for (int s : stock) {
                if (s > 0) {
                    return true;
                }
            }
            return false;
        }

        // the machine just passes each event to its current state
        String handle(String event) {
            String[] p = event.split(" ");
            String before = state.name();
            String message;
            switch (p[0]) {
                case "COIN" -> message = state.coin(this, Integer.parseInt(p[1]));
                case "SELECT" -> message = state.select(this, p[1]);
                case "CANCEL" -> message = state.cancel(this);
                case "SERVICE" -> message = state.service(this);
                default -> message = "unknown event";
            }
            return before + "->" + state.name() + " " + message;
        }
    }

    abstract static class State {
        abstract String name();

        // default: every event is ignored; states override only what they handle
        String coin(Machine m, int amount) {
            return "ignored";
        }

        String select(Machine m, String item) {
            return "ignored";
        }

        String cancel(Machine m) {
            return "ignored";
        }

        String service(Machine m) {
            return "ignored";
        }

        static boolean validCoin(int amount) {
            return amount == 5 || amount == 10 || amount == 25;
        }
    }

    static class Idle extends State {
        @Override
        String name() {
            return "IDLE";
        }

        @Override
        String coin(Machine m, int amount) {
            if (!validCoin(amount)) {
                return "reject " + amount;
            }
            m.credit += amount;
            m.state = new HasCredit();
            return "credit " + m.credit;
        }

        @Override
        String service(Machine m) {
            m.state = new Service();
            return "open";
        }
    }

    static class HasCredit extends State {
        @Override
        String name() {
            return "CREDIT";
        }

        @Override
        String coin(Machine m, int amount) {
            if (!validCoin(amount)) {
                return "reject " + amount;
            }
            m.credit += amount;
            return "credit " + m.credit;
        }

        @Override
        String select(Machine m, String item) {
            int i = m.indexOf(item);
            if (i == -1) {
                return "unknown " + item;
            }
            if (m.stock[i] == 0) {
                return "sold out " + item;
            }
            if (m.credit < m.prices[i]) {
                return "need " + (m.prices[i] - m.credit);
            }
            int change = m.credit - m.prices[i];
            m.stock[i]--;
            m.bank += m.prices[i];
            m.credit = 0;
            m.state = m.anyStock() ? new Idle() : new SoldOut();
            return "vend " + item + " change " + change;
        }

        @Override
        String cancel(Machine m) {
            int refund = m.credit;
            m.credit = 0;
            m.state = new Idle();
            return "refund " + refund;
        }
    }

    static class SoldOut extends State {
        @Override
        String name() {
            return "SOLDOUT";
        }

        @Override
        String coin(Machine m, int amount) {
            return "return " + amount;
        }

        @Override
        String service(Machine m) {
            m.state = new Service();
            return "open";
        }
    }

    static class Service extends State {
        @Override
        String name() {
            return "SERVICE";
        }

        // closing service: collect the money and refill everything to 5
        @Override
        String service(Machine m) {
            int collected = m.bank;
            m.bank = 0;
            for (int i = 0; i < m.stock.length; i++) {
                m.stock[i] = 5;
            }
            m.state = new Idle();
            return "collected " + collected;
        }
    }

    static String run(Machine m, String events) {
        String result = "";
        for (String e : events.split(";")) {
            result += m.handle(e) + " | ";
        }
        return result.substring(0, result.length() - 3);
    }

    static Machine machine(int colaStock, int gumStock) {
        return new Machine(new String[] {"cola", "gum"}, new int[] {35, 10}, new int[] {colaStock, gumStock});
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
        check("cola=2 gum=0", run(machine(2, 0), "COIN 25;SELECT cola;COIN 25;SELECT cola"),
                "IDLE->CREDIT credit 25 | CREDIT->CREDIT need 10 | CREDIT->CREDIT credit 50 | CREDIT->IDLE vend cola change 15");
        check("cola=1 gum=1", run(machine(1, 1), "SELECT cola;CANCEL;COIN 3;COIN 10;CANCEL"),
                "IDLE->IDLE ignored | IDLE->IDLE ignored | IDLE->IDLE reject 3 | IDLE->CREDIT credit 10 | CREDIT->IDLE refund 10");
        check("cola=0 gum=1", run(machine(0, 1), "COIN 10;SELECT cola;SELECT tea;SELECT gum;COIN 5"),
                "IDLE->CREDIT credit 10 | CREDIT->CREDIT sold out cola | CREDIT->CREDIT unknown tea"
                        + " | CREDIT->SOLDOUT vend gum change 0 | SOLDOUT->SOLDOUT return 5");
        check("cola=0 gum=1", run(machine(0, 1), "COIN 10;SELECT gum;SERVICE;COIN 5;SERVICE;DANCE"),
                "IDLE->CREDIT credit 10 | CREDIT->SOLDOUT vend gum change 0 | SOLDOUT->SERVICE open"
                        + " | SERVICE->SERVICE ignored | SERVICE->IDLE collected 10 | IDLE->IDLE unknown event");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
