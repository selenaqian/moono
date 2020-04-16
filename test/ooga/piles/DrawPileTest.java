package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the DrawPile class.
 * @author Tess Noonan (tcn6)
 */
class DrawPileTest {

    /**
     * Tests that the default DrawPile constructor has the standard Deck of 80 Cards.
     */
    @Test
    void defaultContructor() {
        DrawPile dp = new DrawPile();
        assertEquals(80, dp.getCardCount());
    }

    /**
     * Tests that when passed special Values, the constructor makes the extended Deck of 100 Cards.
     */
    @Test
    void specialConstructor() {
        DrawPile dp = new DrawPile(Arrays.asList(Value.values()));
        assertEquals(100, dp.getCardCount());
    }

    /**
     * Tests drawCard() when the DrawPile is empty.
     * Makes sure it returns null.
     */
    @Test
    void drawCardEmpty() {
        DrawPile draw = new DrawPile(new Stack<Card>());
        assertNull(draw.drawCard());
    }

    /**
     * Tests drawCard() when the DrawPile is populated.
     * Makes sure it returns the top Card and removes it from the Pile.
     */
    @Test
    void drawCardPopulated() {
        Card card1 = new Card(Suit.B, Value.NINE);
        Card card2 = new Card(Suit.A, Value.FOUR);
        DrawPile draw = new DrawPile(new Stack<Card>());
        draw.addCards(Arrays.asList(card1, card2));

        assertEquals(2, draw.getCardCount());
        assertEquals(card2, draw.drawCard());
        assertEquals(1, draw.getCardCount());
        assertEquals(card1, draw.drawCard());
        assertEquals(0, draw.getCardCount());
    }

    /**
     * Tests drawCard() when the DrawPile is empty.
     * Makes sure it returns an empty List.
     */
    @Test
    void drawCardsEmpty() {
        DrawPile draw = new DrawPile(new Stack<Card>());
        assertEquals(new ArrayList<Card>(), draw.drawCards(3));
    }

    /**
     * Tests drawCards() when the DrawPile is populated.
     * Makes sure it returns the top x specified Cards and removes them from the Pile.
     */
    @Test
    void drawCardsPopulated() {
        Card card1 = new Card(Suit.B, Value.NINE);
        Card card2 = new Card(Suit.A, Value.FOUR);
        Card card3 = new Card(Suit.D, Value.SIX);
        DrawPile draw = new DrawPile(new Stack<Card>());
        draw.addCards(Arrays.asList(card1, card2, card3));

        assertEquals(3, draw.getCardCount());
        assertEquals(new ArrayList<>(Arrays.asList(card3, card2)), draw.drawCards(2));
        assertEquals(1, draw.getCardCount());
        assertEquals(new ArrayList<>(Arrays.asList(card1)), draw.drawCards(1));
        assertEquals(0, draw.getCardCount());
    }
}