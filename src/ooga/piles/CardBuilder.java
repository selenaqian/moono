package ooga.piles;

import ooga.cards.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * This class instantiates Cards and compiles them into a List.
 * @author Tess Noonan (tcn6)
 */
public class CardBuilder {

    public static final int BASIC_THRESHOLD = 9;

    public static final String DEFAULT = "deck_size";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT);

    /**
     * Creates new CardBuilder.
     */
    public CardBuilder(){
    }

    /**
     * This method creates a full Deck of basic Cards:
     * X per Suit of every Card with 0 <= Value <= 9 (where X is defined in myResources)
     * @return full deck of basic cards
     */
    public Stack<Card> makeFullDeck(){
        Stack<Card> out = new Stack<>();
        for(Value v : Value.values()){
            if(v.getNumericValue() > BASIC_THRESHOLD){
                continue;
            }
            for(Suit s : Suit.values()){
                int perSuit = Integer.parseInt(myResources.getString("basic"));
                for(int i = 0; i < perSuit; i++){
                    out.push(new Card(s, v));
                }
            }
        }
        return out;
    }

    /**
     * This method creates a Deck using Cards with the given (special) Values.
     * X per Suit of every Card with 0 <= Value <= 9
     * Y per Suit of every Card without 10 <= Value
     * (where X and Y are defined in myResources)
     * @return custom deck of cards
     */
    public Stack<Card> makeDeck(List<Value> values){
        Stack<Card> out = makeFullDeck();
        for(Value v : values){
            if(v.getNumericValue() <= BASIC_THRESHOLD){
                continue;
            }
            for(Suit s : Suit.values()){
                int perSuit = Integer.parseInt(myResources.getString("special"));
                for(int i = 0; i < perSuit; i++){
                    out.push(new Card(s, v));
                }
            }
        }
        return out;
    }
}
