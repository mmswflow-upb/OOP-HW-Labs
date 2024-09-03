public class Loan {

    private int id;
    private double ammount;
    private Person borrower;

    Loan(int i, double amm, Person p) {

        this.id = i;
        this.ammount = amm;
        this.borrower = p;
    }

    public Person getBorrower() {
        return this.borrower;
    }

    public int getId() {
        return this.id;
    }

    public double getAmmount() {
        return this.ammount;
    }

    public void increaseAmmount(double added) {
        this.ammount += added;
    }
}
