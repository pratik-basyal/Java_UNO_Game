import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class Skip extends Card {

    public Skip(Color cardColor) {
        super(cardColor);
    }

    /**
     * Skip can skip any player except for the player who played it.
     * This function accomplishes the following:
     * - Prompts the user who they would like to skip with the following message:
     *   "Who would you like to skip? (n)ext or (s)pecific user?"
     * - If the answer is "n" then the next player is skipped
     * - If the answer is "s" then a specific player is skipped
     *   - The user must then be prompted with the following prompt:
     *     "Please choose from the following numbers: $playerNumbers"
     *     where playerNumbers are all the indices of players other than the current player seperated by spaces
     *   - You must loop until they give a valid index, if they fail output the following message:
     *     "$playerNumber is not valid. $playersToChoose"
     *     where playerNumber is the number they input
     *   - If they give an index that is not a number then output the following message and loop again:
     *     "$n not an int, please try again."
     *     where n is the index they input
     * - You must loop until they give you a valid command, if they fail output the following message:
     *   "$answer is not a recognized command, please try again."
     * @param game
     * TODO: Implement this
     */
    @Override
    public void doAction(Game game) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Who would you like to skip? (n)ext or (s)pecific user?");
        String input = sc.next();
        while (true) {
            if (input.equals("n")) {
                game.getPlayers().skip(game.getPlayers().findNextIndex(false));
                break;
            }
            else if (input.equals("s")) {
                System.out.println("Please choose from the following numbers: ");
                ArrayList<Integer> arr = new ArrayList<>();
                while (true) {
                    for (int i = 0; i < game.getNumPlayers(); i++) {
                        if (i != game.getPlayers().getCurIndex()) {
                            arr.add(i);
                            System.out.print(i + " ");
                        }
                    }
                    System.out.println();
                    String str = "";
                    while (true) {
                        try {
                            str = sc.next();
                            int conversion = Integer.parseInt(str);
                            for(int i = 0; i < arr.size(); i++) {
                                if (conversion == i) {
                                    game.getPlayers().skip(i);
                                    return;
                                }
                            }
                            if(!arr.contains(conversion)){
                                System.out.println(conversion + " is not valid. " + arr);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(str + " is not an int, please try again.");
                        }
                    }
                }
            }
            else {
                System.out.println(input + " is not a valid command, please try again.");
                input = sc.next();
            }
        }
    }
    @Override
    public boolean matchValue(Card other) {
        return other instanceof Skip;
    } // matching the instance of skip

    @Override
    public String strRep() {
        return "S";
    } // used to represent the card to skip
}
