package ooga.view;

import javafx.scene.paint.Color;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CardRenderTest {
    @Test
    void createCardSimpleTest1() {
        Card card1 = new Card(Suit.A, Value.ZERO);

        CardRender card1View = new CardRender(card1, 100, 200);

        assertEquals(0, card1.compareTo(card1View.getCard()));
        assertEquals(100, card1View.getCardViewBase().getWidth());
        assertEquals(200, card1View.getCardViewBase().getHeight());
        assertEquals("A", card1View.getCardViewBase().getStyleClass().get(0));
        assertEquals("0", card1View.getCardViewText().getText());
        assertEquals("AText", card1View.getCardViewText().getStyleClass().get(0));
    }

    @Test
    void createCardSimpleTest2() {
        Card card2 = new Card(Suit.B, Value.TWO);

        CardRender card2View = new CardRender(card2, 100, 200);

        assertEquals(0, card2.compareTo(card2View.getCard()));
        assertEquals(100, card2View.getCardViewBase().getWidth());
        assertEquals(200, card2View.getCardViewBase().getHeight());
        assertEquals("B", card2View.getCardViewBase().getStyleClass().get(0));
        assertEquals("2", card2View.getCardViewText().getText());
        assertEquals("BText", card2View.getCardViewText().getStyleClass().get(0));
    }

    @Test
    void createCardSimpleTest3() {
        Card card3 = new Card(Suit.D, Value.FOUR);

        CardRender card3View = new CardRender(card3, 100, 200);

        assertEquals(0, card3.compareTo(card3View.getCard()));
        assertEquals(100, card3View.getCardViewBase().getWidth());
        assertEquals(200, card3View.getCardViewBase().getHeight());
        assertEquals("D", card3View.getCardViewBase().getStyleClass().get(0));
        assertEquals("4", card3View.getCardViewText().getText());
        assertEquals("DText", card3View.getCardViewText().getStyleClass().get(0));
    }
}