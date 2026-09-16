public class M08L04_SearchingStrings {

    public static void main(String[] args) {
        String text = "Hello, World";

        // indexOf gives the first position, or -1 when not found
        System.out.println("indexOf('o'): " + text.indexOf('o'));
        System.out.println("indexOf(\"World\"): " + text.indexOf("World"));
        System.out.println("indexOf('z'): " + text.indexOf('z'));

        // searching is case-sensitive
        System.out.println("indexOf(\"world\"): " + text.indexOf("world"));

        // a second argument says where to start; lastIndexOf searches from the end
        System.out.println("indexOf('o', 5): " + text.indexOf('o', 5));
        System.out.println("lastIndexOf('l'): " + text.lastIndexOf('l'));

        // count matches by searching again just after each match
        int count = 0;
        int position = text.indexOf('l');
        while (position != -1) {
            count++;
            position = text.indexOf('l', position + 1);
        }
        System.out.println("count of l: " + count);

        // cut a string at a separator
        String email = "ann@example.com";
        int at = email.indexOf('@');
        System.out.println("user: " + email.substring(0, at));
        System.out.println("domain: " + email.substring(at + 1));

        // contains, startsWith and endsWith answer yes/no (case-sensitive)
        String file = "report_2024.pdf";
        System.out.println("contains 2024: " + file.contains("2024"));
        System.out.println("startsWith report: " + file.startsWith("report"));
        System.out.println("endsWith .PDF: " + file.endsWith(".PDF"));

        // lower-case first to search without caring about case
        System.out.println("lower endsWith .pdf: " + "SCAN.PDF".toLowerCase().endsWith(".pdf"));
    }
}
