import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		Account[] accounts = { new CreditAccount("a", 1000, 1000), new DebitAccount("b", 1000),
				new DebitAccount("a", 1000),
				new CreditAccount("b", 9000, 1),
				new DebitAccount("c", 200) };

		Arrays.sort(accounts);

		for (Account a : accounts) {

			a.store("accountDetails.txt");
		}
	}

}