package ooga.cards;

/**
 * This class combines Color, Value, and Action to create a Card.
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

    /**
     * Get Action of Card.
     * @return myAction
     */
    Action getAction ();

    @Override
    String toString ();

    /*@Override*/
    int compareTo (Card o);
}
