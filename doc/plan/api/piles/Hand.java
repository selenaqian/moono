package ooga.piles;

import ooga.cards.*;

/**
 * Unordered and visible Pile meant to function as the set of Cards a Player posses.
 * (Will be changed to concrete class)
 */
public interface Hand /*implements Pile*/ {

    /**
     * Counts the number of Cards of a given Suit in the Hand.
     * @param suit
     * @return int
     */
    int numSuit(Suit suit);

    /**
     * Counts the number of Cards of a given Value in the Hand.
     * @param value
     * @return int
     */
    int numValue(Value value);

    /**
     * Determines whether or not a given Card is present in the hand.
     * @param card
     * @return boolean
     */
    boolean contains(Card card);

    /**
     * Returns and removes given Card from Hand.
     * @param card
     * @return Card
     */
    Card playCard(Card card);
}
