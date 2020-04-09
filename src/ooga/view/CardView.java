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

import static ooga.view.SetupView.DEFAULT_STAGE_WIDTH;

public class CardView extends StackPane {
    private Card myCard;

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
        Rectangle cardViewBase = new Rectangle(width, height);
        cardViewBase.getStyleClass().add(myCard.getSuit().toString());

        Text cardViewText = new Text("" + myCard.getValue().getNumericValue());
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
}
