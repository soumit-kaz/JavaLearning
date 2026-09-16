public class M10L07_RecursionExamples {

    // Euclid: gcd(a, b) = gcd(b, a % b), and gcd(a, 0) = a
    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // compare the outer letters, then check the inside
    static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    static int search(int[] sorted, int target, int low, int high) {
        // empty range: the value is not there
        if (low > high) {
            return -1;
        }
        // this form avoids overflow of (low + high) / 2
        int mid = low + (high - low) / 2;
        if (sorted[mid] == target) {
            return mid;
        }
        // keep only the half that can contain the target
        if (sorted[mid] < target) {
            return search(sorted, target, mid + 1, high);
        }
        return search(sorted, target, low, mid - 1);
    }

    static void hanoi(int disks, char from, char to, char via) {
        if (disks == 0) {
            return;
        }
        // move the smaller disks away, move the biggest, put the smaller back
        hanoi(disks - 1, from, via, to);
        System.out.println("disk " + disks + ": " + from + " -> " + to);
        hanoi(disks - 1, via, to, from);
    }

    static void subsets(String letters, int index, String chosen) {
        // every letter has been decided: print this subset
        if (index == letters.length()) {
            System.out.print("[" + chosen + "] ");
            return;
        }
        // choice 1: skip this letter; choice 2: take it
        subsets(letters, index + 1, chosen);
        subsets(letters, index + 1, chosen + letters.charAt(index));
    }

    public static void main(String[] args) {
        System.out.println("gcd(48, 18) = " + gcd(48, 18));
        System.out.println("racecar = " + isPalindrome("racecar"));
        System.out.println("rocket = " + isPalindrome("rocket"));

        int[] numbers = {2, 5, 8, 12, 16, 23, 38, 56};
        System.out.println("23 at " + search(numbers, 23, 0, numbers.length - 1));
        System.out.println("7 at " + search(numbers, 7, 0, numbers.length - 1));

        // n disks need 2^n - 1 moves
        hanoi(2, 'A', 'C', 'B');

        // 3 letters give 2^3 = 8 subsets
        subsets("xyz", 0, "");
        System.out.println();
    }
}
