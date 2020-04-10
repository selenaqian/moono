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
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3));
        h1 = new Hand(cards);
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

    /**
     * Tests that addCard() adds a single Card to the Hand.
     */
    @Test
    void addUniqueCard() {
        ArrayList<Card> start = new ArrayList<>(Arrays.asList(card1));
        Hand hand = new Hand(start);
        hand.addCard(card2);

        assertTrue(hand.contains(card1));
        assertTrue(hand.contains(card2));
    }

    /**
     * Tests that addCard() adds a single Card to the Hand
     * despite it being a duplicate to one already present.
     */
    @Test
    void addDuplicateCard() {
        ArrayList<Card> start = new ArrayList<>(Arrays.asList(card1));
        Hand hand = new Hand(start);
        hand.addCard(card1);

        assertTrue(hand.contains(card1));
        assertEquals(2, hand.getCardCount());
    }

    /**
     * Tests adding a List of Cards with the addCards() method when
     * there is only one Card in the List.
     */
    @Test
    void addCardsSingle() {
        List<Card> moreCards = new ArrayList<>(Arrays.asList(card1));
        emptyHand.addCards(moreCards);
        assertTrue(emptyHand.contains(card1));
        assertEquals(1, emptyHand.getCardCount());
    }

    /**
     * Tests adding a List of Cards with the addCards() method when
     * there are multiple Cards in the List.
     */
    @Test
    void addCardsMultiple() {
        List<Card> moreCards = new ArrayList<>(Arrays.asList(card1, card2, card3));
        emptyHand.addCards(moreCards);

        assertEquals(3, emptyHand.getCardCount());
        assertTrue(emptyHand.contains(card1));
        assertTrue(emptyHand.contains(card2));
        assertTrue(emptyHand.contains(card3));
    }

    /**
     * Tests the getAllCards() method with an empty Hand.
     */
    @Test
    void getAllCardsEmptyHand() {
        assertEquals(new ArrayList<Card>(), emptyHand.getAllCards());
    }

    /**
     * Tests the getAllCards() method with a populated Hand.
     */
    @Test
    void getAllCardsPopulatedHand() {
        List<Card> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(card1, card2, card3));
        assertEquals(expected, h1.getAllCards());
    }

    /**
     * Tests getCardCount() for a populated Hand.
     */
    @Test
    void getCardCountFullDeck() {
        assertEquals(3, h1.getCardCount());
    }

    /**
     * Tests getCardCount() for an empty Hand.
     */
    @Test
    void getCardCountEmptyDeck() {
        assertEquals(0, emptyHand.getCardCount());
    }
}