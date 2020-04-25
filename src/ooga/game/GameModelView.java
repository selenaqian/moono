package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;

import java.util.List;


/**
 * Methods that provide information for the view
 * @author Mary Jiang (mvj6)
 */
public interface GameModelView {
    /**
     * Returns list of cards the user (the human player) has in their hand
     * Called in view to update the cards shown in the user's hand
     * @return List of cards in user hand
     * @deprecated
     * replaced by player observer
     */
    List<Card> getUserHand();

    /**
     * Get the top card of the discard pile
     * @return Card
     * @deprecated
     * replaced by player observer
     */
    Card getTopDiscardCard();


    void registerPlayerObserver(PlayerObserver o);

    void removePlayerObserver(PlayerObserver o);

    void notifyPlayerObservers();
}
