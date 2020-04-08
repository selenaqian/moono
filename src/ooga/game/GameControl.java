package ooga.game;

/**
 * Can be implemented by classes controlling an overall game and each round within a game
 * Controls the overall flow of the game and will control the game loop
 */

public interface GameControl{
    /**
     * Begin playing a new game or new round
     */
    void init();

    /**
     * Handle the end of a game if a player reaches the winning score or if a round is completed
     */
    void end();

    /**
     * Checks to see if a game can be over
     * @return true if over
     */
    boolean isOver();
}