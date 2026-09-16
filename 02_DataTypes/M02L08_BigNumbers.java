import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class M02L08_BigNumbers {

    public static void main(String[] args) {
        // BigInteger has no size limit and uses methods instead of + - * /
        BigInteger a = new BigInteger("123456789012345678901234567890");
        BigInteger b = BigInteger.valueOf(1000);
        System.out.println(a.add(b));
        System.out.println(a.multiply(b));
        System.out.println(a.remainder(b));

        // long overflows, BigInteger does not
        System.out.println(Long.MAX_VALUE + 1);
        System.out.println(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE));
        System.out.println(BigInteger.TWO.pow(100));

        // big numbers never change: keep the result
        BigInteger n = BigInteger.TEN;
        n.add(BigInteger.ONE);
        System.out.println(n);
        n = n.add(BigInteger.ONE);
        System.out.println(n);

        // BigDecimal gives exact decimal math
        BigDecimal x = new BigDecimal("0.1");
        BigDecimal y = new BigDecimal("0.2");
        System.out.println(x.add(y));

        // create it from a String, not from a double
        System.out.println(new BigDecimal(0.1));

        // money example
        BigDecimal price = new BigDecimal("19.99");
        System.out.println(price.multiply(BigDecimal.valueOf(3)));

        // equals also compares the scale; compareTo only the value
        BigDecimal two = new BigDecimal("2.0");
        BigDecimal twoAgain = new BigDecimal("2.00");
        System.out.println(two.equals(twoAgain));
        System.out.println(two.compareTo(twoAgain));

        // a division that never ends needs a scale and a rounding mode
        BigDecimal third = BigDecimal.ONE.divide(new BigDecimal("3"), 4, RoundingMode.HALF_UP);
        System.out.println(third);

        // rounding 2.5 to a whole number in different ways
        BigDecimal half = new BigDecimal("2.5");
        System.out.println(half.setScale(0, RoundingMode.HALF_UP));
        System.out.println(half.setScale(0, RoundingMode.HALF_EVEN));
        System.out.println(half.setScale(0, RoundingMode.DOWN));
    }
}
