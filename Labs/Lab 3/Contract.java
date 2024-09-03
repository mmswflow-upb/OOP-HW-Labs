import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Contract implements Storable {

    /*
     * 
     * Class Contract has an array of courses with methods addCourse(Course),
     * deleteCourse(type, stream, name) sort(), display(). Courses are sorted by
     * stream, type, name. If 2 courses
     * are equal raise a custom exception in method sort().Make Contract implement
     * Storable (see below). Course should be immutable
     */

    private ArrayList<Course> courses = new ArrayList<Course>();

    public void addCourse(Course c) {
        courses.add(c);
    }

    public boolean deleteCourse(Course.Stream stream, Course.Type t, String name) {

        for (Course c : courses) {

            if (c.getName().equals(name) && c.getStream() == stream && c.getType() == t) {

                courses.remove(c);
                return true;
            }
        }

        return false;
    }

    public void display() {

    }

    public void store(String fileName) {

        FileWriter fw;
        BufferedWriter bw;

        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);

            bw.write("Courses in my contract: \n");
            bw.write(courses.toString());

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
