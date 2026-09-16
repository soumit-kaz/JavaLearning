public class M08L10_TextBlocks {

    public static void main(String[] args) {
        // a text block starts with """ and a line break; common indentation is removed
        String poem = """
                Roses are red,
                  Violets are blue.
                """;
        System.out.print(poem);

        // closing """ on its own line adds a final line break
        String withBreak = """
                abc
                """;
        String withoutBreak = """
                abc""";
        System.out.println("lengths: " + withBreak.length() + " " + withoutBreak.length());

        // trailing spaces are removed, but \s keeps one; \ at line end joins lines
        String joined = """
                one\s\
                line""";
        System.out.println(joined);

        // quotes need no escaping, and formatted fills in values
        String json = """
                {"name": "%s", "age": %d}
                """.formatted("Ann", 30);
        System.out.print(json);
    }
}
