import java.util.*;

public class TestHW {

    public static void main(String[] args) {

        Attraction att1 = new Attraction("Disney Land", "Paris");
        Attraction att2 = new Attraction("Eiffel Tower", "Paris");
        Attraction att3 = new Attraction("Mount Fuji", "Tokyo");
        Attraction att4 = new Attraction("Imperial Tokyo", "Tokyo");
        Attraction att5 = new Attraction("The Great Wall", "China");
        Attraction att6 = new Attraction("The Forbidden City & Imperial Palace", "Beijing");
        Attraction att7 = new Attraction("The Terracotta Army", "Xi'-an");
        Attraction att8 = new Attraction("Gardens By The Bay", "Singapore");
        Attraction att9 = new Attraction("Borobudur", "Indonesia");
        Attraction att10 = new Attraction("Gili Islands", "Indonesia");

        Attraction[] atts1 = { att1, att2, att2 };
        Attraction[] atts2 = { att3, att4, att5 };
        Attraction[] atts3 = { att7, att10, att10 };
        Attraction[] atts4 = { att3, att3, att1 };
        Attraction[] atts5 = { att9, att8, att5 };
        Attraction[] atts6 = { att10, att2, att9 };
        Attraction[] atts7 = { att4, att9, att10 };
        Attraction[] atts8 = { att6, att6, att3 };
        Attraction[] atts9 = { att6, att7, att9 };
        Attraction[] atts10 = { att8, att8, att3 };

        ArrayList<TouristicPackage> sold_packages = new ArrayList<>();
        sold_packages.add(new TouristicPackage("A", 1, 20000, atts1));
        sold_packages.add(new TouristicPackage("B", 2, 19000, atts2));
        sold_packages.add(new TouristicPackage("C", 3, 44000, atts3));
        sold_packages.add(new TouristicPackage("D", 4, 65000, atts4));
        sold_packages.add(new TouristicPackage("E", 5, 33300, atts5));
        sold_packages.add(new TouristicPackage("F", 6, 21000, atts6));
        sold_packages.add(new TouristicPackage("G", 7, 98300, atts7));
        sold_packages.add(new TouristicPackage("H", 8, 41000, atts8));
        sold_packages.add(new TouristicPackage("I", 9, 54300, atts9));
        sold_packages.add(new TouristicPackage("J", 3, 56000, atts10));

        Map<Attraction, Integer> sold_attractionsMap = new HashMap<>();

        sold_packages.forEach(pack -> pack.getAttractions()
                .forEach(attr -> sold_attractionsMap.put(attr,
                        sold_attractionsMap.containsKey(attr) ? sold_attractionsMap.get(attr) + 1 : 1)));

        System.out.println("\n\nThe List of Attractions & No. of Purchases:\n");
        System.out.println(sold_attractionsMap.toString());

        Map<Attraction, Double> attractionPopularity = new HashMap<>();
        sold_attractionsMap.forEach((att, no) -> {
            attractionPopularity.put(att, (double) no / 30);
        });
        System.out.println("\n\nThe popularity of each attraction:\n");
        System.out.println(attractionPopularity.toString());
    }
}
