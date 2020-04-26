p
/**
 * Keeps track of players and delegates turns
 * Used to rotate between player objects so that each player can play
 */
public interface TurnManager{

    /**
     * Direction of "rotation" to the next player
     */
    final int CW = 1;
    final int CCW = -1;

    /**
     * Adds a player to the game
     * Can be called when initializing a new game
     * @param player new player to add
     */
    void addPlayer(Player player);

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

    /**
     * Checks to see if there is a player with no cards e.g. the winner of the round
     * @return the player with no cards left after their turn, or returns null if there is no winner yet
     */
    Player findWinner();

    /**
     * What direction the next turn will go in
     * @return int
     */
    int getDirection();

    /**
     * Changes the direction, affecting which player has the next turn
     * Typically called when an uno reverse card is played
     * @see getNextPlayer()
     */
    void changeDirection();

    /**
     * Returns all players a TurnManager is keeping track of
     * Used in UnoController to calculate scores for all players
     * @return List<Player>
     */
    List<Player> getAllPlayers();

}