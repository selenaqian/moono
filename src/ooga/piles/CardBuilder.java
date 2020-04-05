package ooga.piles;

import ooga.cards.Action;
import ooga.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class instantiates Cards and compiles them into a List.
 */
public class CardBuilder {

    /**
     * Creates new CardBuilder.
     */
    public CardBuilder(){
    }

    /**
     * This method instantiates every possible Card combination and returns them as a List.
     * @return full deck of cards
     */
    public Stack<Card> makeFullDeck(){
        //TODO: Write method;
        return new Stack<>();
    }

    /**
     * This method creates a Deck using Cards with the given Actions.
     * There will be 8 of every Card with Action.NONE (2 per suit)
     * There will be 4 of every Card without Action.NONE (1 per suit)
     * @return custom deck of cards
     */
    public Stack<Card> makeDeck(List<Action> actions){
        //TODO: Write method;
        return new Stack<>();
    }
}
