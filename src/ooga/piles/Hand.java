package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unordered and visible Pile meant to function as the set of Cards a Player posses.
 */
public class Hand implements Pile {

    /**
     * Counts the number of Cards of a given Suit in the Hand.
     * @param suit
     * @return int
     */
    public int numSuit(Suit suit){
        //TODO: Write method;
        return 0;
    }

    /**
     * Counts the number of Cards of a given Value in the Hand.
     * @param value
     * @return int
     */
    public int numValue(Value value){
        //TODO: Write method;
        return 0;
    }

    /**
     * Determines whether or not a given Card is present in the hand.
     * @param card
     * @return boolean
     */
    public boolean contains(Card card){
        //TODO: Write method;
        return false;
    }

    /**
     * Removes given Card from Hand.
     * @param card
     * @return Card
     */
    public void removeCard(Card card){
        //TODO: Write method;
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
