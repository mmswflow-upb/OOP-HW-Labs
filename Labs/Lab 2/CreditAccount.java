import java.io.*;

public class CreditAccount extends Account {

	private int maxCredit;

	public CreditAccount(String iban, int ammount, int new_maxCredit) {
		super(iban, ammount);
		this.maxCredit = new_maxCredit;
	}

	public boolean withdraw(int ammount) {

		if (ammount <= super.getAmmount()) {
			super.setAmmount(super.getAmmount() - ammount);
			return true;
		} else if (ammount >= super.getAmmount() && ammount <= super.getAmmount() + maxCredit) {

			int newAmmount = super.getAmmount() - (ammount - this.maxCredit);
			super.setAmmount(newAmmount);
			this.maxCredit -= (ammount - newAmmount);
			return true;
		}

		return false;
	}

	public String toString() {

		return ("Account Type: Credit Account | IBAN: " + this.getIBAN() + " | Ammount: " + this.getAmmount()
				+ " | MaxCredit: " + maxCredit + "\n");
	}

}
