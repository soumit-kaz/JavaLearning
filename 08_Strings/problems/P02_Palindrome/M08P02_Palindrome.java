public class M08P02_Palindrome {
    public static void main(String[] args) {
        String[] inputs = {"racecar", "A man, a plan, a canal: Panama", "race a car", "", "0P", "No 'x' in Nixon"};
        boolean[] expected = {true, true, false, true, false, true};

        for (int t = 0; t < inputs.length; t++) {
            String text = inputs[t];
            int left = 0;
            int right = text.length() - 1;
            boolean isPalindrome = true;

            while (left < right) {
                char a = text.charAt(left);
                char b = text.charAt(right);
                // skip anything that is not a letter or digit
                if (!Character.isLetterOrDigit(a)) {
                    left++;
                } else if (!Character.isLetterOrDigit(b)) {
                    right--;
                } else if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                    isPalindrome = false;
                    break;
                } else {
                    left++;
                    right--;
                }
            }

            String result = isPalindrome == expected[t] ? "PASS" : "FAIL";
            System.out.println("\"" + text + "\" -> " + isPalindrome + "  " + result);
        }
    }
}
