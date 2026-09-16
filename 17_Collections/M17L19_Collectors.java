import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class M17L19_Collectors {

    record Employee(String name, String dept, int salary) {
    }

    public static void main(String[] args) {
        List<Employee> staff = List.of(
                new Employee("ava", "eng", 130),
                new Employee("ben", "eng", 110),
                new Employee("cat", "ops", 90),
                new Employee("dan", "sales", 70),
                new Employee("eve", "eng", 150));

        // groupingBy builds a map of lists
        Map<String, List<Employee>> byDept = staff.stream()
                .collect(Collectors.groupingBy(Employee::dept));
        System.out.println("eng size: " + byDept.get("eng").size());

        // TreeMap::new keeps the keys sorted; counting counts each group
        Map<String, Long> countByDept = staff.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.counting()));
        System.out.println("count: " + countByDept);

        // summingInt adds up a number in each group
        Map<String, Integer> totalByDept = staff.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new,
                        Collectors.summingInt(Employee::salary)));
        System.out.println("total: " + totalByDept);

        // mapping keeps only the names in each group
        Map<String, List<String>> namesByDept = staff.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new,
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println("names: " + namesByDept);

        // partitioningBy splits into a true group and a false group
        Map<Boolean, Long> highPaid = staff.stream()
                .collect(Collectors.partitioningBy(e -> e.salary() >= 100, Collectors.counting()));
        System.out.println("salary >= 100: " + highPaid);

        // toMap throws on duplicate keys unless you pass a merge function (here Math::max)
        Map<String, Integer> maxByDept = staff.stream()
                .collect(Collectors.toMap(Employee::dept, Employee::salary, Math::max, TreeMap::new));
        System.out.println("max: " + maxByDept);

        // joining builds one string
        String joined = staff.stream().map(Employee::name).collect(Collectors.joining(", "));
        System.out.println("joined: " + joined);

        // averagingInt gives the mean as a double
        double average = staff.stream().collect(Collectors.averagingInt(Employee::salary));
        System.out.println("average: " + average);
    }
}
