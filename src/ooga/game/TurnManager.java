package ooga.game;
import ooga.player.Player;

import java.util.List;

/**
 * Keeps track of players and delegates turns
 * Used to rotate between player objects so that each player can play
 */
public interface TurnManager{
    /**
     * Adds all players to the game
     * Can be called when initializing a new game
     * @param numPlayers from GameSettings
     */
    void addPlayers(int numPlayers);

    /**
     * Once the current player is done, called to set currentPlayer as the next player
     * @return
     */
    Player getNextPlayer();

    /**
     * Moves turnManager to the next player so that getCurrentPlayer can be called later
     * Used when a turn is ended
     */
    void nextPlayer();


    /**
     * The player currently making the play
     * Can also be called from the view to visually indicate who's turn it is
     */
    Player getCurrentPlayer();

    /**
     * Get "id" of player
     * @return
     */
    int getPlayerId(Player player);

    /**
     * Removes all the cards from players' hands
     * Used when resetting a game for a new round
     */
    void clearPlayerHands();

    /**
     * Returns all players a TurnManager is keeping track of
     * Used in UnoController to calculate scores for all players
     * @return
     * NOTE: THIS WAS ADDED TO THE INTERFACE BY TESS FOR XMLToJavaTest
     */
    List<Player> getAllPlayers();

    /**
     * Changes the direction, affecting which player has the next turn
     * Typically called when an uno reverse card is played
     * NOTE: THIS WAS ADDED TO THE INTERFACE BY TESS FOR XMLToJavaTest
     */
    void changeDirection();

    /** Gives current direction.
     * THIS IS USED JUST FOR TESTING PURPOSES
     * refactored uno to use this method(sna19) since it makes it easier to work with the direction
     * NOTE: THIS WAS ADDED TO THE INTERFACE BY TESS FOR XMLToJavaTest
     */
    int getDirection();

}