package ooga.game;

/**
 * Can be implemented by classes controlling an overall game and each round within a game
 * Controls the overall flow of the game and will control the game loop
 */

public interface GameControl{
    /**
     * Set up new a game or new round
     */
    void initialize();

    /**
     * Begin playing a new game or new round
     */
    void start();

    /**
     * Pause gameplay
     * For example, when a user accesses settings during a game
     */
    void pause();

    /**
     * Handle the end of a game if a player reaches the winning score or if a round is completed
     */
    void end();
}