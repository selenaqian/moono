package ooga.rules;

import ooga.cards.Card;
import ooga.cards.Value;
import ooga.piles.Hand;

/**
 * This class follows the classic uno rules: (WHICH IS ONLY USING BASIC CARDS FOR SPRINT 1)
 *      Must play a card that is either of same Suit or Value as the Card on top of DiscardPile (unless it's Wild).
 *      Game is over when the last card in a player's hand is valid.
 */
public class ClassicRules implements Rule {

    /**
     * Create new ClassicRules object.
     */
    public ClassicRules() {
    }

    @Override
    public boolean isValid(Card discard, Card playedCard) {
        if(playedCard.getValue() == Value.WILD || playedCard.getValue() == Value.WILD4){
            return true;
        }
        else if(playedCard.getSuit() == discard.getSuit() || playedCard.getValue() == discard.getValue()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOver(Card discard, Hand hand) {
        if(hand.getCardCount() == 1 && isValid(discard, hand.getAllCards().get(0))){
            return true;
        }
        return false;
    }
}
