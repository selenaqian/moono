package ooga.cards;

/**
 * This class combines Suit, Value, and Action to create a Card.
 * Has capacity for comparison via Comparable.
 */
public class Card implements Comparable<Card> {
    private final Suit mySuit;
    private final Value myValue;
    private final Action myAction;

    /**
     * Create a new Card.
     * @param suit
     * @param value
     * @param action
     */
    public Card (Suit suit, Value value, Action action) {
        mySuit = suit;
        myValue = value;
        myAction = action;
    }

    /**
     * Get Suit of Card.
     * @return mySuit
     */
    public Suit getSuit () {
        return mySuit;
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
        return getSuit().toString() + getValue().toString() + getAction().toString();
    }

    @Override
    public int compareTo (Card o) {
        return myValue.compareTo(o.myValue);
    }

}
