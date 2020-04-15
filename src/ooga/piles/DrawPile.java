package ooga.piles;

import ooga.cards.Card;
import ooga.cards.Value;

import java.util.*;

/**
 * This is a Deck that typically starts with a complete set of Cards then shrinks.
 * @author Tess Noonan (tcn6)
 */
public class DrawPile extends Deck {

    public static final int EMPTY = 0;

    /**
     * Creates a new shuffled DrawPile with a full standard Deck.
     */
    public DrawPile(){
        CardBuilder build = new CardBuilder();
        myCards = build.makeFullDeck();
        shuffle();
    }

    /**
     * Creates a new shuffled DrawPile with the passed Cards.
     */
    public DrawPile(Stack<Card> cards){
        myCards = cards;
        shuffle();
    }

    /**
     * Creates a new shuffled DrawPile with standard Deck and additional action cards as specified.
     * @param values
     */
    public DrawPile(List<Value> values){
        CardBuilder build = new CardBuilder();
        myCards = build.makeDeck(values);
        shuffle();
    }

    /**
     * Returns the top Card and removes it from the Pile.
     * @return Card
     */
    public Card drawCard(){
        if(myCards.size() == EMPTY){
            return null;
        }
        return myCards.pop();
    }

    /**
     * Returns the specified number of Cards and removes them from the Pile.
     * @param numCards
     * @return List<Card>
     */
    public List<Card> drawCards(int numCards){
        List<Card> out = new ArrayList<>();
        for(int i = 0; i < numCards; i++){
            if(myCards.size() == EMPTY){
                break;
            }
            out.add(myCards.pop());
        }
        return out;
    }
}
