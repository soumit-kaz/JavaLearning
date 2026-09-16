public class M02L05_BooleanType {

    public static void main(String[] args) {
        // a boolean holds only true or false
        boolean isRaining = false;
        boolean hasTicket = true;
        System.out.println(isRaining);
        System.out.println(hasTicket);

        // it can change like any variable
        isRaining = true;
        System.out.println("raining: " + isRaining);

        // copying takes the value
        boolean canTravel = hasTicket;
        hasTicket = false;
        System.out.println("can travel: " + canTravel);

        // true without quotes is a boolean, "true" is text
        boolean flag = true;
        String text = "true";
        System.out.println(flag + " " + text);

        // var picks boolean from the value
        var isOpen = false;
        System.out.println("open: " + isOpen);

        // a boolean is not a number: boolean b = 1; does not compile
    }
}
