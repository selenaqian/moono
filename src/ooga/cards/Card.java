package ooga.cards;

/**
 * This class combines Color, Value, and Action to create a Card.
 * Has capacity for comparison via Comparable.
 */
public class Card implements Comparable<Card> {
    private final Color myColor;
    private final Value myValue;
    private final Action myAction;

    /**
     * Create a new Card.
     * @param color
     * @param value
     * @param action
     */
    public Card (Color color, Value value, Action action) {
        myColor = color;
        myValue = value;
        myAction = action;
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

    /**
     * Get Action of Card.
     * @return myAction
     */
    public Action getAction (){
        return myAction;
    }

    @Override
    public String toString () {
        return getColor().toString() + getValue().toString() + getAction().toString();
    }

    @Override
    public int compareTo (Card o) {
        return myValue.compareTo(o.myValue);
    }

}
