package ooga.game;

import ooga.cards.Card;
import ooga.cards.Value;
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
    private HashMap<String,Integer> playerScores;

    public UnoScoreTracker(GameSettings settings, Uno game){
        this.settings = settings;
        this.game = game;
        scoreResources = ResourceBundle.getBundle(SCORE_RESOURCES);
        playerScores = new HashMap<>();
    }

    @Override
    public void calculate() {

    }

    @Override
    public int calcPlayerScore(Player player) {
        int score = 0;
        for (Card card : player.hand().getAllCards()) {
            String cardType = card.getValue().name();
            if (scoreResources.containsKey(cardType)) {
                String cardVal = scoreResources.getString(cardType);
                score += Integer.parseInt(cardVal);
            } else { //for normal cards, add the value of the card to the score
                score += card.getValue().getNumericValue();
            }
        }

        return score;
    }


    private void updatePlayerScore(String playerID, int addedScore){

    }
}
