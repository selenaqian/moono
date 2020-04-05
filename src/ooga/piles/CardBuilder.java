package ooga.piles;

import ooga.cards.*;

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
        Stack<Card> out = new Stack<>();
        for(Value v : Value.values()){
            for(Suit s : Suit.values()){
                out.push(new Card(s, v, Action.NONE));
                out.push(new Card(s, v, Action.NONE));
            }
        }
        return out;
    }

    /**
     * This method creates a Deck using Cards with the given Actions.
     * There will be 8 of every Card with Action.NONE (2 per suit)
     * There will be 4 of every Card without Action.NONE (1 per suit)
     * @return custom deck of cards
     */
    public Stack<Card> makeDeck(List<Action> actions){
        Stack<Card> out = makeFullDeck();
        for(Action a : actions){
            if(a == Action.NONE){
                break;
            }
            //TODO: implement in Sprint 2;
        }
        return out;
    }
}
