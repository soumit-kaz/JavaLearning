import java.util.Locale;

public class M08L09_Formatting {

    public static void main(String[] args) {
        // fix the locale so numbers look the same on every computer
        Locale.setDefault(Locale.US);
        double price = 1234.5678;

        // %d whole numbers: width pads left, - pads right, 0 pads with zeros
        System.out.println(String.format("[%d] [%5d] [%-5d] [%05d]", 42, 42, 42, 42));

        // + shows the sign, and , adds thousands separators
        System.out.println(String.format("[%+d] [%,d]", 42, 1234567));

        // %f decimals: .2 means two digits after the point, and it rounds
        System.out.println(String.format("[%.2f] [%10.1f] [%,.2f]", price, price, price));

        // %x is hex, %e is scientific, %% is a percent sign
        System.out.println(String.format("[%x] [%.2e] [%d%%]", 255, price, 75));

        // other locales use other separators
        System.out.println(String.format(Locale.GERMANY, "%,.2f", price));

        // %s inserts any value as text; a width aligns it
        System.out.println(String.format("[%8s] [%-8s] [%.2s]", "Ann", "Ann", "Ann"));

        // %c is a char, %b a boolean, %n a line break
        System.out.print(String.format("%c %b%n", 'x', true));

        // 2$ picks the second argument
        System.out.println(String.format("%2$s %1$s", "world", "hello"));

        // printf prints directly; formatted is called on the pattern
        System.out.printf("%-6s|%4d|%n", "pens", 3);
        System.out.printf("%-6s|%4d|%n", "books", 12);
        System.out.println("%s: %d".formatted("Ann", 5));

        // a wrong-type argument fails when the program runs
        try {
            System.out.println(String.format("%d", "Ann"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getSimpleName());
        }
    }
}
