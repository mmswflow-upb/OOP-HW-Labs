public class WarGame {

    /*
     * EXPLANATION:
     * 
     * We start with the "game" method, which resets the decks & number of moves for
     * the current game. Then it calls the method "fillDeck_Split", which fills the
     * first deck with all 52 possible cards, shuffles them 3 times,
     * then splits them into 4 stacks for each player.
     * 
     * A while loop starts incrementing the number of moves made and calls the
     * method "move", running until one player loses all their cards.
     * This is checked using a method called "checkLosers" which returns true if a
     * player lost.
     * 
     * The "move" method creates a temporary stack of cards by calling the
     * "drawCards" method, which darws 1 card from each player. Then we check which
     * player has the highest ranking card by using "findMaxCardIndx" which returns
     * the index corresponding to the highest ranking card, after which all cards
     * from the temporary deck are added to the stack of the winning player.
     * If two players have a 'highest ranking' card, then it returns -1, indicating
     * that the players have to start a war, we call the "startWar" method, giving
     * it as an argument the temporary stack of cards.
     * 
     * In the "startWar" method, we check wether any player lost all their cards,
     * then we draw 1 card for each player and we check again, then we draw again,
     * but this time we check which player has the maximum card
     * from among the second set of cards, we check the winner and the rest is the
     * same as the "move" method, basically the "startWar" method is recursive, and
     * it runs as long as players have enough cards or one winner is picked.
     *
     * At the end of each game, we check which player has the most cards, in case
     * two players are supposed to win, we consider it a draw. We compare the
     * current number of moves with the overall min and max number of moves and make
     * proper adjustments.
     * 
     */

    // Overall stats:
    static int minMoves = 10000;
    static int maxMoves = 0;
    static int no_games = 0;
    static int no_moves = 0;
    static int no_draws = 0;
    static double[] players_wins = { 0, 0, 0, 0 };

    // In-game stats:
    static int last_move = 0;// Stores number of moves temporarily for each game

    // The first deck in the array holds all cards at first, then they are split
    // between other players
    static Deck[] decks = { new Deck(), new Deck(), new Deck(), new Deck(), new Deck() };

    // UTILITY METHODS:

    // Fills the initial deck with all 52 cards, then splits them between players
    public static void fillDeck_Split() {

        decks[0] = new Deck();

        // Generating Cards & adding them to totalDeck:

        for (int i = 0; i < 4; i++) {
            for (int v = 0; v < 13; v++) {

                decks[0].addCard(new PlayingCard(PlayingCard.Rank.values()[v], PlayingCard.Suit.values()[i]));
            }
        }

        // Shuffling at least 3 times to make sure they're not in order
        decks[0].shuffle(3);

        // Splitting cards to 4 players
        for (int i = 1; i < 5; i++) {

            for (int v = 0; v < 13; v++) {

                decks[i].addCard(decks[0].removeCard(decks[0].numCards() - 1));

            }
        }

    }

    // Returns either the index of the max card or -1 when two max cards are found
    public static int findMaxCardIndx(Deck tempDeck) {

        PlayingCard maxCard = tempDeck.getCard(0);
        int indx = 0;
        int comparison;

        // Finding first max card
        for (int i = 1; i < 4; i++) {

            comparison = tempDeck.getCard(i).compareRank(maxCard);

            if (comparison > 0) {
                indx = i;
                maxCard = tempDeck.getCard(i);
            }
        }

        // Check if any cards have the same power
        for (int i = 0; i < 4; i++) {

            comparison = tempDeck.getCard(i).compareRank(maxCard);

            if (comparison == 0 && tempDeck.getCard(i) != maxCard) {
                return -1;
            }
        }
        return indx + 1;
    }

    // Merging two decks
    public static Deck mergeDecks(Deck d1, Deck d2) {

        Deck mergedDecks = new Deck();

        int n1 = d1.numCards();
        int n2 = d2.numCards();

        for (int i = 0; i < n1; i++) {
            mergedDecks.addCard(d1.removeCard(0));
        }

        for (int i = 0; i < n2; i++) {
            mergedDecks.addCard(d2.removeCard(0));
        }

        return mergedDecks;
    }

    // Drawing 1 Card from all 4 players
    public static Deck drawCards() {

        Deck tempDeck = new Deck();

        for (int i = 1; i < 5; i++) {// Drawing one card from each player
            tempDeck.addCard(decks[i].removeCard(0));

        }

        return tempDeck;
    }

    // A method used to check if any player has lost all their cards, in order to
    // stop the game.
    public static boolean checkLosers() {

        for (int i = 1; i < 5; i++) {

            if (decks[i].numCards() == 0) {
                System.out.println("Player " + i + " lost all their cards.");
                return true;
            }
        }

        return false;
    }

    // A method that finds the player with most cards
    public static void findGameWinner() {

        int max_no_cards = decks[1].numCards();
        int indx = 1;

        for (int i = 2; i < 5; i++) {

            if (decks[i].numCards() >= max_no_cards) {
                indx = i;
                max_no_cards = decks[i].numCards();
            }
        }
        players_wins[indx - 1]++;

        for (int i = 1; i < indx; i++) {
            if (decks[i].numCards() == max_no_cards) {
                players_wins[indx - 1]--;
                no_draws++;
                break;
            }
        }
    }

    // GAMEPLAY METHODS:

    // Two players or more have the same max rank card, we need to manage this
    // special case by drawing again and again until we get a different result
    public static void startWar(Deck prevDeck) {

        System.out.println("WE GO TO WAR!");

        // One of the players lost all cards before starting the war
        if (checkLosers()) {
            System.out.println("A player lost all cards before drawing for the war.");
            return;
        }

        Deck tempDeck1 = drawCards();

        // One player lost all cards after drawing the first card during the war
        if (checkLosers()) {
            System.out.println("A player lost all cards before drawing a second time for the war.");
            return;
        }

        Deck tempDeck2 = drawCards();

        int tempMaxCardIndx = findMaxCardIndx(tempDeck2);// Index of player with max card ( We check the secondly drawn
        // cards)

        if (tempMaxCardIndx == -1) {// We found two max cards, we must go to war (again)

            tempDeck2 = mergeDecks(mergeDecks(tempDeck2, tempDeck1), prevDeck);
            startWar(tempDeck2);
            return;
        }

        // We add all previously drawn cards to the set of cards of the winner
        System.out.println("Winning Player: " + tempMaxCardIndx);
        decks[tempMaxCardIndx] = mergeDecks(mergeDecks(mergeDecks(tempDeck2, tempDeck1), prevDeck),
                decks[tempMaxCardIndx]);
    }

    // Starting next round
    public static void move() {

        System.out.println("\nMove " + (last_move) + " : ");
        Deck tempDeck = drawCards();
        tempDeck.display();

        int tempMaxCardIndx = findMaxCardIndx(tempDeck);// Index of player with max card

        if (tempMaxCardIndx == -1) {// We found two max cards, we must go to war

            startWar(tempDeck);
            return;

        }

        // Giving all cards to the player with max card
        decks[tempMaxCardIndx] = mergeDecks(decks[tempMaxCardIndx], tempDeck);

    }

    // Game controller
    public static void game() {

        no_games++;
        last_move = 0;
        // Initialize new decks:
        for (int i = 0; i < 5; i++) {
            decks[i] = new Deck();
        }

        // Fill and split deck into 4:
        fillDeck_Split();

        while (!checkLosers()) {

            last_move++;
            move();
        }

        minMoves = Math.min(minMoves, last_move);
        maxMoves = Math.max(maxMoves, last_move);
        no_moves += last_move;
        findGameWinner();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

            game();
        }

        double meanMoves = no_moves / no_games;

        System.out.println("\n\nFINAL RESULTS AFTER RUNNING THE GAME 10 TIMES:  \n\n");
        System.out.println("The minimum number of moves: " + minMoves);
        System.out.println("The maximum number of moves: " + maxMoves);
        System.out.println("The average number of moves: " + meanMoves);
        System.out.println("Tied Games: " + no_draws);

        System.out.println("\nPlayer 1 Wins: " + 100 * players_wins[0] / no_games + "%");
        System.out.println("Player 2 Wins: " + 100 * players_wins[1] / no_games + "%");
        System.out.println("Player 3 Wins: " + 100 * players_wins[2] / no_games + "%");
        System.out.println("Player 4 Wins: " + 100 * players_wins[3] / no_games + "%\n");
    }
}