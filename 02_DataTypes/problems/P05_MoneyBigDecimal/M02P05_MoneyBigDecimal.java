import java.math.BigDecimal;
import java.math.RoundingMode;

public class M02P05_MoneyBigDecimal {

    public static void main(String[] args) {
        // double cannot store 0.1 exactly
        System.out.println("double: 0.1 + 0.2 = " + (0.1 + 0.2));

        // create BigDecimal from Strings, not from doubles
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        System.out.println("BigDecimal: 0.1 + 0.2 = " + a.add(b));

        BigDecimal paid = new BigDecimal("2.00");
        BigDecimal price = new BigDecimal("1.10");
        System.out.println("double change = " + (2.00 - 1.10));
        System.out.println("BigDecimal change = " + paid.subtract(price));

        BigDecimal unitPrice = new BigDecimal("19.99");
        BigDecimal quantity = new BigDecimal("3");
        BigDecimal subtotal = unitPrice.multiply(quantity);
        System.out.println("subtotal = " + subtotal);

        // round the tax to whole cents
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.0825")).setScale(2, RoundingMode.HALF_UP);
        System.out.println("tax = " + tax);
        System.out.println("total = " + subtotal.add(tax));

        // new BigDecimal(0.1) shows the real value stored in the double
        System.out.println("new BigDecimal(0.1) = " + new BigDecimal(0.1));
    }
}
