import java.time.LocalDate;

public class Employee implements Comparable {

    private String cnp;
    private LocalDate hiringDate;
    private String specialization;
    private double salary;

    Employee(String c, LocalDate hd, String spec, double sal) {
        this.cnp = c;
        this.hiringDate = hd;
        this.specialization = spec;
        this.salary = sal;
    }

    public String toString() {

        return "\nEmployee-CNP: " + this.cnp + " | Hiring Date: " + this.hiringDate + " | Specialization: "
                + this.specialization + " | Salary: " + this.salary;
    }

    @Override
    public int hashCode() {

        return cnp.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (o.getClass() != Employee.class) {
            return false;
        }

        Employee e = (Employee) o;

        if (this.cnp.equals(e.cnp)) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Object o) {

        Employee e = (Employee) o;
        if (e.salary == this.salary) {
            return cnp.compareTo(e.cnp);
        }
        return (int) (this.salary - e.salary);
    }
}