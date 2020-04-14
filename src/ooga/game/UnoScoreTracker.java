package ooga.game;

import ooga.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.view.SetupView.DEFAULT_RESOURCES;



public class UnoScoreTracker implements ScoreTracker {

    public static final String SCORE_RESOURCES = "scoring";
    private GameSettings settings;
    private Uno game;
    private ResourceBundle scoreResources;

    public UnoScoreTracker(GameSettings settings, Uno game){
        this.settings = settings;
        this.game = game;
        scoreResources = ResourceBundle.getBundle(SCORE_RESOURCES);
    }

    @Override
    public void calculate() {

    }

    @Override
    public int calcPlayerScore(Player player) {
        return 0;
    }
}
