package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Deck abstract class.
 * @author Tess Noonan (tcn6)
 */
class DeckTest {

    /**
     * Tests that the Card Stacks are unequal to the original Stack after shuffling.
     * Note: Stacks are equal when they have the same elements in the same order.
     */
    @Test
    void shuffleStandardDeck() {
        Deck deck1 = new DrawPile();
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
    void addUniqueCard() {
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

    /**
     * Tests adding a List of Cards with the addCards() method when
     * there is only one Card in the List.
     */
    @Test
    void addCardsSingle() {
        Deck deck6 = new DiscardPile();
        Card card1 = new Card(Suit.D, Value.NINE);

        List<Card> moreCards = new ArrayList<>(Arrays.asList(card1));
        deck6.addCards(moreCards);
        assertEquals(card1, deck6.getAllCards().peek());
        assertEquals(1, deck6.getAllCards().size());
    }

    /**
     * Tests adding a List of Cards with the addCards() method when
     * there are multiple Cards in the List.
     */
    @Test
    void addCardsMultiple() {
        Deck deck7 = new DiscardPile();
        Card card1 = new Card(Suit.D, Value.NINE);
        Card card2 = new Card(Suit.A, Value.ONE);
        Card cardiB = new Card(Suit.D, Value.ONE);

        List<Card> moreCards = new ArrayList<>(Arrays.asList(card1, card2, cardiB));
        deck7.addCards(moreCards);

        assertEquals(3, deck7.getAllCards().size());
        assertEquals(cardiB, deck7.getAllCards().pop());
        assertEquals(card2, deck7.getAllCards().pop());
        assertEquals(card1, deck7.getAllCards().pop());
    }

    /**
     * Tests the getAllCards() method with an empty Deck.
     */
    @Test
    void getAllCardsEmptyDeck() {
        Deck deck8 = new DiscardPile();
        assertEquals(new Stack<Card>(), deck8.getAllCards());
    }

    /**
     * Tests the getAllCards() method with a populated Deck.
     */
    @Test
    void getAllCardsNormalDeck() {
        Deck deck9 = new DiscardPile();
        Card card1 = new Card(Suit.D, Value.NINE);
        Card card2 = new Card(Suit.A, Value.ONE);
        deck9.addCards(Arrays.asList(card1, card2));

        Stack<Card> expected = new Stack<>();
        expected.push(card1);
        expected.push(card2);
        assertEquals(expected, deck9.getAllCards());
    }

    /**
     * Tests getCardCount() for a populated Deck.
     */
    @Test
    void getCardCountFullDeck() {
        Deck deck10 = new DrawPile();
        assertEquals(80, deck10.getCardCount());
    }

    /**
     * Tests getCardCount() for an empty Deck.
     */
    @Test
    void getCardCountEmptyDeck() {
        Deck deck11 = new DiscardPile();
        assertEquals(0, deck11.getCardCount());
    }
}