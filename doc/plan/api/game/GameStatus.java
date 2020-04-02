package ooga.game;

/**
 * Controls used by GamePlay to determine the status of the game
 */

public interface GameStatus{
    /**
     * The score to play until a player has reached it
     * @param score chosen when new game is initialized, typically 500
     */
    void setWinningScore(int score);

    /**
     * Get the score that players much reach to end the game
     * @return
     */
    int getWinningScore();

    /**
     * Get the current round being played
     * @return
     */
    Round getCurrentRound();

    /**
     * Check if a game is ove (once a player has reached the winning score)
     * @return true if game is over
     */
    boolean isOver();


}