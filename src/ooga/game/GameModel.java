package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;
import ooga.view.GameView;

import java.util.List;

public interface GameModel {
    void start();
    boolean playCard(Card selectedCard, GameView gameView);
    void drawCard();

    /**
     * Returns list of cards the user (the human player) has in their hand
     * Called in view to update the cards shown in the user's hand
     * @return list of cards inside a
     */
    List<Card> getUserHand();

    Card getTopDiscardCard();

    void registerObserver(PlayerObserver o);

    void removeObserver(PlayerObserver o);

    void notifyObservers();
}
