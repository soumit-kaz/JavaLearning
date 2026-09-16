public class M01P03_Diamond {

    public static void main(String[] args) {
        // top half: fewer spaces and more stars on each row
        System.out.println("   *");
        System.out.println("  ***");
        System.out.println(" *****");
        System.out.println("*******");

        // bottom half: the same rows in reverse, without repeating the middle row
        System.out.println(" *****");
        System.out.println("  ***");
        System.out.println("   *");
    }
}
