public class Wild extends Card {

    public Wild(Color cardColor) {
        super(cardColor);
    } // used to create Wild card with proper string representation and others

    @Override
    public void doAction(Game game) {}

    @Override
    public boolean matchValue(Card other) {
        return true;
    }

    @Override
    public String strRep() {
        return "W";
    }
}
