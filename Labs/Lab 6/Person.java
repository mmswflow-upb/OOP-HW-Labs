public class Person {

    private String name;
    private String gender;
    private String jobDesc;
    private int xpInYears;
    private String location;

    Person(String n, String g, String jd, int xp, String l) {

        name = n;
        gender = g;
        jobDesc = jd;
        xpInYears = xp;
        location = l;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getJobDescription() {

        return jobDesc;
    }

    public String getExp() {
        return String.valueOf(xpInYears);
    }

    public String getLocation() {
        return location;
    }

    public String toString() {
        return "Name: " + name + " | Gender: " + gender + " | Job Description: " + jobDesc + "\n"
                + "Years of Experienec: " + xpInYears + " | Location: " + location;
    }
}