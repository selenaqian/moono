package ooga.cards;

/**
 * This class combines Suit and Value to create a Card.
 * Has capacity for comparison via Comparable.
 */
public class Card implements Comparable<Card> {
    private final Suit mySuit;
    private final Value myValue;

    /**
     * Create a new Card.
     * @param suit
     * @param value
     */
    public Card (Suit suit, Value value) {
        mySuit = suit;
        myValue = value;
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

    @Override
    public String toString () {
        return getSuit().toString() + getValue().toString();
    }

    @Override
    public int compareTo (Card o) {
        return myValue.compareTo(o.myValue);
    }

}
