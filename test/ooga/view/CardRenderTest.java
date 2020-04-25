/**
 * Tests CardRender class.
 *
 * @author Selena Qian
 */

package ooga.view;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardRenderTest {
    /**
     * Tests creation of a simple numerical card.
     */
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

    /**
     * Tests creation of a simple numerical card.
     */
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

    /**
     * Tests creation of a simple numerical card.
     */
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

    /***
     * Tests creation of a special card (non-numerical) and a method updating it to a numerical card.
     * Used to change the visual of the discard pile in the game.
     */
    @Test
    void updateCardRenderTest() {
        Card original = new Card(Suit.C, Value.SKIP);
        CardRender cardView = new CardRender(original, 100, 200);

        assertEquals(0, original.compareTo(cardView.getCard()));
        assertEquals(100, cardView.getCardViewBase().getWidth());
        assertEquals(200, cardView.getCardViewBase().getHeight());
        assertEquals("C", cardView.getCardViewBase().getStyleClass().get(0));
        assertEquals("SKIP", cardView.getCardViewText().getText());
        assertEquals("CText", cardView.getCardViewText().getStyleClass().get(0));

        Card replacement = new Card(Suit.A, Value.ZERO);
        cardView.updateCardRender(replacement);

        assertEquals(0, replacement.compareTo(cardView.getCard()));
        assertEquals(100, cardView.getCardViewBase().getWidth());
        assertEquals(200, cardView.getCardViewBase().getHeight());
        assertEquals("A", cardView.getCardViewBase().getStyleClass().get(0));
        assertEquals("0", cardView.getCardViewText().getText());
        assertEquals("AText", cardView.getCardViewText().getStyleClass().get(0));
    }
}