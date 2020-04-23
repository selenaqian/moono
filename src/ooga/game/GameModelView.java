package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;

import java.util.List;


/**
 * Methods that provide information for the view
 */
public interface GameModelView {
    /**
     * Returns list of cards the user (the human player) has in their hand
     * Called in view to update the cards shown in the user's hand
     * @return
     */
    List<Card> getUserHand();

    Card getTopDiscardCard();

    /**
     * Can be called to indicate in the view which player is currently active
     * @return
     */
    Player getCurrentPlayer();

    void registerPlayerObserver(PlayerObserver o);

    void removePlayerObserver(PlayerObserver o);

    void notifyPlayerObservers();
}
