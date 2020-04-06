package ooga.game;

/**
 * Keeps track of players and delegates turns
 * Used to rotate between player objects so that each player can play
 */
public interface TurnManager{
    /**
     * Determines the first player to play at the start of a new game or round
     * @return Player who gets to take the first turn
     */
    Player getFirstPlayer();

    /**
     * Gets the player who has the next turn
     */
    Player getNextPlayer();

    /**
     * The player currently making the play
     * Can also be called from the view to visually indicate who's turn it is
     */
    Player getCurrentPlayer();

}