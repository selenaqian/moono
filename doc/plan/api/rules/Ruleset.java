package ooga.rules;

import ooga.cards.Card;
import ooga.piles.Hand;

/**
 * A Ruleset defines how a game is to be played, as in what moves are legal, how the game ends,
 * and the consequences of special Cards.
 */
public interface Ruleset {

    /**
     * This method determines whether or not a move is legal based on
     * the discardPile Card that is to be matched and the given Player's Card.
     * @param discard
     * @param playedCard
     * @return boolean
     */
    boolean isValid(Card discard, Card playedCard);

    /**
     * Checks if a game is over based on a given Player's hand.
     * (for example, Classic Uno ends when someone has 1 Card left that is valid
     *               Deadly Uno ends when someone has 0 valid Cards)
     * @param discard
     * @param hand
     * @return boolean
     */
    boolean isOver(Card discard, Hand hand);
}
