public class M04L07_SwitchExpressions {

    public static void main(String[] args) {
        int day = 6;

        // with -> only the matching case runs: no break, no fall-through
        switch (day) {
            case 1, 2, 3, 4, 5 -> System.out.println("workday");
            case 6, 7 -> System.out.println("weekend");
            default -> System.out.println("invalid day");
        }

        // several statements need braces
        char grade = 'A';
        switch (grade) {
            case 'A' -> {
                System.out.println("excellent");
                System.out.println("well done");
            }
            case 'B', 'C' -> System.out.println("good");
            default -> System.out.println("keep trying");
        }

        // a switch expression gives back a value and ends with ;
        String name = switch (day) {
            case 1 -> "Monday";
            case 6 -> "Saturday";
            default -> "other";
        };
        System.out.println(name);

        // every value must be covered, so an int switch needs default
        int month = 2;
        int days = switch (month) {
            case 2 -> 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
        System.out.println("days = " + days);

        // yield gives the value from a block
        int score = 77;
        String letter = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> {
                String seven = "C";
                if (score >= 75) {
                    seven = "C+";
                }
                yield seven;
            }
            default -> "F";
        };
        System.out.println("grade = " + letter);

        // the colon style can also be an expression, using yield
        char op = '-';
        int result = switch (op) {
            case '+':
                yield 10 + 4;
            case '-':
                yield 10 - 4;
            default:
                yield 0;
        };
        System.out.println("result = " + result);
    }
}
