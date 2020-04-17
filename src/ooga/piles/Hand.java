package ooga.piles;

import ooga.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unordered and visible Pile meant to function as the set of Cards a Player posses.
 * @author Tess Noonan (tcn6)
 */
public class Hand implements Pile {

    private List<Card> myCards;

    /**
     * Creates new empty Hand.
     */
    public Hand(){
        myCards = new ArrayList<>();
    }

    /**
     * Creates new Hand with passed Cards.
     * @param initialCards
     */
    public Hand(List<Card> initialCards){
        myCards = initialCards;
    }

    /**
     * Counts the number of Cards of a given Suit in the Hand.
     * @param suit
     * @return int
     */
    public int numSuit(Suit suit){
        int count = 0;
        for(Card c : myCards){
            if(c.getSuit() == suit){
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of Cards of a given Value in the Hand.
     * @param value
     * @return int
     */
    public int numValue(Value value){
        int count = 0;
        for(Card c : myCards){
            if(c.getValue() == value){
                count++;
            }
        }
        return count;
    }

    /**
     * Determines whether or not a given Card is present in the hand.
     * @param card
     * @return boolean
     */
    public boolean contains(Card card){
        for(Card c : myCards){
            if(c.equals(card)){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes given Card from Hand if present.
     * @param card
     */
    public void removeCard(Card card){
        boolean ret = myCards.remove(card);
        return;
    }

    @Override
    public void addCard(Card card){
        myCards.add(card);
    }

    @Override
    public void addCards(List<Card> cards){
        myCards.addAll(cards);
    }

    @Override
    public List<Card> getAllCards(){
        return myCards;
    }

    @Override
    public int getCardCount(){
        return myCards.size();
    }

    /*
    sorts hand
    sna19
     */
    public void sortedHand(List<Card> Cards){
        Collections.sort(Cards);
        Collections.reverse(Cards);
    }

    public void reset(){
        myCards.clear();
    }
}
