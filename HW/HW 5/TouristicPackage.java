import java.util.*;

public class TouristicPackage {

    private String name;
    private int id;
    private double price;
    private ArrayList<Attraction> attractions;

    TouristicPackage(String n, int i, double p, Attraction[] atts) {

        name = n;
        id = i;
        price = p;
        attractions = new ArrayList<>();
        for (Attraction at : atts) {
            attractions.add(at);
        }
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }
}