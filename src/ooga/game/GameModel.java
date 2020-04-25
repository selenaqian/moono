package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;
import ooga.view.GameView;

import java.util.List;

/**
 * Contains methods for managing gameplay events
 * @author Mary Jiang (mvj6)
 *
 */

public interface GameModel {
    /**
     * Initialize a new game
     */
    void start();

    /**
     * Restart a round of a game (resetting decks and player hands)
     */
    void restart();

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     */
    boolean playCard(Card selectedCard, Player player);

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view
     */
    void drawCard(Player player);

    /**
     * Check if a game is ove (once a player has reached the winning score)
     * @return true if game is over
     */
    boolean isOver();

    /**
     * TurnManager for accessing players
     * Use this method to access the current player
     * @return
     */
    TurnManager getTurnManager();

    /**
     * Gets PileManager that holds the draw and discard piles
     * @return
     */
    PileManager getPileManager();

    /**
     * Settings that are passed into the Game from the SetupView
     * Has information including number of players, cards per player, and winning score
     * @return
     */
    GameSettings getSettings();


}
