
import java.time.LocalDate;

public class Client {

    private Address add;
    private String name;
    private LocalDate bdate;
    private String cnp;
    private Account[] accounts = new Account[10];
    private int noAcc = 0;

    Client(String clientName, String client_CNP, LocalDate client_bDate, Address clientAdd) {
        this.add = clientAdd;
        this.name = clientName;
        this.bdate = client_bDate;
        this.cnp = client_CNP;
    }


    
    public void addAccount(Account a) {

    	accounts[noAcc++] = a;
    }

    public boolean removeAccount(String iban) {
    	
    	for (int i = 0; i < noAcc; i++) {
            if (accounts[i].getIBAN().equals(iban)) {
                accounts[i]=accounts[noAcc-1];
                noAcc--;
              return true;
           }
    	}
        return false;
    }

}
