package ooga.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card1 = new Card(Suit.A, Value.ZERO);
    private Card card2 = new Card(Suit.A, Value.ZERO);
    private Card card3 = new Card(Suit.A, Value.ONE);
    private Card card4 = new Card(Suit.B, Value.ZERO);
    private Card card5 = new Card(Suit.B, Value.ONE);

    @Test
    void getSameSuitWhenValuesDiffer() {
        assertEquals(Suit.A, card1.getSuit());
        assertEquals(Suit.A, card3.getSuit());
    }

    @Test
    void getDifferentSuitWhenValuesSame() {
        assertEquals(Suit.A, card1.getSuit());
        assertEquals(Suit.B, card4.getSuit());
    }

    @Test
    void getSameValueWhenSuitsDiffer() {
        assertEquals(Value.ZERO, card1.getValue());
        assertEquals(Value.ZERO, card4.getValue());
    }

    @Test
    void getDifferentValueWhenSuitsSame() {
        assertEquals(Value.ZERO, card1.getValue());
        assertEquals(Value.ONE, card3.getValue());
    }

    @Test
    void testToStringA() {
        assertEquals("AZERO", card1.toString());
        assertEquals("AONE", card3.toString());
    }

    @Test
    void testToStringB() {
        assertEquals("BZERO", card4.toString());
        assertEquals("BONE", card5.toString());
    }

    @Test
    void compareToSameInstance() {
        assertEquals(0, card1.compareTo(card1));
    }

    @Test
    void compareToSameCard() {
        assertEquals(0, card1.compareTo(card2));
    }

    @Test
    void compareToDifferentSuits() {
        assertNotEquals(0, card1.compareTo(card4));
    }

    @Test
    void compareToDifferentValues() {
        assertNotEquals(0, card1.compareTo(card3));
    }

    @Test
    void compareToAllDifferent() {
        assertNotEquals(0, card1.compareTo(card5));
    }
}