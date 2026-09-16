// static import: use static members of another class without its name
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

// * imports every static member, but hides where names come from
import static java.lang.Integer.*;

public class M09L08_StaticImport {

    public static void main(String[] args) {
        // Math.sqrt and Math.PI without the Math. prefix
        System.out.println("sqrt(49) = " + sqrt(49));
        System.out.println("area = " + PI * 2.0 * 2.0);

        // MAX_VALUE and parseInt come from Integer
        System.out.println("MAX_VALUE = " + MAX_VALUE);
        System.out.println("parseInt = " + parseInt("42"));

        // the normal form still works and is often clearer
        System.out.println("Math.abs = " + Math.abs(-5));
    }
}
