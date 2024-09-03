import java.util.*;
import java.time.*;

public class TestClass {

    public static void main(String[] args) {

        // First Problem:
        ArrayList<Employee> empList = new ArrayList<Employee>();

        empList.add(new Employee("1", LocalDate.of(2012, 8, 19), "DBA", 9843));
        empList.add(new Employee("1", LocalDate.of(2012, 8, 19), "DBA", 9843));
        empList.add(new Employee("2", LocalDate.of(2009, 2, 12), "Java Programmer", 19843));
        empList.add(new Employee("2", LocalDate.of(2009, 2, 12), "Java Programmer", 19843));
        empList.add(new Employee("3", LocalDate.of(2019, 4, 10), "JS Programmer", 11843));
        empList.add(new Employee("4", LocalDate.of(2020, 12, 20), "PHP Programmer", 20843));
        empList.add(new Employee("5", LocalDate.of(2022, 1, 29), "Python Programmer", 8500));
        empList.add(new Employee("2", LocalDate.of(2003, 11, 9), "C Programmer", 8500));
        empList.add(new Employee("7", LocalDate.of(1999, 9, 15), "C++ Programmer", 45000));
        empList.add(new Employee("8", LocalDate.of(2002, 7, 23), "Project Manager", 55000));

        Iterator<Employee> empIterator = empList.iterator();

        System.out.println("\n\nUsing an Iterator to display the list:\n");

        while (empIterator.hasNext()) {

            System.out.println(empIterator.next().toString());
        }

        System.out.println("\n\nUse ListIterator to display the list backwards & add an employee in the middle:\n");

        ListIterator<Employee> empListIterator = empList.listIterator(empList.size());
        int i = empList.size();

        while (empListIterator.hasPrevious()) {

            if (i == empList.size() / 2) {
                empListIterator.add(new Employee("9", LocalDate.of(1990, 12, 2), "CEO", 190000));
            }
            i--;
            System.out.println(empListIterator.previous().toString());
        }

        // I am comparing the employees by comparing only their salaries

        System.out.println("\n\nCreate a hashset from the list of employees to display the list:\n");
        HashSet<Employee> empHashset = new HashSet<>(empList);
        Iterator<Employee> emp_hashSet_iterator = empHashset.iterator();
        while (emp_hashSet_iterator.hasNext()) {
            System.out.println(emp_hashSet_iterator.next().toString());
        }

        System.out.println(
                "\n\nCreate a TreeSet from the list of employees to display it in ascending order (by salary):\n");
        TreeSet<Employee> empTreeSet = new TreeSet<>(empList);
        empTreeSet.forEach(e -> System.out.println(e.toString()));

        // Second Problem:

        ArrayList<Employee2> emp2List = new ArrayList<Employee2>();
        emp2List.add(new Employee2("Mario", 95000, LocalDate.of(2023, 12, 12), "Bucharest"));
        emp2List.add(new Employee2("John", 195000, LocalDate.of(2003, 1, 2), "Atlanta"));
        emp2List.add(new Employee2("Alice", 12700, LocalDate.of(2014, 5, 12), "Moscow"));
        emp2List.add(new Employee2("Matthew", 45700, LocalDate.of(2018, 3, 3), "Amman"));
        emp2List.add(new Employee2("Khaled", 87500, LocalDate.of(2012, 6, 19), "Tokyo"));
        emp2List.add(new Employee2("Andrew", 129000, LocalDate.of(2016, 7, 20), "Riga"));
        emp2List.add(new Employee2("Jacob", 78000, LocalDate.of(2021, 10, 15), "Teheran"));
        emp2List.add(new Employee2("Daniel", 63200, LocalDate.of(2008, 9, 18), "Madrid"));
        emp2List.add(new Employee2("Karym", 24500, LocalDate.of(2011, 3, 25), "Jeddah"));
        emp2List.add(new Employee2("Ted", 41000, LocalDate.of(1989, 1, 9), "Toronto"));

        Map<String, Employee2> emp2Map = new HashMap<String, Employee2>();
        emp2List.forEach(e -> emp2Map.put(e.getName(), e));

        System.out.println("\n\nInsert a name of an employee you're looking for: ");
        Scanner s = new Scanner(System.in);
        String searchName = s.next();
        s.close();

        if (emp2Map.containsKey(searchName)) {

            System.out.println("\nWe have found the employee: " + emp2Map.get(searchName).toString());
        } else {
            System.out.println("We haven't found the employee.");
        }
    }
}