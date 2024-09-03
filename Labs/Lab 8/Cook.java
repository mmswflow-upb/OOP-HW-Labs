public class Cook extends Thread {

    private Kitchen kitchen;
    private int id;
    private int count = 0;

    public Cook(int id, Kitchen kitchen) {

        this.kitchen = kitchen;
        this.id = id;
        Thread.currentThread().setName("Cook" + id);

    }

    public void run() {

        while (!isInterrupted()) {

            makePizza();
        }
    }

    public void makePizza() {

        kitchen.addPizza(new Pizza(String.valueOf(id) + String.valueOf(count++)));
    }
}
