import java.util.Random;

public class Candidate extends Thread {

    private Embassy embassy;

    private String firstName;
    private String lastName;
    private int age;
    private String passportNumber;
    private int educationLevel;
    private int numberOfApplications;

    public Candidate(Embassy embassy, String firstName, String lastName, int age, String passportNumber,
            int educationLevel, int numberOfApplications) {
        this.embassy = embassy;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passportNumber = passportNumber;
        this.educationLevel = educationLevel;
        this.numberOfApplications = numberOfApplications;
    }

    @Override
    public void run() {

        for (int i = 0; i < numberOfApplications; i++) {
            applyForVisa(String.valueOf(i + 1));

        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void applyForVisa(String id) {

        Random random = new Random();
        this.educationLevel = random.nextInt(6);// from 0 to 5

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        embassy.addApplication(
                new Visa(embassy, firstName, lastName, age, passportNumber, educationLevel, numberOfApplications,
                        lastName + id));
    }

    @Override
    public String toString() {
        return "{First name: " + firstName + "\nLast Name: " + lastName + "\nAge: " + age + "\nPassport Number: "
                + passportNumber + "\nEducation Level: " + educationLevel + "\nNumber of Applications: "
                + numberOfApplications
                + "}\n\n";
    }
}
