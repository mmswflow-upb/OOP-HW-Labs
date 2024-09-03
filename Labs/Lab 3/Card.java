public final class Card implements Comparable {

    enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, KNIGHT, QUEEN, KING;
    }

    enum Suit {

        CLUBS, DIAMONDS, HEARTS, SPADES;
    }

    private final Rank rank;
    private final Suit suit;

    public Card(Rank r, Suit s) {
        this.rank = r;
        this.suit = s;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public String toString() {
        return rank + " Of " + suit + "\n";
    }

    public int compareTo(Object o) {

        Card c = (Card) o;
        int comparison = this.getSuit().compareTo(c.getSuit());
        if (comparison == 0) {
            return this.getRank().compareTo(c.getRank());
        }
        return comparison;
    }
}
