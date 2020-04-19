package ooga.rules;

import ooga.cards.Card;
import ooga.cards.Value;

/**
 * This class follows the ascending uno rules:
 *      Must play a card that is either the same Value (any Suit) or greater Value (same Suit) as that on the top of the
 *      Discard Pile.
 *          You can always play any special card but only Wild cards "reset" the deck.
 *      Game is over when the last card in a player's hand is valid.
 */
public class AscendingRules extends Rule {

    public static final int WILD_THRESHOLD = 13;
    public static final int SPECIAL_THRESHOLD = 10;

    /**
     * Create new AscendingRules object.
     */
    public AscendingRules() {}

    @Override
    public boolean isValid(Card discard, Card playedCard) {
        if(discard.getValue().getNumericValue() >= WILD_THRESHOLD ||
                playedCard.getValue().getNumericValue() >= SPECIAL_THRESHOLD){
            return true;
        }
        if(playedCard.getValue() == discard.getValue()){
            return true;
        }
        if(playedCard.getValue().getNumericValue() > discard.getValue().getNumericValue() &&
                playedCard.getSuit() == discard.getSuit()){
            return true;
        }
        return false;
    }
}
