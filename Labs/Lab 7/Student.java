import java.util.*;

public class Student {

    public int studentId;
    public String name;
    public String group;

    public List<Course> courses;

    Student(int i, String n, String g) {
        studentId = i;
        name = n;
        group = g;
        courses = new ArrayList<>();
    }

    public String toString() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
