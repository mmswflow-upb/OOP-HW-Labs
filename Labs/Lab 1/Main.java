
import java.time.LocalDate;
public class Main {

	public static void main(String[] args) {
		
		DebitAccount myAcc = new DebitAccount("abc123", 800);
		Address myAdd = new Address("Bucharest", "Baneasa", 24);
		Client myClient = new Client("Mario", "123CNP", LocalDate.of(2004, 8, 19), myAdd);
		
		myClient.addAccount(myAcc);
		System.out.println(myClient.removeAccount(myAcc.getIBAN()));
		
		myAcc.withdraw(200);
		System.out.println(myAcc.getAmmount());
	}
	
}