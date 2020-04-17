package ooga.piles;

/**
 * A Deck is an ordered Pile that acts like a Stack (only add/draw from top).
 * (Will be changed to an abstract class)
 */
public interface Deck /*implements Pile*/ {

    /**
     * Shuffle the order of the Cards.
     */
    void shuffle();

    /**
     * Returns the top Card and removes it from the Pile.
     * @return Card
     */
    Card drawCard();
}
