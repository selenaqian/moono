package ooga.game;

import ooga.player.Player;

public interface ScoreTracker {


    void calculate();

    /**
     * Get the total score of a player
     */
    int calcPlayerScore(Player player);
}
