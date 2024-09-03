public class Attraction {

    private String name;
    private String location;

    Attraction(String n, String l) {
        name = n;
        location = l;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String toString() {
        return "\nAttraction Name: " + name + " | Location: " + location;
    }
}
