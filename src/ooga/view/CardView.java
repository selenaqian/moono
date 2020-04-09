package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import ooga.cards.Card;

public class CardView extends StackPane {
    private Card myCard;

    protected CardView(Card c, double width, double height) {
        myCard = c;

        Rectangle cardViewBase = new Rectangle(width, height);
        cardViewBase.getStyleClass().add(myCard.getSuit().toString());

        Text cardViewText = new Text("" + myCard.getValue().getNumericValue());
        cardViewText.getStyleClass().add("cardText");

        this.getChildren().addAll(cardViewBase, cardViewText);
        this.setAlignment(Pos.CENTER);
    }

    public Card getCard() {
        return myCard;
    }
}
