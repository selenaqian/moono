package ooga.cards;

import java.util.List;

/**
 * This class instantiates Cards and compiles them into a List.
 * (Will be changed to concrete class)
 */
public interface CardBuilder {

    /**
     * This method instantiates 2 of every possible Card combination and returns them as a Stack.
     * This is the default option.
     * @return full deck of cards
     */
    Stack<Card> makeDeck();

    /**
     * This method creates a Deck using Cards with the given Value.
     * There will be 8 of every Card with 0 <= Value <= 9 (2 per suit)
     * There will be 4 of every Card without 10 <= Value (1 per suit)
     * @return custom deck of cards
     */
    Stack<Card> makeDeck(List<Value> values);
}
