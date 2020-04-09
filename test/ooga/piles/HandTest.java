package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests the Hand class.
 * @author Tess Noonan (tcn6)
 */
class HandTest {

    private Card card1 = new Card(Suit.A, Value.ONE);
    private Card card2 = new Card(Suit.A, Value.EIGHT);
    private Card card3 = new Card(Suit.B, Value.ONE);
    private Hand h1;
    private Hand emptyHand;

    @BeforeEach
    void setUp() {
        h1 = new Hand(Arrays.asList(card1, card2, card3));
        emptyHand = new Hand();
    }

    /**
     * Tests numSuit() when there are no Cards of that Suit.
     */
    @Test
    void numSuitNone() {
        assertEquals(0, h1.numSuit(Suit.C));
        assertEquals(0, emptyHand.numSuit(Suit.C));
    }

    /**
     * Tests numSuit() when there is at least one Card of that Suit.
     */
    @Test
    void numSuitSome() {
        assertEquals(2, h1.numSuit(Suit.A));
        assertEquals(1, h1.numSuit(Suit.B));
    }

    /**
     * Tests numValue() when there are no Cards of that Value.
     */
    @Test
    void numValueNone() {
        assertEquals(0, h1.numValue(Value.FIVE));
        assertEquals(0, emptyHand.numValue(Value.FOUR));
    }

    /**
     * Tests numValue() when there is at least one Card of that Value.
     */
    @Test
    void numValueSome() {
        assertEquals(2, h1.numValue(Value.ONE));
        assertEquals(1, h1.numValue(Value.EIGHT));
    }

    /**
     * Tests contains() when the Card is present in the Hand.
     * Compares to the same and to a different Card instance.
     */
    @Test
    void containsTrue() {
        assertTrue(h1.contains(card1));
        assertTrue(h1.contains(new Card(Suit.A, Value.ONE)));
    }

    /**
     * Tests contains() when the Card is not present in the Hand.
     */
    @Test
    void containsFalse() {
        assertFalse(emptyHand.contains(card1));
        assertFalse(h1.contains(new Card(Suit.C, Value.ZERO)));
    }

    /**
     * Tests removeCard() when it is present in the Hand.
     * //TODO: error here. Find and fix.
     */
    @Test
    void removeCardPresent() {
        assertEquals(3, h1.getCardCount());
        h1.removeCard(card1);
        assertEquals(2, h1.getCardCount());
        h1.removeCard(new Card(Suit.A, Value.EIGHT));
        assertEquals(1, h1.getCardCount());
    }

    /**
     * Tests removeCard() when it is absent from the Hand.
     * (There should be no changes to the Hand)
     */
    @Test
    void removeCardAbsent() {
        assertEquals(3, h1.getCardCount());
        h1.removeCard(new Card(Suit.D, Value.FOUR));
        assertEquals(3, h1.getCardCount());
    }

    @Test
    void addCard() {
    }

    @Test
    void addCards() {
    }

    @Test
    void getAllCards() {
    }

    @Test
    void getCardCount() {
    }
}