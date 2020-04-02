
/**
 * This class demonstrates the use case:
 * Create and shuffle a card deck
 *      backend builds the correct set of cards, specified by the user via frontend input
 *      randomizes the order of the cards and stores that order
 */
public class UseCase1 {
    public static void main (String[] args){
        //the follow are chunks of code that could be written within the initialize() method in GameControl

        //create a drawPile with the default set of Cards, shuffle
        Deck draw = new DrawPile();
        draw.shuffle();

        //create a discardPile with 1 starter Card, add two new Cards, shuffle
        Deck discard = new DiscardPile(new Card(Color.GREEN, Value.3, Action.NONE));
        List<Card> someCards = new ArrayList<>(Arrays.asList(new Card(Color.RED, Value.2, Action.NONE),
                                                             new Card(Color.BLUE, Value.6, Action.NONE)));
        discard.addCards(someCards);
        discard.shuffle();
    }
}