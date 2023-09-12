import com.sun.jdi.Value;

import javax.naming.Name;
import java.util.*;

public final class Game {
    private final Scanner io;
    private final UnusIterator<Player> players;
    private final int numPlayers;
    private final Deck deck;
    private final Deque<Card> playArea;

    /**
     * Constructs all the data necessary to run a game.
     * This includes the following:
     * - Create a scanner of System.in and saves it into io
     * - Creates a deck using the createDeck function and saves it into deck.
     * - Creates a list of players with length given by numPlayers
     * - Has each player draw 5 cards
     * - Creates a UnusIterator with the aforementioned player list
     * - Assigns the parameter numPlayers to the instance variable numPlayers
     * - Initializes playArea with a new ArrayDeque
     * @param numPlayers
     * TODO: Implement this
     */
    public Game(int numPlayers) {

        /**
         * This is not complete I think. I am not sure though
         */
        //Create a scanner of System.in and saves it into io
        Scanner sc = new Scanner(System.in);
        this.io = sc;

        //Creates a deck using the createDeck function and saves it into deck.
        this.deck = createDeck();

        //Creates a list of players with length given by numPlayers
        List<Player> players = new ArrayList<>(numPlayers);
        //Has each player draw 5 cards
            for(int i = 0; i < numPlayers; i++){
                players.add(new Player(Integer.toString(i), this));
                players.get(i).drawCards(5);
            }
        this.players = new UnusIterator<>(players);
        this.numPlayers = numPlayers;
        this.playArea = new ArrayDeque<>();
    }

    /**
     * The main game loop function.
     * Does the following:
     * - Loops until the curPlayer's hand is empty
     *   - When this is the case the curPlayer has won the game.
     *   - It then prints: "$curPlayer won!"
     * - The current player is received from the UnusIterator
     * - The player then takes their turn
     * - The UnusIterator is then moved to the next player
     * TODO: Implement this
     */
    public void start() {
         do {
             players.current().takeTurn();
            // Loops until the curplayer's hand is empty
            if(players.current().emptyHand()){
                break;
            }
            players.next();
        }
         while (true);
        System.out.println(players.current() + " won!");
    }
    public String interact(String toUser) {
        System.out.println(toUser);
        return io.nextLine();
    }

    public UnusIterator<Player> getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumberOfCardsInPlay() {
        return playArea.size();
    }

    public Deck getDeck() {
        return deck;
    }// returns the deck of the card

    public Card getTopCard() { // this method gets the top card from the deck
        if (playArea.isEmpty()) {
            return new None();
        }

        return playArea.getFirst();
    }

    public void playCard(Card card) {
        playArea.addFirst(card);
    } // this method helps to pla

    public void shufflePlayAreaIntoDeck() {// used when all cards from the deck is used and to shuffle it
        deck.addCards(playArea);
        deck.shuffleDeck();
    }

    /**
     * Creates the standard 108 card Unus deck.
     * The deck contains the following cards:
     * - 19 red cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 blue cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 green cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 yellow cards
     *   - 1 zero
     *   - 2 of every number
     * - 8 skip cards - two of each color
     * - 8 reverse cards - two of each color
     * - 8 draw 2 cards - two of each color
     * - 4 wild cards
     * - 4 wild draw 4 cards
     * @return A standard Unus deck of 108 cards
     * TODO: Implement this
     */
    private Deck createDeck() {

        final int total_cards_in_deck = 108;
        List<Card> cards = new ArrayList<>(total_cards_in_deck);
        Card.Color[] colors = Card.Color.values();

        int n = 0;
        // this is to store value zero for every colors
        for(int i = 0; i < colors.length -2; i++){
            Numbers Zero_for_colors = new Numbers(colors[i], n);
            cards.add(Zero_for_colors);
        }

        // this is to store value from 1 to 9 in every colors
        for(int i = 1; i < 10; i++) {
            for(int j = 0; j < Card.Color.values().length-2; j++) {
                Numbers values_for_colors = new Numbers(colors[j], i);
                cards.add(values_for_colors);
            }
    }
        // again storing the same value because it has two values from 1 to 9 in every colors
        for(int i = 1; i < 10; i++) {
            for(int j = 0; j < Card.Color.values().length-2; j++) {
                Numbers values_for_colors = new Numbers(colors[j], i);
                cards.add(values_for_colors);
            }
        }
    // for skip cards to be added
        for(int i = 0; i < colors.length -2; i++){
            Skip skip = new Skip(colors[i]);
            cards.add(skip);
            cards.add(skip);
        }
        // repeating cause we need two of each colors
        for(int i = 0; i < colors.length -2; i++){
            Skip skip = new Skip(colors[i]);
            cards.add(skip);
            cards.add(skip);
        }
    // for reverse cards to be added
        for(int i = 0; i < colors.length -2; i++){
            Reverse reverse = new Reverse(colors[i]);
            cards.add(reverse);
            cards.add(reverse);
        }
        //repeating cause we need two
        for(int i = 0; i < colors.length -2; i++){
            Reverse reverse = new Reverse(colors[i]);
            cards.add(reverse);
            cards.add(reverse);
        }
    // for 8 draw 2 cards, two of each colors
        for(int i = 0; i < colors.length -2; i++){
            DrawN draw2 = new DrawN(colors[i], 2);
            cards.add(draw2);
        }
        //repeating cause we need two of each colors
        for(int i = 0; i < colors.length -2; i++){
            DrawN draw2 = new DrawN(colors[i], 2);
            cards.add(draw2);
        }
    // for 4 wild cards
        for(int i = 0; i < 4; i++){
            Wild wild = new Wild(colors[colors.length - 1]);
            cards.add(wild);
        }
    // for 4 wild draw 4 cards
        for(int i = 0; i < 4; i ++){
            DrawN wild_draw4 = new DrawN(colors[colors.length-1], 4);
            cards.add(wild_draw4);
        }
        Deck complete_deck = new Deck(cards);
        return complete_deck;
    }
}
