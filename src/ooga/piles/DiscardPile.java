package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Deck that Starts with no Cards then grows.
 */
public class DiscardPile implements Pile, Deck {

    /**
     * Returns the the top card, does not remove from Pile.
     * @return
     */
    public Card showTopCard(){
        //TODO: Write method;
        return new Card(Suit.A, Value.ZERO, Action.NONE);
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
