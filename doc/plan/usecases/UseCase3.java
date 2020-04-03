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
    private HBox humanPlayerHandDisplay;

    /**
     * The following code would appear within a method called when the user clicks a design theme node.
     * It would need to access the selected theme and the cards in the hand (likely passed in as a parameter) and build the card views based on that.
     * There will be a data file that sets the proper color, value, size, etc.
     */
    String theme = welcomeView.getSelectedTheme();

    // This section adds all cards from a player's hand to an HBox for display.
    for (Card c : humanPlayerHand.getAllCards) {
        StackPane cardGrouping = new StackPane; // used to center the rectangle and text
        Rectangle card = new Rectangle(width, height); // where width and height are defined elsewhere
        card.setArcWidth(arcSize); // where arcSize is defined elsewhere
        card.setArcHeight(arcSize);
        card.setFill(c.getSuit); // color will be based on suit but implementation will differ slightly - different method will pull the colors matched to suits from data file
        cardGrouping.add(card);
        Text cardText = new Text();

        if (c.getValue < 10) {
            cardText.setText(c.getValue);
        }
        // also need cases for special cards
        cardGrouping.add(cardText);

        HBox.add(cardGrouping);
    }
}
