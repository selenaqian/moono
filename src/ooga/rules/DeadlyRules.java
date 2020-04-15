package ooga.rules;

import ooga.cards.Card;
import ooga.piles.Hand;

/**
 * This class follows the deadly uno rules:
 *      Must play a card that is either of same Suit or Value as the Card on top of DiscardPile (unless it's any Special Card).
 *      Game is over when a player does not have a valid move.
 */
public class DeadlyRules implements Rule {

    public static final int SPECIAL_THRESHOLD = 10;

    /**
     * Create new ClassicRules object.
     */
    public DeadlyRules() {
    }

    @Override
    public boolean isValid(Card discard, Card playedCard) {
        if(playedCard.getValue().getNumericValue() >= SPECIAL_THRESHOLD){
            return true;
        }
        else if(playedCard.getSuit() == discard.getSuit() || playedCard.getValue() == discard.getValue()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOver(Card discard, Hand hand) {
        for(Card c : hand.getAllCards()){
            if(isValid(discard, c)){
                return false;
            }
        }
        return true;
    }
}
