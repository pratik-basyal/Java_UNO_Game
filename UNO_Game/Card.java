import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Card {
    private final Color cardColor;

    public enum Color{
        RED("R"), YELLOW("Y"), BLUE("B"), GREEN("G"), WILD("W");

        private final String strRep;

        Color(String strRep) {
            this.strRep = strRep;
        }

        public boolean match(Color other) {
            return this == WILD || other == WILD || this == other;
        }

        @Override
        public String toString() {
            return strRep;
        }
    }

    public Card(Color cardColor) {
        this.cardColor = cardColor;
    }

    public Color getCardColor() {
        return cardColor;
    }

    public static class CannotPlayCardException extends Exception {}

    public abstract void doAction(Game game);
    public abstract boolean matchValue(Card other);
    public abstract String strRep();

    /**
     * Matches two cards according to Unus rules which state that two cards
     * match if they have the same color or the same symbol/numeric value
     * @param other Card to match this card against
     * @return true if the two cards match and false otherwise
     * TODO: Implement this
     */

    public boolean match(Card other) {
        //matching numbers and color of the cards
        return (strRep().equals(other.strRep()) || cardColor.match(other.cardColor));
    }

    public void play(Game game) throws CannotPlayCardException {
        if (!match(game.getTopCard())) {
            throw new CannotPlayCardException();
        }
        doAction(game);
        game.playCard(this);
    }

    /**
     * This function does the following:
     * - Creates a List<String>
     * - Adds the top part of the card to this list which is the following string:
     *   "/-------\\"
     * - Then constructs the middle of the card which depends on the length of the strRep()
     *   of the current card. If it is length 3 the following string should be constructed and added to the list:
     *   "| $getCardColor() |$strRep()|"
     *   Otherwise if it is length 1 then the following string should be constructed and added to the list:
     *   "| $getCardColor() | $strRep() |"
     * - Then the bottom part of the card is added to the list which is given by the following string:
     *   "\\-------/"
     * - This list is then returned
     * @return List of lines of this card
     * TODO: Implement this
     */
    public List<String> prettyPrint() {
        LinkedList<String> creation = new LinkedList<>();
        creation.addFirst("/-------\\"); // first part

        // middle part left to be finished
        // constructing all the strings required for the different condition
        String for3 = "| " +  getCardColor() + " |" + strRep() + "|";
        String for1 = "| " + getCardColor() + " | " + strRep() + " |";

        if(strRep().length() == 3){
            creation.add(for3);
        }

        if(strRep().length() == 1){
            creation.add(for1);
        }

        creation.addLast("\\-------/"); // last part

        //return the same list
        return creation;
    }

    @Override
    public final String toString() { // helps returning the appended string
        StringBuilder sb = new StringBuilder();

        for (String line : prettyPrint()) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }
}
