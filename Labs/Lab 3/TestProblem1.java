
public class TestProblem1 {

    public static void main(String[] args) {

        Hand myHand = new Hand();

        Card c1 = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        Card c2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card c3 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS);

        Card c4 = new Card(Card.Rank.THREE, Card.Suit.HEARTS);
        try {

            myHand.addCard(c3);
            myHand.addCard(c4);
            myHand.addCard(c2);
            myHand.addCard(c1);
            // myHand.addCard(c4);

            myHand.display();

            if (myHand.deleteCard(Card.Rank.ACE, Card.Suit.HEARTS)) {

                System.out.println("Ace of Hearts, Found and Deleted");
            }

            myHand.sort();

            myHand.store("deck.txt");

        } catch (CardDuplicateException e) {
            System.out.println(e.getMessage());
        }

    }

}
