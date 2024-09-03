public class Bus extends Vehicle {

    Bus(String n, String c, double price) {

        super(n, c, price);
    }

    public double computeRoadTax() {

        return this.getBasePrice() * 0.04;
    }
}
