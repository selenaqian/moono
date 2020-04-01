package ooga.piles;

import ooga.cards.Card;

/**
 * This is a Deck that Starts with no Cards then grows.
 * (Will be changed to concrete class)
 */
public interface DiscardPile /*implements Pile, Deck*/ {

    /**
     * Returns the the top card, does not remove from Pile.
     * @return
     */
    Card showTopCard();
}
