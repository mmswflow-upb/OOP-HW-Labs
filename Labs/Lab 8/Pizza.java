import java.util.*;

public class Pizza {

    private int numberOfIngerdients;
    private String id;

    Pizza(String id) {
        this.id = id;
        this.numberOfIngerdients = new Random().nextInt(3, 8);
    }

    public int getNumIngredients() {
        return numberOfIngerdients;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "Pizza ID: " + id + " | Ingredients: " + numberOfIngerdients + "\n";
    }

}
