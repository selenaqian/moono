package ooga.piles;

/**
 * A Deck is an ordered Pile that acts like a Stack (only add/draw from top).
 */
public interface Deck {

    /**
     * Shuffle the order of the Cards.
     */
    void shuffle();
}
