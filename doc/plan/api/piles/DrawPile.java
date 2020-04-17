package ooga.piles;

import ooga.cards.Card;
import java.util.List;

/**
 * This is a Deck that typically starts with a complete set of Cards then shrinks.
 * (Will be changed to concrete class)
 */
public interface DrawPile /*extends Deck*/ {

    /**
     * Returns the specified number of Cards and removes them from Pile.
     * @param numCards
     * @return List<Card>
     */
    List<Card> drawCards(int numCards);
}
