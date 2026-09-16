public class M15P01_TextCaseConverter {

    abstract static class CaseConverter {
        // template method: split into words, then join them in the subclass style
        final String convert(String text) {
            String[] words = splitWords(text);
            String result = "";
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    result += separator();
                }
                result += shapeWord(words[i], i);
            }
            return result;
        }

        abstract String separator();

        abstract String shapeWord(String lowerWord, int index);

        static String capitalize(String word) {
            return Character.toUpperCase(word.charAt(0)) + word.substring(1);
        }

        // "parseHTTPResponse" -> ["parse", "http", "response"]
        private static String[] splitWords(String text) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (!Character.isLetterOrDigit(c)) {
                    sb.append(' ');
                    continue;
                }
                if (i > 0 && startsNewWord(text, i)) {
                    sb.append(' ');
                }
                sb.append(Character.toLowerCase(c));
            }
            String cleaned = sb.toString().trim();
            if (cleaned.isEmpty()) {
                return new String[0];
            }
            return cleaned.split(" +");
        }

        private static boolean startsNewWord(String t, int i) {
            char prev = t.charAt(i - 1);
            char c = t.charAt(i);
            // lower followed by upper: "parseHttp"
            if (Character.isLowerCase(prev) && Character.isUpperCase(c)) {
                return true;
            }
            // last capital of an acronym: "HTTPResponse" -> the "R"
            boolean nextIsLower = i + 1 < t.length() && Character.isLowerCase(t.charAt(i + 1));
            if (Character.isUpperCase(prev) && Character.isUpperCase(c) && nextIsLower) {
                return true;
            }
            // switching between letters and digits: "version2Beta"
            return Character.isLetterOrDigit(prev) && Character.isDigit(prev) != Character.isDigit(c);
        }
    }

    static class CamelCase extends CaseConverter {
        @Override
        String separator() {
            return "";
        }

        @Override
        String shapeWord(String word, int index) {
            return index == 0 ? word : capitalize(word);
        }
    }

    static class SnakeCase extends CaseConverter {
        @Override
        String separator() {
            return "_";
        }

        @Override
        String shapeWord(String word, int index) {
            return word;
        }
    }

    static class ConstantCase extends SnakeCase {
        @Override
        String shapeWord(String word, int index) {
            return word.toUpperCase();
        }
    }

    static class KebabCase extends CaseConverter {
        @Override
        String separator() {
            return "-";
        }

        @Override
        String shapeWord(String word, int index) {
            return word;
        }
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("CamelCase \"hello world\"", new CamelCase().convert("hello world"), "helloWorld");
        check("SnakeCase \"parseHTTPResponse\"", new SnakeCase().convert("parseHTTPResponse"), "parse_http_response");
        check("ConstantCase \"maxRetryCount\"", new ConstantCase().convert("maxRetryCount"), "MAX_RETRY_COUNT");
        check("KebabCase \"  Already--Kebab__and Spaces \"", new KebabCase().convert("  Already--Kebab__and Spaces "), "already-kebab-and-spaces");
        check("KebabCase \"XMLHttpRequest\"", new KebabCase().convert("XMLHttpRequest"), "xml-http-request");
        check("SnakeCase \"version2Beta\"", new SnakeCase().convert("version2Beta"), "version_2_beta");
        check("CamelCase \"version2Beta\"", new CamelCase().convert("version2Beta"), "version2Beta");
        check("CamelCase \"!!!\"", new CamelCase().convert("!!!"), "");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
