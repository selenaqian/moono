package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unordered and visible Pile meant to function as the set of Cards a Player posses.
 */
public class Hand implements Pile {

    /**
     * Counts the number of Cards of a given Color in the Hand.
     * @param color
     * @return int
     */
    int numColor(Color color){
        //TODO: Write method;
        return 0;
    }

    /**
     * Counts the number of Cards of a given Value in the Hand.
     * @param value
     * @return int
     */
    int numValue(Value value){
        //TODO: Write method;
        return 0;
    }

    /**
     * Determines whether or not a given Card is present in the hand.
     * @param card
     * @return boolean
     */
    boolean contains(Card card){
        //TODO: Write method;
        return false;
    }

    /**
     * Returns and removes given Card from Hand.
     * @param card
     * @return Card
     */
    Card playCard(Card card){
        //TODO: Write method;
        return new Card(Color.BLUE, Value.ZERO, Action.NONE);
    }

    @Override
    public void addCard(Card card){
        //TODO: Write method;
    }

    @Override
    public void addCards(List<Card> cards){
        //TODO: Write method;
    }

    @Override
    public List<Card> getAllCards(){
        //TODO: Write method;
        return new ArrayList<Card>();
    }

    @Override
    public int getCardCount(){
        //TODO: Write method;
        return 0;
    }
}
