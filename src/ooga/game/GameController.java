package ooga.game;

import javafx.stage.Stage;

/**
 * Can be implemented by classes controlling an overall game and each round within a game
 * Controls the overall flow of the game and will control the game loop
 * @author Mary Jiang (mvj6)
 */

public interface GameController{
    /**
     * Begin playing a new game or new round
     * Called from view once a user has finished setting up
     */
    void start();

    /**
     * Pause gameplay
     * For example, when a user accesses settings during a game
     */
    void pause();

    /**
     * Handle the end of a game if a player reaches the winning score or if a round is completed
     * @param playerID winning player
     */
    void endGame(int playerID);

    /**
     * Handles actions performed in a game loop
     * Used to call plays by AI_players
     * @param elapsedTime
     */
    void step(double elapsedTime);

    /**
     * Adjust rate that step is called
     * @param speed double representing seconds between each "turn"
     */
    void changeSpeed(double speed);

    /**
     * Creates a new EndRoundView controller once a round is over
     * Updates player scores for the round
     */
    void endRound();

    /**
     * Called in EndRoundView when user clicks button to begin new round
     * Resets a game but not the GameSettings and Scores
     */
    void newRound();

}