public class PlayingCard {

    enum Rank {
        ACE, KING, QUEEN, KNIGHT, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
    }

    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;
    }

    private final Rank rank;
    private final Suit suit;

    public PlayingCard(Rank r, Suit s) {
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
        return rank + " Of " + suit + ", ";
    }

    public int compareTo(Object o) {

        PlayingCard c = (PlayingCard) o;
        int comparison = this.getSuit().compareTo(c.getSuit());
        if (comparison == 0) {
            return this.getRank().compareTo(c.getRank());
        }
        return comparison;
    }

    public int compareRank(PlayingCard c) {

        return -1 * this.rank.compareTo(c.rank);
    }
}