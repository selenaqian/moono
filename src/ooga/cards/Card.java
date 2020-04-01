package ooga.cards;

/**
 * This class combines Color and Value to create a Card.
 * Has capacity for comparison via Comparable.
 */
public class Card implements Comparable<Card> {
    private final Color myColor;
    private final Value myValue;

    /**
     * Create a new Card.
     * @param color
     * @param value
     */
    public Card (Color color, Value value) {
        myColor = color;
        myValue = value;
    }

    /**
     * Get Color of Card.
     * @return myColor
     */
    public Color getColor () {
        return myColor;
    }

    /**
     * Get Value of Card.
     * @return myValue
     */
    public Value getValue () {
        return myValue;
    }

    @Override
    public String toString () {
        return getColor().toString() + getValue().toString();
    }

    @Override
    public int compareTo (Card o) {
        return myValue.compareTo(o.myValue);
    }

}
