/**
 * This class demonstrates the use case:
<<<<<<< HEAD
 * dealing a hand of cards
 *
 */

public class UseCase3 {
    private List<Card> deck;
    private DrawPile drawPile = new DrawPile();
    private int numcardsperhand;

    /**
     *
     * @param playernum number of players as chosen by manual player
     * @param drawpile full deck of cards
     * @return a list of lists of each player's cards
     */
    List<List<Card>> dealhands(int playernum, List<Card> deck){
        List<List<Card>> handsforallplayers = new ArrayList();
        while(playernum>0){
            handsforallplayers.add(drawPile.drawcards(numcardsperhand));
            playernum-=1;
            }
        }

    }

}
=======
 * Choose card design theme - e.g. Duke theme, space theme
 *     - create view of cards with the particular theme
 * This use case focuses on the frontend, allowing users to click on a node or button that would then spur changes in the
 * visual output of the cards.
 */

public class UseCase3 {
    // The following code would appear within a method called when the node is clicked
}
>>>>>>> c5716c33bf66ce459f89dd8ffc76076c5d63dc51
