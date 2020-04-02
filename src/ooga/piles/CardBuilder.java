package ooga.piles;

import ooga.cards.Action;
import ooga.cards.Card;

import java.util.List;
import java.util.Stack;

/**
 * This class instantiates Cards and compiles them into a List.
 */
public interface CardBuilder {

    /**
     * This method instantiates every possible Card combination and returns them as a List.
     * @return full deck of cards
     */
    List<Card> makeFullDeck();

    /**
     * This method creates a Deck using Cards with the given Actions.
     * There will be 8 of every Card with Action.NONE (2 per suit)
     * There will be 4 of every Card without Action.NONE (1 per suit)
     * @return custom deck of cards
     */
    Stack<Card> makeDeck(List<Action> actions);
}
