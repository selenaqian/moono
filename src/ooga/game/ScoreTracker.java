package ooga.game;

import ooga.player.Player;

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
}
