import java.util.Arrays;
import java.util.List;
import java.util.*;
public final class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int numCardsRemaining() {
        return cards.size();
    }

    /**
     * This function does the following:
     * - Gets the current card using index
     * - Plays the current card
     * - Removes the current card from cards
     * @param game State of the game
     * @param index Index of desired card to play in cards
     * @throws Card.CannotPlayCardException
     * TODO: Implement this
     */
    public void playCard(Game game, int index) throws Card.CannotPlayCardException {
        Card current_card = cards.get(index);
        current_card.play(game);
        cards.remove(index);
    }

    /**
     * This function checks to see if your hand has any
     * matches to the given card
     * @param topCard Card currently in play
     * @return true if match is found and false otherwise
     * TODO: Implement this
     */
    public boolean noMatches(Card topCard) {
      for (int i = 0; i < cards.size(); i++){
          if(topCard.match(cards.get(i))){
              return true;
          }
      }
      return false;
    }

    /**
     * Prints out your current hand's cards horizontally.
     * This is accomplished by calling the Card::prettyPrint
     * function on all cards in your hands. The prettyPrint function
     * transforms each card into a List<String> where each element of
     * the list represents a line of output for that card. You must then
     * Append all the first lines together seperated by a space
     * then all the second lines seperated by spaces
     * etc.
     * You must then put an index label under each card that
     * is centered between each card.
     * Then put a new line below
     * Then return the String you constructed
     * For example if your hand consisted of a red reverse and a blue skip
     * then your output would look like:
     * /-------\ /-------\
     * | R |Rev| | B | S |
     * \-------/ \-------/
     *     0         1
     *
     * @return
     * TODO: Implement this
     */
    @Override
    public String toString() {

        List<List<String>> str = new LinkedList<>();
        for(Card each : cards){
            str.add(each.prettyPrint());
        }

        String append = "";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < str.size(); j++){
                append += str.get(j).get(i) + " ";
            }
            append += "\n";
        }
        // to put an index at the center of the card
        for(int i = 0; i < cards.size(); i++){
            append += "    " + i + "     ";
        }
        return append;
    }

    // Code you can use to test your implementation
    public static void main(String[] args) {
        Hand hand = new Hand(Arrays.asList(new Reverse(Card.Color.RED),
                                           new Skip(Card.Color.BLUE)));
        String handStr = hand.toString();
        System.out.println(handStr);
    }
}
