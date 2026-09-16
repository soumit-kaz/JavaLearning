public class M03L02_AssignmentOperators {

    public static void main(String[] args) {
        // = stores the value on the right in the variable on the left
        int score = 10;

        // x += 5 means x = x + 5; the same for -= *= /= %=
        score += 5;
        System.out.println("+= 5: " + score);
        score -= 3;
        System.out.println("-= 3: " + score);
        score *= 2;
        System.out.println("*= 2: " + score);
        score /= 5;
        System.out.println("/= 5: " + score);
        score %= 3;
        System.out.println("%= 3: " + score);

        // += also joins text
        String word = "Java";
        word += "!";
        System.out.println(word);

        // compound assignment hides a cast, so it can overflow silently
        byte big = 120;
        big += 10;
        System.out.println("big = " + big);

        // the right side is calculated first: x = x * (2 + 3)
        int x = 2;
        x *= 2 + 3;
        System.out.println("x = " + x);

        // a = b = 7 sets both, from right to left
        int p;
        int q;
        p = q = 7;
        System.out.println("p = " + p + ", q = " + q);
    }
}
