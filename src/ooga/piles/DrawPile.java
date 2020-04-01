package ooga.piles;

import ooga.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Deck that typically starts with a complete set of Cards then shrinks.
 */
public class DrawPile implements Pile, Deck {

    /**
     * Returns the specified number of Cards and removes them from Pile.
     * @param numCards
     * @return List<Card>
     */
    public List<Card> drawCards(int numCards){
        //TODO: Write method;
        return new ArrayList<Card>();
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

    @Override
    public void shuffle(){
        //TODO: Write method;
    }
}
