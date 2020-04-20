package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;
import ooga.view.GameView;

import java.util.List;

public interface GameModel {
    void start();
    void restart();
    boolean playCard(Card selectedCard, Player player);
    void drawCard(Player player);

    /**
     * Returns list of cards the user (the human player) has in their hand
     * Called in view to update the cards shown in the user's hand
     * @return list of cards inside a
     */
    List<Card> getUserHand();

    Card getTopDiscardCard();

    void registerPlayerObserver(PlayerObserver o);

    void removePlayerObserver(PlayerObserver o);

    void notifyPlayerObservers();
}
