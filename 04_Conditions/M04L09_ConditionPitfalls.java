public class M04L09_ConditionPitfalls {

    // the stray semicolon below is on purpose, so its compiler warning is switched off
    @SuppressWarnings("empty")
    public static void main(String[] args) {
        // pitfall: without braces only the FIRST statement belongs to the if
        int points = 5;
        if (points > 10)
            System.out.println("bonus");
        System.out.println("always printed");

        // pitfall: a stray semicolon ends the if, so the block always runs
        if (points > 100);
        {
            System.out.println("runs anyway");
        }

        // pitfall: a variable declared inside a block is gone after it
        String level = "low";
        if (points > 3) {
            String note = "raised";
            level = "high";
            System.out.println(note);
        }
        System.out.println("level = " + level);

        // pitfall: = assigns, so this condition is always true; write if (isAdmin)
        boolean isAdmin = false;
        if (isAdmin = true) {
            System.out.println("isAdmin = " + isAdmin);
        }

        // pitfall: else belongs to the NEAREST if, whatever the indentation
        int childAge = 10;
        boolean member = true;
        if (childAge >= 18)
            if (member)
                System.out.println("member discount");
        else
            System.out.println("adult price");

        // braces make the meaning clear
        if (childAge >= 18) {
            if (member) {
                System.out.println("member discount");
            }
        } else {
            System.out.println("child price");
        }

        // pitfall: == on Strings compares objects; use equals
        String typed = "ye";
        typed += "s";
        if (typed == "yes") {
            System.out.println("== matched");
        }
        if (typed.equals("yes")) {
            System.out.println("equals matched");
        }

        // pitfall: NaN is never equal to itself; use Double.isNaN
        double bad = 0.0 / 0.0;
        if (Double.isNaN(bad)) {
            System.out.println("NaN");
        }
    }
}
