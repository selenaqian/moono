package ooga.cards;

/**
 * This class combines Color and Value to create a Card.
 * Has capacity for comparison via Comparable.
 * (Will be changed to concrete class)
 */
public interface Card /*implements Comparable<Card>*/ {

    /**
     * Get Color of Card.
     * @return myColor
     */
    Color getColor ();

    /**
     * Get Value of Card.
     * @return myValue
     */
    Value getValue ();

    @Override
    String toString ();

    /*@Override*/
    int compareTo (Card o);
}
