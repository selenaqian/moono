/**
 * This class demonstrates the use case:
 * Choose card design theme - e.g. Duke theme, space theme
 *     - create view of cards with the particular theme
 * This use case focuses on the frontend, allowing users to click on a node or button that would then spur changes in the
 * visual output of the cards.
 */

public class UseCase3 {
    private ViewInterface welcomeView;
    private Hand humanPlayerHand;

    /**
     * The following code would appear within a method called when the user clicks a design theme node.
     * It would need to access the selected theme and the cards in the hand and build the card views based on that.
     */
    String theme = welcomeView.getSelectedTheme();

    for (Card c : humanPlayerHand.getAllCards) {

    }
}
