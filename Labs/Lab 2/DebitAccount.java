import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DebitAccount extends Account {

	public DebitAccount(String iban, int ammount) {
		super(iban, ammount);
	}

	public boolean withdraw(int ammount) {
		if (ammount <= super.getAmmount()) {
			super.setAmmount(super.getAmmount() - ammount);
			return true;
		}
		return false;
	}

	public String toString() {

		return ("Account Type: Debit Account | IBAN: " + this.getIBAN() + " | Ammount: " + this.getAmmount() + "\n");
	}

}
