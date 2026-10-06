import java.util.ArrayList;

public class AddString {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("!");

        int secondLastIndex = list.size() - 1;

        String value = "ABCD";
        list.add(secondLastIndex, value);

        System.out.println(list);
    }


}
