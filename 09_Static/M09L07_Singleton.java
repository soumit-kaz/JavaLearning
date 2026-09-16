public class M09L07_Singleton {

    // singleton: a class that allows only ONE object
    static class Settings {
        // eager: the single object is created when the class is set up
        private static final Settings INSTANCE = new Settings();

        String theme = "light";

        // private constructor: code outside cannot write new Settings()
        private Settings() {
            System.out.println("Settings created");
        }

        static Settings getInstance() {
            return INSTANCE;
        }
    }

    static class Logger {
        // lazy: stays null until someone asks for the object
        private static Logger instance;

        private int lineNumber = 0;

        private Logger() {
            System.out.println("Logger created");
        }

        // create the object only on the first call (fine for one-thread programs)
        static Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        void log(String message) {
            lineNumber++;
            System.out.println(lineNumber + ": " + message);
        }
    }

    public static void main(String[] args) {
        Settings first = Settings.getInstance();
        Settings second = Settings.getInstance();
        System.out.println("same settings: " + (first == second));

        // a change through one variable is seen through the other
        first.theme = "dark";
        System.out.println("second.theme = " + second.theme);

        // the Logger is created only at the first call
        Logger.getInstance().log("start");
        Logger.getInstance().log("login");
        System.out.println("same logger: " + (Logger.getInstance() == Logger.getInstance()));
    }
}
