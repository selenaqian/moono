package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the CardBuilder class.
 * @author Tess Noonan (tcn6)
 */
class CardBuilderTest {

    /**
     * Tests that makeFullDeck() returns a full Stack with 80 cards.
     * (This is the only test for this method because it takes no parameters so there are no edge cases)
     */
    @Test
    void makeFullDeck() {
        CardBuilder cb = new CardBuilder();
        Stack<Card> cards = cb.makeFullDeck();
        assertEquals(80, cards.size());

        //the returned Stack is not shuffled so the top Card should be of the greatest Suit/Value
        Card top = cards.peek();
        assertEquals(Suit.D, top.getSuit());
        assertEquals(Value.NINE, top.getValue());
    }
}