import java.util.*;

public class Course {

    public int courseId;
    public int creditPoints;
    public int year;
    public String fullName;

    public List<Student> students;

    Course(int i, int credPoints, int y, String n) {
        courseId = i;
        creditPoints = credPoints;
        year = y;
        fullName = n;
        students = new ArrayList<>();
    }

    public String toString() {

        return fullName;
    }

    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        Course c = (Course) o;
        return courseId == c.courseId;
    }

    public int hashCode() {

        return courseId;
    }
}
