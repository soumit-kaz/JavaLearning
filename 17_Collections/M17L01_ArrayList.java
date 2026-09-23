import java.util.ArrayList;
import java.util.List;

public class M17L01_ArrayList {

    public static void main(String[] args) {
        // an array has a fixed size; an ArrayList grows by itself
        // declare with the interface (List), create with the class (ArrayList)
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");
        System.out.println("list: " + fruits);

        // add at an index shifts the later elements right
        fruits.add(1, "blueberry");
        System.out.println("add(1): " + fruits);

        System.out.println("get(0): " + fruits.get(0));
        System.out.println("size: " + fruits.size());

        // set replaces an element and returns the old one
        System.out.println("set(0) old: " + fruits.set(0, "apricot"));

        System.out.println("contains banana: " + fruits.contains("banana"));
        // indexOf returns -1 when the element is missing
        System.out.println("indexOf mango: " + fruits.indexOf("mango"));

        // remove by value
        fruits.remove("banana");
        System.out.println("remove banana: " + fruits);

        // index loop: use it when you need the position
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(i + ": " + fruits.get(i));
        }

        // for-each loop: the simplest way to visit every element
        for (String fruit : fruits) {
            System.out.println("upper: " + fruit.toUpperCase());
        }

        // forEach with a method reference
        fruits.forEach(System.out::println);

        // addAll appends a whole collection; clear empties the list
        fruits.addAll(List.of("fig", "kiwi"));
        System.out.println("addAll: " + fruits);
        fruits.clear();
        System.out.println("isEmpty: " + fruits.isEmpty());
    }
}
