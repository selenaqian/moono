package ooga.piles;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the CardBuilder class.
 * @author Tess Noonan (tcn6)
 */
class CardBuilderTest {

    /**
     * Tests that makeFullDeck() returns a full Stack with 80 cards when it takes no parameters.
     * This makes a deck with only basic cards.
     * (This is the only test for this method because it takes no parameters so there are no edge cases)
     */
    @Test
    void makeFullBasicDeck() {
        CardBuilder cb = new CardBuilder();
        Stack<Card> cards = cb.makeFullDeck();
        assertEquals(80, cards.size());

        //the returned Stack is not shuffled so the top Card should be of the greatest Suit/Value
        assertEquals(new Card(Suit.D, Value.NINE), cards.peek());
    }

    /**
     * Tests that makeDeck() with the given parameters makes a full Stack with all basic and special cards.
     * There should be 80 basic cards and 20 special cards for a total of 100 cards.
     */
    @Test
    void makeCompleteSpecialDeck(){
        CardBuilder cb = new CardBuilder();
        Stack<Card> cards = cb.makeDeck(Arrays.asList(Value.values()));
        assertEquals(100, cards.size());

        //the returned Stack is not shuffled so the top Card should be of the greatest Suit/Value
        assertEquals(new Card(Suit.D, Value.WILD4), cards.peek());
    }

    /**
     * Tests that makeDeck() with the given parameters makes a Stack with all basic and some special cards.
     */
    @Test
    void makePartialSpecialDeck(){
        CardBuilder cb = new CardBuilder();
        Stack<Card> cards = cb.makeDeck(Arrays.asList(Value.WILD, Value.REVERSE));
        assertEquals(88, cards.size());

        //the returned Stack is not shuffled so the top Card should be of the greatest Suit/Value
        assertEquals(new Card(Suit.D, Value.REVERSE), cards.peek());
    }

    /**
     * Tests that makeDeck() with the given parameters makes a Stack with all 80 basic cards (none extra)
     * even when passed basic Values.
     */
    @Test
    void makeDeckBasic(){
        CardBuilder cb = new CardBuilder();
        Stack<Card> cards = cb.makeDeck(Arrays.asList(Value.ONE, Value.TWO, Value.THREE));
        assertEquals(80, cards.size());

        //the returned Stack is not shuffled so the top Card should be of the greatest Suit/Value
        assertEquals(new Card(Suit.D, Value.NINE), cards.peek());
    }
}