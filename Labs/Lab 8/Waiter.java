public class Waiter extends Thread {

    private int id;
    private Kitchen kitchen;

    public Waiter(int id, Kitchen kitchen) {

        this.kitchen = kitchen;
        this.id = id;
        Thread.currentThread().setName("Waiter " + id);
    }

    public void run() {

        while (!isInterrupted()) {
            kitchen.servePizza();

        }
    }
}
