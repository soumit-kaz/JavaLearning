public class M15P04_FileSystemTree {

    abstract static class Node {
        private final String name;
        private Folder parent;

        Node(String name) {
            if (name.isEmpty() || name.contains("/")) {
                throw new IllegalArgumentException("invalid name '" + name + "'");
            }
            this.name = name;
        }

        String name() {
            return name;
        }

        // the same for files and folders: walk up through the parents
        final String path() {
            return parent == null ? "/" + name : parent.path() + "/" + name;
        }

        abstract long size();

        abstract int fileCount();

        // the tree as text, each line starting with "indent"
        abstract String render(String indent);

        // hook: only files can match, so the default finds nothing
        String find(String extension) {
            return "";
        }
    }

    static class File extends Node {
        private final long bytes;

        File(String name, long bytes) {
            super(name);
            this.bytes = bytes;
        }

        @Override
        long size() {
            return bytes;
        }

        @Override
        int fileCount() {
            return 1;
        }

        @Override
        String render(String indent) {
            return indent + name() + " (" + bytes + ")\n";
        }

        @Override
        String find(String extension) {
            return name().endsWith("." + extension) ? path() + " " : "";
        }
    }

    static class Folder extends Node {
        private Node[] children = new Node[0];

        Folder(String name) {
            super(name);
        }

        Folder add(Node child) {
            for (Node c : children) {
                if (c.name().equals(child.name())) {
                    throw new IllegalStateException("duplicate '" + child.name() + "' in " + path());
                }
            }
            // grow the array by one
            Node[] bigger = new Node[children.length + 1];
            for (int i = 0; i < children.length; i++) {
                bigger[i] = children[i];
            }
            bigger[children.length] = child;
            children = bigger;
            child.parent = this;
            return this;
        }

        // a folder's answers are built from its children's answers
        @Override
        long size() {
            long total = 0;
            for (Node c : children) {
                total += c.size();
            }
            return total;
        }

        @Override
        int fileCount() {
            int count = 0;
            for (Node c : children) {
                count += c.fileCount();
            }
            return count;
        }

        @Override
        String render(String indent) {
            String text = indent + name() + "/ [" + size() + "]\n";
            for (Node c : children) {
                text += c.render(indent + "  ");
            }
            return text;
        }

        @Override
        String find(String extension) {
            String found = "";
            for (Node c : children) {
                found += c.find(extension);
            }
            return found;
        }
    }

    static Folder sample() {
        Folder util = new Folder("util").add(new File("Str.java", 300)).add(new File("notes.txt", 50));
        Folder src = new Folder("src").add(new File("Main.java", 1200)).add(util);
        Folder docs = new Folder("docs").add(new File("readme.txt", 400));
        return new Folder("root").add(src).add(docs).add(new Folder("empty")).add(new File("build.gradle", 90));
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
        Folder root = sample();
        check("root size", root.size(), 2040);
        check("root fileCount", root.fileCount(), 5);
        check("find java", root.find("java").trim(), "/root/src/Main.java /root/src/util/Str.java");
        check("find txt", root.find("txt").trim(), "/root/src/util/notes.txt /root/docs/readme.txt");
        check("find md", "[" + root.find("md") + "]", "[]");
        check("render root (lines joined by |)", root.render("").trim().replace("\n", " | "),
                "root/ [2040] |   src/ [1550] |     Main.java (1200) |     util/ [350] |       Str.java (300) |"
                        + "       notes.txt (50) |   docs/ [400] |     readme.txt (400) |   empty/ [0] |   build.gradle (90)");

        String error;
        try {
            new Folder("a").add(new File("x.txt", 1)).add(new File("x.txt", 2));
            error = "no exception";
        } catch (IllegalStateException e) {
            error = e.getMessage();
        }
        check("add x.txt twice", error, "duplicate 'x.txt' in /a");

        try {
            new File("a/b", 1);
            error = "no exception";
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        check("new File(\"a/b\")", error, "invalid name 'a/b'");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
