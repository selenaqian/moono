package ooga.cards;

/**
 * This class combines Suit and Value to create a Card.
 * Has capacity for comparison via Comparable.
 * @author Tess Noonan (tcn6)
 */
public class Card implements Comparable<Card> {
    public static final int EQUAL = 0;

    private Suit mySuit;
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

    public void setSuit (Suit newSuit) {
        mySuit = newSuit;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card c = (Card) o;
        return this.getSuit() == c.getSuit() && this.getValue() == c.getValue();
    }

    @Override
    public int compareTo (Card o) {
        int valueComp = myValue.compareTo(o.myValue);
        int suitComp = mySuit.compareTo(o.mySuit);
        if(valueComp == EQUAL && suitComp == EQUAL){
            return EQUAL;
        }
        else if(valueComp != EQUAL){
            return valueComp;
        }
        return suitComp;
    }

}
