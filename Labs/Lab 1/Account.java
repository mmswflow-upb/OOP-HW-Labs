
public abstract class Account {

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
}
