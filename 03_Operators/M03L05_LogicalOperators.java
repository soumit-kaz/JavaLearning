public class M03L05_LogicalOperators {

    public static void main(String[] args) {
        int age = 25;
        boolean hasTicket = true;

        // && needs both sides, || needs at least one, ! flips
        System.out.println("adult with ticket: " + (age >= 18 && hasTicket));
        System.out.println("child or senior: " + (age < 12 || age > 65));
        System.out.println("no ticket: " + !hasTicket);

        // a range check needs two comparisons
        System.out.println("in 20..29: " + (age >= 20 && age <= 29));

        // ^ is true if exactly one side is true
        System.out.println("true ^ false: " + (true ^ false));
        System.out.println("true ^ true: " + (true ^ true));

        // && is done before ||
        boolean t = true;
        boolean f = false;
        System.out.println("t || t && f: " + (t || t && f));
        System.out.println("(t || t) && f: " + ((t || t) && f));

        // && stops early, so 10 / x is never calculated
        int x = 0;
        System.out.println("safe: " + (x != 0 && 10 / x > 1));

        // & always checks both sides: count still grows
        int count = 0;
        boolean skipped = f && ++count > 0;
        boolean both = f & ++count > 0;
        System.out.println(skipped + " " + both + ", count = " + count);

        // check null first before using a String
        String name = null;
        System.out.println("long name: " + (name != null && name.length() > 3));
    }
}
