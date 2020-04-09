package ooga.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Card class.
 * @author Tess Noonan (tcn6)
 */
class CardTest {

    private Card card1 = new Card(Suit.A, Value.ZERO);
    private Card card2 = new Card(Suit.A, Value.ZERO);
    private Card card3 = new Card(Suit.A, Value.ONE);
    private Card card4 = new Card(Suit.B, Value.ZERO);
    private Card card5 = new Card(Suit.B, Value.ONE);

    /**
     * Tests getSuit() on Cards with the same suit but different values.
     */
    @Test
    void getSameSuitWhenValuesDiffer() {
        assertEquals(Suit.A, card1.getSuit());
        assertEquals(Suit.A, card3.getSuit());
    }

    /**
     * Tests getSuit() on Cards with different suits but the same values.
     */
    @Test
    void getDifferentSuitWhenValuesSame() {
        assertEquals(Suit.A, card1.getSuit());
        assertEquals(Suit.B, card4.getSuit());
    }

    /**
     * Tests getValue() on Cards with the same values but different suits.
     */
    @Test
    void getSameValueWhenSuitsDiffer() {
        assertEquals(Value.ZERO, card1.getValue());
        assertEquals(Value.ZERO, card4.getValue());
    }

    /**
     * Tests getValue() on Cards with different values but the same suits.
     */
    @Test
    void getDifferentValueWhenSuitsSame() {
        assertEquals(Value.ZERO, card1.getValue());
        assertEquals(Value.ONE, card3.getValue());
    }

    /**
     * Tests toString() when Suit is A.
     */
    @Test
    void testToStringA() {
        assertEquals("AZERO", card1.toString());
        assertEquals("AONE", card3.toString());
    }

    /**
     * Tests toString() when Suit is B.
     */
    @Test
    void testToStringB() {
        assertEquals("BZERO", card4.toString());
        assertEquals("BONE", card5.toString());
    }

    /**
     * Tests equals() when the Cards are the same in instance or value.
     */
    @Test
    void equalsTrue(){
        assertTrue(card1.equals(card1));
        assertTrue(card1.equals(card2));
    }

    /**
     * Tests equals() when the Cards have different values.
     */
    @Test
    void equalsFalse(){
        assertFalse(card1.equals(card4));
        assertFalse(card1.equals(card3));
        assertFalse(card1.equals(card5));
    }

    /**
     * Tests compareTo() when the Cards are the same instance.
     */
    @Test
    void compareToSameInstance() {
        assertEquals(0, card1.compareTo(card1));
    }

    /**
     * Tests compareTo() when the Cards are the same but different instances.
     */
    @Test
    void compareToSameCard() {
        assertEquals(0, card1.compareTo(card2));
    }

    /**
     * Tests compareTo() when the Cards have the same Values but different Suits.
     */
    @Test
    void compareToDifferentSuits() {
        assertNotEquals(0, card1.compareTo(card4));
    }

    /**
     * Tests compareTo() when the Cards have the same Suits but different Values.
     */
    @Test
    void compareToDifferentValues() {
        assertNotEquals(0, card1.compareTo(card3));
    }

    /**
     * Tests compareTo() when the Cards have both different Suits and Values.
     */
    @Test
    void compareToAllDifferent() {
        assertNotEquals(0, card1.compareTo(card5));
    }
}