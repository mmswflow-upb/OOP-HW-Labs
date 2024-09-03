import java.util.*;

public class Kitchen {

    private LinkedList<Pizza> pizzaList;
    private int LIMIT;

    Kitchen(int LIMIT) {
        pizzaList = new LinkedList<>();
        this.LIMIT = LIMIT;
    }

    public synchronized void addPizza(Pizza newPizza) {

        try {
            while (pizzaList.size() == LIMIT) {
                wait();
                System.out.println("PIZZA LIST IS FULL.");
            }
            pizzaList.add(newPizza);
            System.out.println("\nPizza " + newPizza.getId() + " was added.");
            Thread.sleep(5 + 2 * newPizza.getNumIngredients());

        } catch (InterruptedException exec) {
            exec.printStackTrace();
        } finally {

            notifyAll();
        }

    }

    public synchronized void servePizza() {
        try {
            while (pizzaList.size() == 0) {
                wait();
                System.out.println("PIZZA LIST IS EMPTY");
            }
            Pizza removedPizza = pizzaList.removeFirst();
            System.out.println("\nPizza " + removedPizza.getId() + " was removed.");
            Thread.sleep(10);

        } catch (InterruptedException exec) {
            exec.printStackTrace();
        } finally {

            notifyAll();
        }
    }

    public static void main(String[] args) {

        Kitchen kitchen = new Kitchen(10);

        Cook c1 = new Cook(1, kitchen);
        Cook c2 = new Cook(2, kitchen);
        Cook c3 = new Cook(3, kitchen);

        Waiter w1 = new Waiter(1, kitchen);
        Waiter w2 = new Waiter(2, kitchen);
        Waiter w3 = new Waiter(3, kitchen);
        Waiter w4 = new Waiter(4, kitchen);

        c1.start();
        c2.start();
        c3.start();

        w1.start();
        w2.start();
        w3.start();
        w4.start();

    }
}
