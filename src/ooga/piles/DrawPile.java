package ooga.piles;

import ooga.cards.Card;
import ooga.cards.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Deck that typically starts with a complete set of Cards then shrinks.
 */
public class DrawPile extends Deck {

    /**
     * Creates a new shuffled DrawPile with a full standard Deck.
     */
    public DrawPile(){
        CardBuilder build = new CardBuilder();
        myCards = build.makeFullDeck();
        shuffle();
    }

    /**
     * Creates a new shuffled DrawPile with standard Deck and additional action cards as specified.
     * TODO: This will not be relevant until Sprint 2
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
            out.add(myCards.pop());
        }
        return out;
    }
}
