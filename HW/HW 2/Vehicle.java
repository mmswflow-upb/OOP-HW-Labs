public abstract class Vehicle implements Taxable, Comparable {

    private double basePrice;
    private String name;
    private String country;

    Vehicle(String n, String c, double price) {
        this.name = n;
        this.country = c;
        this.basePrice = price;
    }

    public String toString() {

        return "Vehicle Type: " + this.getClass().getName() + " | Manufacturer: " + this.name + "\nBase Price: "
                + this.basePrice +
                " | Country: " + this.country + " | Total Taxes: " + this.computeTotalTax() + "\n\n";
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public double computeVAT() {

        return this.basePrice * 0.19;
    }

    public double computeCustomTax() {

        if (this.country.equals("Romania")) {

            return 0;
        }

        return this.basePrice * 0.1;
    }

    public abstract double computeRoadTax();

    public double computeTotalTax() {

        return computeCustomTax() + computeVAT() + computeRoadTax();
    }

    public int compareTo(Object o) {

        // 1 first comes after second
        // 0 they are equal in order
        // -1 first comes before second

        if (this == o) {
            return 0;
        }

        Vehicle v = (Vehicle) o;

        // Check if their taxes reach the same value:
        if (this.computeTotalTax() == v.computeTotalTax()) {

            // We go in descending order

            if (this.basePrice > v.getBasePrice()) {
                return -1;
            } else if (this.basePrice < v.getBasePrice()) {
                return 1;
            }
            return 0;
        }

        // Total Taxes don't reach the same value:

        if (this.computeTotalTax() > v.computeTotalTax()) {
            return 1;
        } else if (this.computeTotalTax() < v.computeTotalTax()) {
            return -1;
        }

        return 0;

    }
}
