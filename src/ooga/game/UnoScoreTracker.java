package ooga.game;

import ooga.cards.Card;
import ooga.cards.Value;
import ooga.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.view.SetupView.DEFAULT_RESOURCES;


/**
 * Score tracker for Uno, where the hand of each player at the end of each round is tallied
 * Takes in a resource file with point values for each type of special card
 * Scores for numbered cards are based on the number value of the card
 * @author Mary Jiang
 */
public class UnoScoreTracker implements ScoreTracker {

    public static final String SCORE_RESOURCES = "scoring";
    private ResourceBundle scoreResources;
    private HashMap<Integer,Integer> playerScores;

    public UnoScoreTracker(){
        scoreResources = ResourceBundle.getBundle(SCORE_RESOURCES);
        playerScores = new HashMap<>();
    }

    @Override
    public void calculate(List<Player> players) {
        for (Player p : players){
            calcPlayerScore(p);
        }

    }
    @Override
    public int getPlayerScore(Player player){
        if (!playerScores.containsKey(player.getID())){
            return 0;
        }
        return playerScores.get(player.getID());
    }

    private int calcPlayerScore(Player player) {
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
        updatePlayerScore(player.getID(), score);
        return score;
    }

    private void updatePlayerScore(int playerID, int addedScore){
        if (!playerScores.containsKey(playerID)){
            playerScores.putIfAbsent(playerID, addedScore);
        } else {
            int oldScore = playerScores.get(playerID);
            playerScores.put(playerID, oldScore + addedScore);
        }
    }

    //XML Encoding requires setters/getters for the instances we want to save
    //@author Tess Noonan (tcn6)

    /**
     * Sets scores.
     * @return playerScores.
     */
    public HashMap<Integer, Integer> getScores() {
        return playerScores;
    }

    /**
     * Gets scores.
     * @param scores
     */
    @Override
    public void setScores(HashMap<Integer, Integer> scores){
        playerScores = scores;
    }

}
