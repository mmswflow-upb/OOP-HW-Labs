import java.util.*;

public class Deck {
    private ArrayList<PlayingCard> cards = new ArrayList<PlayingCard>();

    public void addCard(PlayingCard card) {

        if (cards.contains(card)) {
            throw new CardDuplicateException("Cannot add the same card into deck.");
        }
        cards.add(card);
    }

    public void sort() {
        int n = cards.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (cards.get(j).compareTo(cards.get(j + 1)) > 0) {
                    // Swap elements at j and j+1
                    PlayingCard temp = cards.get(j);
                    cards.set(j, cards.get(j + 1));
                    cards.set(j + 1, temp);
                }
            }
        }
    }

    public void display() {

        cards.forEach(card -> System.out.println(card.toString()));
    }

    public void shuffle(int shuffles) {
        // This method takes input the number of times we want to shuffle the deck of
        // cards
        Random randGen = new Random();
        int randIndx;

        for (int v = 0; v < shuffles; v++) {
            for (int i = 0; i < cards.size(); i++) {

                randIndx = randGen.nextInt(cards.size());
                PlayingCard temp = cards.get(i);
                cards.set(i, cards.get(randIndx));
                cards.set(randIndx, temp);
            }
        }

    }

    public PlayingCard removeCard(int indx) {

        return cards.remove(indx);
    }

    public PlayingCard getCard(int indx) {
        return cards.get(indx);
    }

    public int numCards() {
        return this.cards.size();
    }
}