package ooga.game;

import ooga.cards.Card;

import java.util.List;

/**
 * Updates cards in a player hand and the discard pile card
 * Used to notify the View when players make plays or draws in the GameModel
 * @author Mary Jiang (mvj60
 */
public interface PlayerObserver {
    void updatePlayerHand(int playerId, List<Card> cardsLeft);
    void updateDiscardPile(Card card);
}
