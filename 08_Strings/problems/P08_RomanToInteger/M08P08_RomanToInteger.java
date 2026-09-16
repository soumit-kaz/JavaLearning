public class M08P08_RomanToInteger {
    public static void main(String[] args) {
        String[] inputs = {"III", "IV", "IX", "LVIII", "MCMXCIV", "MMMCMXCIX"};
        int[] expected = {3, 4, 9, 58, 1994, 3999};

        // the value of each symbol sits at the same position as the symbol
        String symbols = "IVXLCDM";
        int[] values = {1, 5, 10, 50, 100, 500, 1000};

        for (int t = 0; t < inputs.length; t++) {
            String roman = inputs[t];
            int total = 0;

            for (int i = 0; i < roman.length(); i++) {
                int value = values[symbols.indexOf(roman.charAt(i))];
                int next = 0;
                if (i + 1 < roman.length()) {
                    next = values[symbols.indexOf(roman.charAt(i + 1))];
                }

                // a smaller value before a bigger one is subtracted (IV = 5 - 1)
                if (value < next) {
                    total -= value;
                } else {
                    total += value;
                }
            }

            String result = total == expected[t] ? "PASS" : "FAIL";
            System.out.println(roman + " -> " + total + "  " + result);
        }
    }
}
