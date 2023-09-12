import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) { // to declare the list of cards and be used in other methods
        this.cards = cards;
        shuffleDeck();
    }

    public static class EmptyDeckException extends Exception {}

    /**
     * This function does the following:
     * - Checks if cards is empty
     *   - If it is then throw a new EmptyDeckException
     *   - If not then return and remove the first card in cards
     * @return The top card from the deck
     * @throws EmptyDeckException
     * TODO: Implement this
     */
    public Card drawCard() throws EmptyDeckException {
        if(cards.isEmpty()){
            throw new EmptyDeckException();
        }
        cards.remove(0);
        return cards.get(0);
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    } // to shuffle the cards after theres nothing left

    public void addCards(Collection<Card> cards) { // simply to add cards
        this.cards.addAll(cards);
        shuffleDeck();
    }
}
