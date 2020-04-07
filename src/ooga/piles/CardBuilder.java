package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class instantiates Cards and compiles them into a List.
 * @author Tess Noonan (tcn6)
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
                out.push(new Card(s, v));
                out.push(new Card(s, v));
            }
        }
        return out;
    }

    /**
     * This method creates a Deck using Cards with the given Value.
     * There will be 8 of every Card with 0 <= Value <= 9 (2 per suit)
     * There will be 4 of every Card without 10 <= Value (1 per suit)
     * @return custom deck of cards
     */
    public Stack<Card> makeDeck(List<Value> values){
        Stack<Card> out = makeFullDeck();
        for(Value v : values){
            if(v.getNumericValue() <= 9){
                break;
            }
            //TODO: implement in Sprint 2;
        }
        return out;
    }
}
