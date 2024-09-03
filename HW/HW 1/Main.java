public class Main {

    public static void main(String[] args) {

        Bank bank1 = new Bank();

        Person jamesMichael = new Person("James", "Michael", "9413151CF");

        Loan l1 = new Loan(1, 900, jamesMichael);
        Loan l2 = new Loan(2, 500, jamesMichael);
        Loan l3 = new Loan(3, 9000, jamesMichael);
        Loan l4 = new Loan(4, 2530, jamesMichael);

        bank1.addLoan(l1);
        bank1.addLoan(l2);
        bank1.addLoan(l3);
        bank1.addLoan(l4);

        bank1.remove(l4);

        bank1.printInFile("output.txt");

        Loan[] jamesLoans = bank1.find("James", 2000.0);
        System.out.println("\nLoans of James Over 2000: \n");
        for (int i = 0; i < jamesLoans.length; i++) {

            String n = jamesLoans[i].getBorrower().getName();
            int id = jamesLoans[i].getId();
            double amm = jamesLoans[i].getAmmount();

            System.out.println("Loan ID: " + id + " | Loan Ammount: " + amm + "\n");
        }

    }
}