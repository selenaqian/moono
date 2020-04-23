package ooga.game;

import ooga.player.Player;

import java.util.HashMap;
import java.util.List;

public interface ScoreTracker {


    /**
     * Calculates score for all players in a game
     * @param players
     */
    void calculate(List<Player> players);

    /**
     * Get the total score of a player
     */
    int getPlayerScore(Player player);

    /**
     * Gets scores.
     * THIS METHOD IS USED IN XMLEncoder TESTING
     * @param scores
     * @author Tess Noonan (tcn6)
     */
    void setScores(HashMap<Integer, Integer> scores);
}
