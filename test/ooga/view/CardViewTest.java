package ooga.view;

import javafx.scene.paint.Color;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardViewTest {
    @Test
    void createCardSimpleTest1() {
        Card card1 = new Card(Suit.A, Value.ZERO);

        CardView card1View = new CardView(card1, 100, 200);

        assertEquals(0, card1.compareTo(card1View.getCard()));
        assertEquals(100, card1View.getCardViewBase().getWidth());
        assertEquals(200, card1View.getCardViewBase().getHeight());
        assertEquals(Color.RED, card1View.getCardViewBase().getFill());
        assertEquals("0", card1View.getCardViewText().getText());
        assertEquals(Color.WHITE, card1View.getCardViewText().getFill());
    }

    @Test
    void createCardSimpleTest2() {
        Card card2 = new Card(Suit.B, Value.TWO);

        CardView card2View = new CardView(card2, 100, 200);

        assertEquals(0, card2.compareTo(card2View.getCard()));
        assertEquals(100, card2View.getCardViewBase().getWidth());
        assertEquals(200, card2View.getCardViewBase().getHeight());
        assertEquals(Color.BLUE, card2View.getCardViewBase().getFill());
        assertEquals("2", card2View.getCardViewText().getText());
        assertEquals(Color.WHITE, card2View.getCardViewText().getFill());
    }

    @Test
    void createCardSimpleTest3() {
        Card card3 = new Card(Suit.D, Value.FOUR);

        CardView card3View = new CardView(card3, 100, 200);

        assertEquals(0, card3.compareTo(card3View.getCard()));
        assertEquals(100, card3View.getCardViewBase().getWidth());
        assertEquals(200, card3View.getCardViewBase().getHeight());
        assertEquals(Color.YELLOW, card3View.getCardViewBase().getFill());
        assertEquals("4", card3View.getCardViewText().getText());
        assertEquals(Color.BLACK, card3View.getCardViewText().getFill());
    }
}