import java.io.*;
import java.util.ArrayList;

public class Hand implements Storable {

    private ArrayList<Card> cards = new ArrayList<Card>();

    public void addCard(Card card) {

        if (cards.contains(card)) {
            throw new CardDuplicateException("Cannot add the same card into deck.");
        }
        cards.add(card);
    }

    public boolean deleteCard(Card.Rank rank, Card.Suit suit) {

        for (Card c : cards) {
            if (c.getRank() == rank && c.getSuit() == suit) {

                cards.remove(c);
                return true;
            }
        }

        return false;
    }

    public void sort() {
        int n = cards.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (cards.get(j).compareTo(cards.get(j + 1)) > 0) {
                    // Swap elements at j and j+1
                    Card temp = cards.get(j);
                    cards.set(j, cards.get(j + 1));
                    cards.set(j + 1, temp);
                }
            }
        }
    }

    public void display() {

        cards.forEach(card -> System.out.println(card.toString()));
    }

    public void store(String fileName) {

        FileWriter fw;
        BufferedWriter bw;

        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);

            bw.write("Cards in my deck: \n");
            bw.write(cards.toString());

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

}