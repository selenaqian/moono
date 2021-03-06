package ooga.piles;

import ooga.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * A Deck is an ordered Pile that acts like a Stack (only add/draw from top).
 * @author Tess Noonan (tcn6)
 */
public abstract class Deck implements Pile {

    public static final int EMPTY = 0;

    protected Stack<Card> myCards; //protected so subclass has access

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

    /**
     * Returns the top Card and removes it from the Pile.
     * @return Card
     */
    public Card drawCard(){
        if(myCards.size() == EMPTY){
            return null;
        }
        return myCards.pop();
    }

    /**
     * Sets cards.
     * XMLEncoder requires a matching setter and getter for each instance variable to be saved.
     * @param cards
     */
    public void setAllCards(Stack<Card> cards) {
        myCards = cards;
    }

    @Override
    public Stack<Card> getAllCards(){
        return myCards;
    }

    @Override
    public int getCardCount(){
        return myCards.size();
    }
}
