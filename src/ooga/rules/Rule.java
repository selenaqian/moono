package ooga.rules;

import ooga.cards.Card;
import ooga.piles.Hand;

/**
 * A Ruleset defines how a game is to be played, as in what moves are legal, how the game ends..
 */
public abstract class Rule {

    public static final int ONE_LEFT = 1;


    /**
     * This method determines whether or not a move is legal based on
     * the discardPile Card that is to be matched and the given Player's Card.
     * @param discard
     * @param playedCard
     * @return boolean
     */
    abstract public boolean isValid(Card discard, Card playedCard);

    /**
     * Checks if a game is over based on a given Player's hand.
     * All games end if a Player has 1 valid Card left.
     * Other versions, for example Deadly Uno, can also end when someone has 0 valid Cards.
     * @param discard
     * @param hand
     * @return boolean
     */
    public boolean isOver(Card discard, Hand hand) {
        if(hand.getCardCount() == ONE_LEFT && isValid(discard, hand.getAllCards().get(0))){
            return true;
        }
        return false;
    }
}
