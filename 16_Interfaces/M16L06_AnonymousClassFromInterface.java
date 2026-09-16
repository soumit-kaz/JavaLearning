public class M16L06_AnonymousClassFromInterface {

    interface Greeter {
        String greet(String name);
    }

    static class Button {
        // an interface inside a class is automatically static
        interface ClickListener {
            String onClick(String buttonName);
        }

        private final String name;
        private ClickListener listener;

        Button(String name) {
            this.name = name;
        }

        void setListener(ClickListener listener) {
            this.listener = listener;
        }

        // the button "calls back" through the interface
        String click() {
            return listener == null ? "no listener" : listener.onClick(name);
        }
    }

    public static void main(String[] args) {
        // an anonymous class implements the interface right where it is needed
        // new Greeter() { ... } creates an unnamed class, not a Greeter itself
        Greeter english = new Greeter() {
            @Override
            public String greet(String name) {
                return "Hello, " + name;
            }
        };
        System.out.println("english: " + english.greet("Rina"));

        // an anonymous class can keep its own fields
        Greeter counting = new Greeter() {
            private int count = 0;

            @Override
            public String greet(String name) {
                count++;
                return "Hi #" + count + ", " + name;
            }
        };
        counting.greet("Rina");
        System.out.println("counting: " + counting.greet("Karim"));

        // it may use local variables that never change
        String suffix = "!";
        Greeter excited = new Greeter() {
            @Override
            public String greet(String name) {
                return "Hey " + name + suffix;
            }
        };
        System.out.println("excited: " + excited.greet("Nila"));

        // use the outer class name to reach a nested interface
        Button save = new Button("Save");
        System.out.println("before: " + save.click());
        save.setListener(new Button.ClickListener() {
            @Override
            public String onClick(String buttonName) {
                return buttonName + " clicked";
            }
        });
        System.out.println("after: " + save.click());
    }
}
