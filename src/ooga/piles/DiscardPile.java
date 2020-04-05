package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Deck that Starts with no Cards then grows.
 */
public class DiscardPile extends Deck {

    /**
     * Returns the the top card, does not remove from Pile.
     * @return
     */
    public Card showTopCard(){
        //TODO: Write method;
        return new Card(Suit.A, Value.ZERO, Action.NONE);
    }
}
