/**
 * Helper class used within view to render cards and store information about them.
 * Decided to make this extend StackPane because our cards will always be StackPanes,
 * with a base Shape and a Text object.
 *
 * @author Selena Qian
 */
package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.cards.Card;

public class CardView extends StackPane {
    private Card myCard;
    private Rectangle cardViewBase;
    private Text cardViewText;

    CardView(Card c, double width, double height) {
        myCard = c;
        renderCard(width, height);
    }

    /**
     * Helper method to render the visual of this StackPane.
     * @param width the width of the rectangle for the card.
     * @param height the height of the rectangle for the card.
     */
    private void renderCard(double width, double height) {
        cardViewBase = new Rectangle(width, height);
        cardViewBase.getStyleClass().add(myCard.getSuit().toString());

        cardViewText = new Text("" + myCard.getValue().getNumericValue());
        cardViewText.getStyleClass().add(myCard.getSuit() + "Text");

        this.getChildren().addAll(cardViewBase, cardViewText);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Allows other classes in the view package to access what card this CardView is tied to.
     * Useful for playing card action and updating styling.
     * @return the card instance variable for this object.
     */
    Card getCard() {
        return myCard;
    }

    // Methods below used for testing.

    /**
     * Used for testing. Allows test to access the Rectangle of the CardView rendering for comparison to expected color/image, size.
     * @return the Rectangle object rendered by this CardView.
     */
    public Rectangle getCardViewBase() {
        return cardViewBase;
    }

    /**
     * Used for testing. Allows test to access the Text of the CardView rendering for comparison to expected String.
     * @return the Text object rendered by this CardView.
     */
    public Text getCardViewText() {
        return cardViewText;
    }
}
