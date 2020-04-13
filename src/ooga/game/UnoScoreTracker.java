package ooga.game;

import ooga.player.Player;

import java.util.HashMap;
import java.util.Map;

public class UnoScoreTracker implements ScoreTracker {

    private GameSettings settings;
    private Uno game;
    private Map<Player, Integer> scores;

    public UnoScoreTracker(GameSettings settings, Uno game){
        this.settings = settings;
        this.game = game;
        scores = new HashMap<>();
    }

    @Override
    public void calculate() {

    }

    @Override
    public int calcPlayerScore(Player player) {
        return 0;
    }
}
