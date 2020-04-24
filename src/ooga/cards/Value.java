package ooga.cards;

/**
 * This enum contains all the possible Values for cards.
 * @author Tess Noonan (tcn6)
 */
public enum Value {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    SKIP(10),
    REVERSE(11),
    DRAW2(12),
    SWAP(13),
    TRADE(14),
    WILD(15),
    WILD4(16);

    private final int myNumericValue;

    Value(int numericValue) {
        myNumericValue = numericValue;
    }

    /**
     * Returns the corresponding int of the Value.
     * @return myNumericValue
     */
    public int getNumericValue () {
        return myNumericValue;
    }

}
