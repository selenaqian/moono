package ooga.cards;

public class Card implements Comparable<Card> {
    private final Color myColor;
    private final Value myValue;

    public Card (Color color, Value value) {
        myColor = color;
        myValue = value;
    }

    public Color getColor () {
        return myColor;
    }

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
