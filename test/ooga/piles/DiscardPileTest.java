package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the DiscardPile class.
 * @author Tess Noonan (tcn6)
 */
class DiscardPileTest {

    /**
     * Tests showTopCard() with an empty DiscardPile.
     * Makes sure that the correct Card is returned.
     */
    @Test
    void showTopCardNoCards() {
        DiscardPile dp1 = new DiscardPile();
        assertNull(dp1.showTopCard());
    }

    /**
     * Tests showTopCard() with a DiscardPile that only has one Card.
     * Makes sure that the correct Card is returned but not removed from the Pile.
     */
    @Test
    void showTopCardSingleCard() {
        Card card1 = new Card(Suit.A, Value.ONE);
        DiscardPile dp2 = new DiscardPile(card1);
        assertEquals(card1, dp2.showTopCard());
        assertEquals(1, dp2.getCardCount());
    }

    /**
     * Tests showTopCard() with a DiscardPile that has multiple Cards.
     * Makes sure that the correct Card is returned but not removed from the Pile.
     */
    @Test
    void showTopCardMultipleCards() {
        Card card1 = new Card(Suit.A, Value.ONE);
        Card card2 = new Card(Suit.B, Value.THREE);
        Card card3 = new Card(Suit.C, Value.NINE);
        DiscardPile dp2 = new DiscardPile(card1);
        dp2.addCards(Arrays.asList(card2, card3));

        assertEquals(card3, dp2.showTopCard());
        assertEquals(3, dp2.getCardCount());
    }
}