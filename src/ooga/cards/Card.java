package ooga.cards;

/**
 * This class combines Suit and Value to create a Card.
 * Has capacity for comparison via Comparable.
 * @author Tess Noonan (tcn6)
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
        int valueComp = myValue.compareTo(o.myValue);
        int suitComp = mySuit.compareTo(o.mySuit);
        if(valueComp == 0 && suitComp == 0){
            return 0;
        }
        else if(valueComp != 0){
            return valueComp;
        }
        return suitComp;
    }

}
