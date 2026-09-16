public class M08L07_StringBuilder {

    public static void main(String[] args) {
        // a StringBuilder is text you CAN change
        StringBuilder sb = new StringBuilder("world");

        // insert puts text at an index
        sb.insert(0, "hello ");
        System.out.println(sb);

        // append adds to the end, and calls can be chained
        sb.append('!').append(' ').append(2024);
        System.out.println(sb);

        // delete removes from start (included) to end (excluded)
        sb.delete(5, 11);
        System.out.println(sb);

        // deleteCharAt, replace and setCharAt change the builder itself
        sb.deleteCharAt(sb.length() - 1);
        sb.replace(0, 5, "Howdy");
        sb.setCharAt(0, 'h');
        System.out.println(sb);

        // reverse flips the content
        System.out.println(new StringBuilder("stressed").reverse());

        // toString makes a normal String copy; setLength(0) empties the builder
        String snapshot = sb.toString();
        sb.setLength(0);
        System.out.println("snapshot: " + snapshot);
        System.out.println("builder: [" + sb + "]");

        // equals on builders compares objects, not text
        StringBuilder x = new StringBuilder("hi");
        StringBuilder y = new StringBuilder("hi");
        System.out.println("x.equals(y): " + x.equals(y));
        System.out.println("text equal: " + x.toString().equals(y.toString()));

        // += in a loop copies the whole string every time; a builder does not
        int[] numbers = {3, 1, 4, 1, 5};
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            // a comma before every number except the first
            if (i > 0) {
                csv.append(',');
            }
            csv.append(numbers[i]);
        }
        System.out.println(csv);
    }
}
