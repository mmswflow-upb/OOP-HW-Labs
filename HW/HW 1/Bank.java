import java.util.Arrays;
import java.io.*;

public class Bank {

    private Loan[] loans = new Loan[100];
    private int no_loans;

    Bank() {
        no_loans = 0;
    }

    public void addLoan(Loan l) {

        if (no_loans < 100) {

            loans[no_loans++] = l;
        }
    }

    public boolean remove(Loan l) {

        if (no_loans >= 0) {

            for (int i = 0; i < no_loans; i++) {

                if (loans[i].getId() == l.getId()) {

                    // Shift everything to the left:
                    this.no_loans--;
                    for (int v = i; v < no_loans; v++) {
                        loans[v] = loans[v + 1];
                    }
                    loans[no_loans] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public Loan[] find(String name) {

        Loan[] foundLoans = new Loan[0];

        for (int i = 0; i < no_loans; i++) {

            if (loans[i].getBorrower().getName().equals(name)) {

                foundLoans = Arrays.copyOf(foundLoans, foundLoans.length + 1);
                foundLoans[foundLoans.length - 1] = loans[i];
            }
        }

        return foundLoans;
    }

    public Loan[] find(String name, double minAmmount) {

        Loan[] foundLoans = new Loan[0];

        for (int i = 0; i < no_loans; i++) {

            if (loans[i].getBorrower().getName().equals(name) && loans[i].getAmmount() >= minAmmount) {

                foundLoans = Arrays.copyOf(foundLoans, foundLoans.length + 1);
                foundLoans[foundLoans.length - 1] = loans[i];
            }
        }

        return foundLoans;
    }

    public void printInFile(String fileName) {

        try {

            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < this.no_loans; i++) {
                String line = "Loan ID: " + this.loans[i].getId() + " | Loan Ammount: " + this.loans[i].getAmmount()
                        + " | Borrower Name: " + this.loans[i].getBorrower().getName() + "\n";
                bw.write(line);
            }
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}