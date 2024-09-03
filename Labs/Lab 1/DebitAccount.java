
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
}
