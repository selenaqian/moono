package ooga.piles;

import ooga.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Deck abstract class.
 * @author Tess Noonan (tcn6)
 */
class DeckTest {

    private Deck deck1 = new DrawPile();

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
        deck1.shuffle();
        assertNotEquals(deck1Start, deck1.getAllCards());
        Stack<Card> deck1Next = deck1.getAllCards();
        deck1.shuffle();
        assertNotEquals(deck1Next, deck1.getAllCards());
    }

    @Test
    void shuffleEmptyDeck() {

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