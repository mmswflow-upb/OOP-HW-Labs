import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Account implements Comparable, Storable {

    private String iban;
    private int ammount;

    Account(String newIban, int newAmmount) {
        this.iban = newIban;
        this.ammount = newAmmount;
    }

    public abstract boolean withdraw(int ammount);

    public void deposit(int ammount) {

        this.ammount += ammount;
    }

    public String getIBAN() {

        return this.iban;
    }

    public int getAmmount() {
        return this.ammount;
    }

    public void setAmmount(int new_ammount) {
        this.ammount = new_ammount;
    }

    public abstract String toString();

    public int compareTo(Object o) {

        int AFTER = 1;
        int BEFORE = -1;
        int EQUAL = 0;

        if (this == o)
            return EQUAL;

        Account acc = (Account) o;

        int res = this.getIBAN().compareTo(acc.getIBAN());

        if (res > 0) {
            return AFTER;
        } else if (res < 0) {
            return BEFORE;
        }

        return EQUAL;
    }

    public void store(String fileName) {

        try {

            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append(toString());

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
