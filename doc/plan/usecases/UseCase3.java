/**
 * This class demonstrates the use case:
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