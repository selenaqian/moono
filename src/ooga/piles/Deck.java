package ooga.piles;

import ooga.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * A Deck is an ordered Pile that acts like a Stack (only add/draw from top).
 */
public abstract class Deck implements Pile {

    protected Stack<Card> myCards; //TODO: protected so subclass has access -- is there better way?

    /**
     * Shuffle the order of the Cards.
     */
    void shuffle(){
        ArrayList<Card> copy = new ArrayList<>();
        copy.addAll(myCards);
        Collections.shuffle(copy);

        Stack<Card> replace = new Stack<>();
        for(Card c : copy){
            replace.push(c);
        }
        myCards = replace;
    }

    @Override
    public void addCard(Card card){
        myCards.push(card);
    }

    @Override
    public void addCards(List<Card> cards){
        for(Card c : cards){
            myCards.push(c);
        }
    }

    @Override
    public List<Card> getAllCards(){
        return myCards;
    }

    @Override
    public int getCardCount(){
        return myCards.size();
    }
}
