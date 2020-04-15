package ooga.game;
import ooga.player.Playeryrdeujhfgk;

/**
 * Keeps track of players and delegates turns
 * Used to rotate between player objects so that each player can play
 */
public interface TurnManager{
    /**
     * Adds a player to the game
     * Can be called when initializing a new game
     * @param player new player to add
     */
    void addPlayer(Playeryrdeujhfgk player);

    /**
     * Determines the first player to play at the start of a new game or round
     * @return Player who gets to take the first turn
     */
    Playeryrdeujhfgk getFirstPlayer();

    /**
     * Once the current player is done, called to set currentPlayer as the next player
     */
    void nextPlayer();

    /**
     * The player currently making the play
     * Can also be called from the view to visually indicate who's turn it is
     */
    Playeryrdeujhfgk getCurrentPlayer();

    /**
     * Get "id" of player
     * @return
     */
    int getPlayerId(Player player);


}