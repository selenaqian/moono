package ooga.game;

import ooga.cards.Card;
import ooga.cards.Value;
import ooga.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.view.SetupView.DEFAULT_RESOURCES;



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
        return playerScores.get(player.getID());
    }

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
        updatePlayerScore(player.getID(), score);
        return score;
    }


    private void updatePlayerScore(int playerID, int addedScore){
        //TODO: change playerID to integer if necessary
        if (!playerScores.containsKey(playerID)){
            playerScores.putIfAbsent(playerID, addedScore);
        } else {
            int oldScore = playerScores.get(playerID);
            playerScores.put(playerID, oldScore + addedScore);
        }
    }


}
