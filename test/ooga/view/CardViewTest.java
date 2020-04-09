package ooga.view;

import javafx.scene.paint.Color;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardViewTest {
    @Test
    void createCardSimpleTest() {
        Card card1 = new Card(Suit.A, Value.ZERO);

        CardView card1View = new CardView(card1, 100, 200);

        assertEquals(0, card1.compareTo(card1View.getCard()));
        assertEquals(100, card1View.getCardViewBase().getWidth());
        assertEquals(200, card1View.getCardViewBase().getHeight());
        assertEquals(Color.RED, card1View.getCardViewBase().getFill());
        assertEquals("0", card1View.getCardViewText().getText());
        assertEquals(Color.WHITE, card1View.getCardViewText().getFill());
    }
}