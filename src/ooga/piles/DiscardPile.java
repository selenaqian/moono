package ooga.piles;

import ooga.cards.*;

import java.util.Stack;

/**
 * This is a Deck that Starts with no or few Cards then grows.
 * @author Tess Noonan (tcn6)
 */
public class DiscardPile extends Deck {

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
     * @return
     */
    public Card showTopCard(){
        return myCards.peek();
    }
}
