public class Truck extends Vehicle {

    Truck(String n, String c, double price) {

        super(n, c, price);
    }

    public double computeRoadTax() {

        return this.getBasePrice() * 0.05;
    }
}
