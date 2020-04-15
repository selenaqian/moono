package ooga.piles;

import ooga.cards.*;

import java.util.Stack;

/**
 * This is a Deck that Starts with no or few Cards then grows.
 * @author Tess Noonan (tcn6)
 */
public class DiscardPile extends Deck {

    public static final int EMPTY = 0;

    /**
     * Creates a new empty DiscardPile.
     */
    public DiscardPile(){
        myCards = new Stack<>();
    }

    /**
     * Creates a new DiscardPile with given initial Card.
     * @param card
     */
    public DiscardPile(Card card){
        myCards = new Stack<>();
        myCards.push(card);
    }

    /**
     * Returns the the top card, does not remove from Pile.
     * If Pile is empty, returns null.
     * @return Card
     */
    public Card showTopCard(){
        if(myCards.size() == EMPTY){
            return null;
        }
        return myCards.peek();
    }
}
