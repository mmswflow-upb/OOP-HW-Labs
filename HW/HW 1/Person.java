public class Person {

    private String name;
    private String surname;
    private String CNP;

    Person(String n, String sur, String cnp) {
        this.CNP = cnp;
        this.name = n;
        this.surname = sur;
    }

    public String getName() {
        return this.name;
    }

}
