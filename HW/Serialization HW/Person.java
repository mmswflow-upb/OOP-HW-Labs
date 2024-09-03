import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String city;
    private String country;

    public Person(String name, String surname, String gender, int age, String city, String country) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String toString() {
        return name + " " + surname + " Gender: " + gender;
    }

}
