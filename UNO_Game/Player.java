import java.util.ArrayList;
import java.util.InputMismatchException;

public class Player{
    private final String name;
    private final Game game;
    private final Hand hand;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.hand = new Hand(new ArrayList<>());
    }

    /**
     * This function does the following:
     * - Attempts to draw num number of cards
     * - If a EmptyDeckException is caught then the play area
     *   must be shuffled into the deck. Note this a function of game class
     * - Adds each drawn card to hand
     * @param num Number of cards to be drawn
     * TODO: Implement this
     */
    public void drawCards(int num){
        for(int i =0; i < num; i++) {
            try {
                hand.addCard(game.getDeck().drawCard());
            }
            catch (Deck.EmptyDeckException deck) {
                game.shufflePlayAreaIntoDeck();
                i--;
            }
        }
    }

    /**
     * Performs IO to figure out what moves the user
     * wants to make. It does this as follows:
     * - Loops until the user has successfully played a card
     * - Prints out "Play area:\n"
     * - Prints out the top card
     * - Checks to see if the hand has any matches against the top card
     *   - If it does not then print: "Your hand had no matches, a card was drawn."
     *   - Then draw 1 card
     * - Then prints "Hand:\n"
     * - Then prints out the hand
     * - If the hand still has no matches then print: "Your hand still has no matches your turn is being passed"
     *   and ends the turn
     * - Otherwise it asks the user: "Which card would you like to play?" using the game::interact function
     * - The code loops until the user successfully answers this question, the three criteria are:
     *   - A valid int, if not print:
     *     "$cardNumStr is not a valid integer, please try again."
     *     where cardNumStr is the user input
     *   - A valid match, if not print:
     *     "Card $cardNumStr cannot currently be played, please try again."
     *     where cardNumStr is the user input
     *   - A valid index, if not print:
     *     "$cardNumStr is not a valid index, please try again."
     * TODO: Implement this
     */
    public void takeTurn() {
        while(true){
            System.out.println("Play area: \n");
            System.out.println(game.getTopCard());
            if(!hand.noMatches(game.getTopCard())){
                System.out.println("Your hand had no matches, a card was drawn.");
                drawCards(1);
            }
            System.out.println("Hand:\n");
            System.out.println(hand);
            if(!hand.noMatches(game.getTopCard())){
                System.out.println("Your hand still has no matches your turn is being passed.");
                return;
            }
            else{
                while(true) {
                    String valid = game.interact("Which card would you like to play?");
                    try {
                        int val = Integer.parseInt(valid);

                        //checking valid integer
                        if (val < 0 || val >= hand.numCardsRemaining()) {
                            System.out.println(valid + " is not a valid index, please try again.");
                        }
                        else {
                            hand.playCard(game, val);
                            return;
                        }
                    }
                    catch (NumberFormatException num) {
                        //valid int
                        System.out.println(valid + " is not a valid integer, please try again.");
                    }
                    catch (Card.CannotPlayCardException cannot) {
                        //no match
                        System.out.println("Card " + valid + " currently cannot be played, please try again.");
                    }
                }
            }
        }
    }

    public boolean emptyHand() {
        return hand.numCardsRemaining() == 0;
    } //used when num of cards in hand is and win

    @Override
    public String toString() {
        return name;
    }
}
