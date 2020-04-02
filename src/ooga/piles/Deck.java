package ooga.piles;

import ooga.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * A Deck is an ordered Pile that acts like a Stack (only add/draw from top).
 * //TODO: Consider changing to abstract class?
 */
public abstract class Deck implements Pile {

    /**
     * Shuffle the order of the Cards.
     */
    void shuffle(){
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
