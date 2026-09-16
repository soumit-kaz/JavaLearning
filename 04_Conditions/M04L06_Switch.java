public class M04L06_Switch {

    // falling through on purpose makes the compiler warn, so the warning is switched off
    @SuppressWarnings("fallthrough")
    public static void main(String[] args) {
        int day = 3;

        // switch jumps to the matching case; break leaves the switch
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            // default runs when no case matches
            default:
                System.out.println("other day");
                break;
        }

        // empty cases fall through, so several values share one block
        int month = 4;
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("spring");
                break;
            default:
                System.out.println("summer or autumn");
        }

        // a missing break runs the next case too
        int stars = 2;
        switch (stars) {
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
            case 3:
                System.out.println("three");
                break;
            case 4:
                System.out.println("four");
        }

        // a String case compares with equals, so convert the case first
        String answer = "YES";
        switch (answer.toLowerCase()) {
            case "yes":
                System.out.println("confirmed");
                break;
            default:
                System.out.println("not confirmed");
        }
    }
}
