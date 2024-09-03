import java.time.*;

public class Employee2 {

    private String name;
    private double salary;
    private LocalDate hiringDate;
    private String address;

    Employee2(String n, double s, LocalDate hd, String ad) {

        this.name = n;
        this.salary = s;
        this.hiringDate = hd;
        this.address = ad;
    }

    public String getName() {
        return name;
    }

    public String toString() {

        return "\nName: " + name + " | Salary: " + salary + " | Hiring Date: " + " | Address: " + address;
    }
}
