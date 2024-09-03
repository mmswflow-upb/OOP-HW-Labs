public class Minibus extends Vehicle {

    Minibus(String n, String c, double price) {

        super(n, c, price);
    }

    public double computeRoadTax() {

        return this.getBasePrice() * 0.03;
    }
}
