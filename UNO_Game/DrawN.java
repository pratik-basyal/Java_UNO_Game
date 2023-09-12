public class DrawN extends Card {
    private final int n;

    public DrawN(Color cardColor, int n) { // draws specific card and numbers
        super(cardColor);
        this.n = n;
    }

    public int getN() {
        return n;
    }

    /**
     * Makes the next player draw n cards
     * @param game Current game state
     * TODO: Implement this
     */
    @Override
    public void doAction(Game game){
        game.getPlayers().peekNext().drawCards(n);
    }

    /**
     * Checks if other has the same value as this
     * @param other Other card to match against
     * @return true if other is an instanceof DrawN and our n equals their n, false otherwise
     * TODO: Implement this
     */
    @Override
    public boolean matchValue(Card other) {
            if(other instanceof DrawN && getN() == ((DrawN) other).getN()){
                return true;
            }
        return false;
    }

    @Override
    public String strRep() {
        return "D+" + n;
    } // is used for the representation of draw card
}
