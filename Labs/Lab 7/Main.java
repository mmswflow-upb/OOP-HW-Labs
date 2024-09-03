import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {

        public static List<Integer> exercise1(List<Integer> li) {

                return li.stream()
                                .filter(n -> n % 2 == 0)
                                .map((n) -> n * n)
                                .collect(Collectors.toList());

        }

        public static int exercise2(List<Integer> li) {

                return li.stream().map(n -> n * n).reduce(0, (sum, n) -> sum += n);
        }

        public static Map<Integer, Long> exercise3a(List<String> ls) {

                return ls.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));

        }

        public static Map<Integer, Long> exercise3b(List<String> ls) {
                return ls.stream()
                                .map(String::length)
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }

        public static List<String> exercise4(List<List<String>> ls) {

                return ls.stream()
                                .flatMap(List::stream)
                                .distinct()
                                .collect(Collectors.toList());
        }

        public static String exercise5(List<Student> students, String g) {

                return students.stream()
                                .filter(s -> s.group.equals(g)).map(s -> s.toString())
                                .collect(Collectors.joining(", "));

        }

        public static void exercise6() {

                List<Integer> largeList = IntStream.rangeClosed(1, 100_000_000)
                                .boxed()
                                .toList();

                long startTimeSeq = System.nanoTime();
                long sumSequential = largeList.stream()
                                .mapToLong(i -> i * i)
                                .sum();
                long endTimeSeq = System.nanoTime();
                long durationSeq = (endTimeSeq - startTimeSeq) / 1_000_000; // Convert to milliseconds

                long startTimePar = System.nanoTime();
                long sumParallel = largeList.parallelStream()
                                .mapToLong(i -> i * i)
                                .sum();
                long endTimePar = System.nanoTime();
                long durationPar = (endTimePar - startTimePar) / 1_000_000; // Convert to milliseconds

                System.out.println(
                                "Exercise 6 Result:\n\nSum using sequential stream: " + sumSequential + " in "
                                                + durationSeq + " ms");
                System.out.println("Sum using parallel stream: " + sumParallel + " in " + durationPar + " ms\n\n");
        }

        public static List<Student> exercise7(List<Student> students) {
                return students.stream().filter(s -> s.courses.stream().anyMatch(c -> c.creditPoints > 5))
                                .collect(Collectors.toList());
        }

        public static List<Student> exercise8(List<Student> students) {
                return students.stream()
                                .filter(s -> s.courses.stream().map(c -> c.creditPoints).reduce(0,
                                                (tempSum, creditPoints) -> tempSum + creditPoints) > 30)
                                .collect(Collectors.toList());
        }

        public static Map<Integer, List<Integer>> exercise9(List<Course> courses) {

                return courses.stream()
                                .collect(Collectors.toMap(
                                                c -> c.courseId,
                                                c -> c.students.stream()
                                                                .map(student -> student.studentId)
                                                                .collect(Collectors.toList())));
        }

        public static List<Student> exercise10(List<Student> students, String g) {

                return students.stream()
                                .filter(s -> s.group.equals(g))
                                .sorted(Comparator.comparing(Student::toString))
                                .limit(5)
                                .collect(Collectors.toList());
        }

        public static Map<String, List<Course>> exercise11(List<Student> students) {

                return students.stream()
                                .collect(Collectors.groupingBy(
                                                s -> s.group,
                                                Collectors.flatMapping(
                                                                s -> s.getCourses().stream(),
                                                                Collectors.collectingAndThen(Collectors.toList(),
                                                                                courses -> courses.stream()
                                                                                                .distinct()
                                                                                                .collect(Collectors
                                                                                                                .toList())))));
        }

        public static void main(String[] args) {

                ArrayList<Integer> li1 = new ArrayList<>();
                for (int i = 1; i < 11; i++) {

                        li1.add(i);
                }

                System.out.println("\n\nExercise 1 Result: " + exercise1(li1) + "\n\n");
                System.out.println("Exercise 2 Result: " + exercise2(li1) + "\n\n");

                System.out.println("Exercise 3a Result: "
                                + exercise3a(Arrays.asList("1", "12", "123", "1234", "12345", "0", "00", "b", "000",
                                                "0000", "00000"))
                                + "\n\n");
                System.out.println("Exercise 3b Result: "
                                + exercise3b(Arrays.asList("1", "12", "123", "1234", "12345", "0", "00", "b", "000",
                                                "0000", "00000"))
                                + "\n\n");

                System.out.println("Exercise 4 Result: "
                                + exercise4(Arrays.asList(Arrays.asList("A", "B", "C", "D"),
                                                Arrays.asList("D", "E", "F", "G", "H")))
                                + "\n\n");

                List<Course> courses = Arrays.asList(new Course(1, 4, 2, "Special Math"),
                                new Course(2, 6, 2, "Databases"),
                                new Course(3, 5, 2, "DIC"),
                                new Course(4, 3, 2, "Microeconomics"),
                                new Course(5, 7, 2, "Operating Systems 2"),
                                new Course(6, 4, 2, "ED 2"),
                                new Course(7, 5, 2, "OOP"),
                                new Course(8, 1, 2, "Physical Edcuation 3"));

                Student mario = new Student(1, "Mario", "1221EB");
                mario.courses = Arrays.asList(courses.get(0), courses.get(1));

                Student azzam = new Student(2, "Azzam", "1221EB");
                azzam.courses = Arrays.asList(courses.get(2), courses.get(3));

                Student essam = new Student(3, "Essam", "1222EB");
                essam.courses = Arrays.asList(courses.get(4), courses.get(5));

                Student abd = new Student(4, "Abd", "1221Eb");
                abd.courses = Arrays.asList(courses.get(6), courses.get(7));

                Student layla = new Student(5, "Layla", "1221EB");// 30 Credit Points exactly
                layla.courses = Arrays.asList(courses.get(0), courses.get(1),
                                courses.get(2), courses.get(3), courses.get(4),
                                courses.get(5), courses.get(7));

                Student andra = new Student(6, "Andra", "1221EB");// 35 Credit Points
                andra.courses = courses;

                List<Student> students = Arrays.asList(mario, azzam, abd, essam, layla, andra);

                courses.forEach(
                                c -> c.students = (students.stream().filter(s -> s.courses.contains(c))
                                                .collect(Collectors.toList())));

                System.out
                                .println("Exercise 5 Result: "
                                                + exercise5(students, "1221EB")
                                                + "\n\n");
                exercise6();

                System.out.println("Exercise 7 Result: " + exercise7(students) + "\n\n");

                System.out.println(
                                "Exercise 8 Result: " + exercise8(students) + "\n\n");

                System.out.println("Exercise 9 Result: " + exercise9(courses) + "\n\n");

                System.out.println("Exercise 10 Result: " + exercise10(students, "1221EB") + "\n\n");

                System.out.println("Exercise 11 Result: " + exercise11(students) + "\n\n");
        }
}