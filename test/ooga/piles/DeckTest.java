package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Deck abstract class.
 * @author Tess Noonan (tcn6)
 */
class DeckTest {

    //TODO: Get rid of the instance var/before each
    private Deck deck1;

    /**
     * Start each test with a fresh Deck.
     * Uses DrawPile as the concrete subclass because it provides Cards to start that we can use for the testing.
     */
    @BeforeEach
    void setUp() {
        deck1 = new DrawPile();
    }

    /**
     * Tests that the Card Stacks are unequal to the original Stack after shuffling.
     * Note: Stacks are equal when they have the same elements in the same order.
     */
    @Test
    void shuffle() {
        Stack<Card> deck1Start = deck1.getAllCards();
        assertEquals(deck1Start, deck1.getAllCards());
        deck1.shuffle();
        assertNotEquals(deck1Start, deck1.getAllCards());
        Stack<Card> deck1Next = deck1.getAllCards();
        assertEquals(deck1Next, deck1.getAllCards());
        deck1.shuffle();
        assertNotEquals(deck1Next, deck1.getAllCards());
    }

    /**
     * Tests the shuffling of an empty Deck to show that the program does not break
     * and that the Stacks are equal as expected (both empty).
     */
    @Test
    void shuffleEmptyDeck() {
        Deck deck2 = new DiscardPile();
        Stack<Card> deck2Start = deck2.getAllCards();
        assertEquals(deck2Start, deck2.getAllCards());
        deck2.shuffle();
        assertEquals(deck2Start, deck2.getAllCards());
    }

    /**
     * Tests the shuffling of a Deck with one Card to show that the program does not break
     * and that the Stacks are equal as expected (both have the same single Card).
     */
    @Test
    void shuffleDeckOfOne() {
        Deck deck3 = new DiscardPile(new Card(Suit.C, Value.SEVEN));
        Stack<Card> deck2Start = deck3.getAllCards();
        assertEquals(deck2Start, deck3.getAllCards());
        deck3.shuffle();
        assertEquals(deck2Start, deck3.getAllCards());
    }

    /**
     * Tests that addCard() adds a single Card to the top of the Deck.
     */
    @Test
    void addCard() {
        Card card1 = new Card(Suit.D, Value.NINE);
        Card card2 = new Card(Suit.B, Value.SIX);
        Deck deck4 = new DiscardPile(card1);
        assertEquals(card1, deck4.getAllCards().peek());

        deck4.addCard(card2);
        assertEquals(card2, deck4.getAllCards().pop());
        assertEquals(card1, deck4.getAllCards().pop());
    }

    /**
     * Tests that addCard() adds a single Card to the top of the Deck
     * despite it being a duplicate to one already present.
     */
    @Test
    void addDuplicateCard() {
        Card card1 = new Card(Suit.D, Value.NINE);
        Deck deck5 = new DiscardPile(card1);
        assertEquals(card1, deck5.getAllCards().peek());

        deck5.addCard(card1);
        assertEquals(card1, deck5.getAllCards().pop());
        assertEquals(card1, deck5.getAllCards().pop());
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