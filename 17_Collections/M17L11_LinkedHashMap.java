import java.util.LinkedHashMap;
import java.util.Map;

public class M17L11_LinkedHashMap {

    public static void main(String[] args) {
        // LinkedHashMap remembers insertion order
        Map<String, Integer> steps = new LinkedHashMap<>();
        steps.put("wake", 7);
        steps.put("run", 8);
        steps.put("work", 9);
        System.out.println("steps: " + steps);

        // putting an existing key again keeps its position
        steps.put("wake", 6);
        System.out.println("re-put wake: " + steps);

        // removing and adding again moves a key to the end
        steps.remove("wake");
        steps.put("wake", 6);
        System.out.println("re-added wake: " + steps);

        // access order (third argument true): every get moves the key to the end
        Map<String, Integer> recent = new LinkedHashMap<>(16, 0.75f, true);
        recent.put("a", 1);
        recent.put("b", 2);
        recent.put("c", 3);
        recent.get("a");
        System.out.println("access order: " + recent);

        // the first key is now the least recently used one
        String eldest = recent.keySet().iterator().next();
        System.out.println("least recent: " + eldest);
    }
}
