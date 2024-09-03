class Account {

    private String IBAN;
    private String name;
    private Double balance;

    Account(String IBAN, String name, Double balance) {

        this.balance = balance;
        this.IBAN = IBAN;
        this.name = name;
    }

    public synchronized void deposit(Double s) {

        System.out.println(Thread.currentThread().getName() + " : Depositing: " + s);
        balance += s;
    }

    public synchronized void withdraw(Double s) {
        System.out.println(Thread.currentThread().getName() + " : Withdrawing: " + s);
        balance -= s;
    }

    public synchronized void displayBalance() {

        System.out.println("Current Balance: " + balance + " $\n");
    }

}

public class Exercise2 {

    public static void main(String[] args) {
        Account myAccount = new Account("ABCD", "Mario", 0.0);

        Thread t1 = new Thread() {

            public void run() {
                myAccount.deposit(50.0);
                myAccount.displayBalance();

            }
        };

        Thread t2 = new Thread() {
            public void run() {
                myAccount.withdraw(50.0);
                myAccount.displayBalance();

            }
        };

        Thread t3 = new Thread() {
            public void run() {
                myAccount.withdraw(100.0);
                myAccount.displayBalance();

            }
        };

        Thread t4 = new Thread() {
            public void run() {
                myAccount.deposit(100.0);
                myAccount.displayBalance();

            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
