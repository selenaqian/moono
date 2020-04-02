package ooga.game;

/**
 * Calculates and keeps track of player scores at the end of each round
 * Will be implemented as a concrete class
 */
public interface ScoreTracker{

    /**
     * For each player, calculate and update their score based on the cards left in their hand
     * Called at the end of each round once one player has no more cards
     */
    void calculate();

    /**
     * Get the total score of a player
     */
    int getPlayerScore(Player player);
}