import java.util.Arrays;

public class TestProblems_2_3 {

    public static void main(String[] args) {

        Vehicle[] vehicles = { new Truck("Ford", "US", 65000), new Bus("Mercedes", "Germany", 65000),
                new Minibus("Volkswagen", "Germany", 65000), new Truck("Iveco", "Romania", 65000) };

        Arrays.sort(vehicles);

        System.out.println(Arrays.toString(vehicles));
    }

}
