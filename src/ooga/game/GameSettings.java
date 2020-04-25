package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.rules.ClassicRules;
import ooga.rules.DeadlyRules;
import ooga.rules.Rule;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

import static ooga.view.SetupView.DEFAULT_STYLESHEET;

/**
 * Holds and updates values for game settings like number of players, number of cards, winning score
 * Called in SetupView and passed to model
 * @author Mary Jiang (mvj6)
 */
public class GameSettings {

    private int numPlayers; //set to default 4 players
    private int handSize; //number of cards per player, set to default 7
    private int winningScore; //default 500 for uno
    private ArrayList<Card> specialCards;
    private Rule rule;
    private ResourceBundle myResources = ResourceBundle.getBundle("default");
    private String theme;
    private double speed; //rate the timeline is run at in seconds


    public GameSettings() {
        numPlayers = 4;
        handSize = 7;
        winningScore = 500;
        specialCards = new ArrayList<>();
        theme = DEFAULT_STYLESHEET;
        speed = 1.5;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getHandSize() {
        return handSize;
    }

    public int getWinningScore() {
        return winningScore;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }

    public void setSpecialCards(List<String> specialCardValues) {
        for (String s : specialCardValues) {
            Value cardVal = Value.valueOf(s);
            specialCards.add(new Card(Suit.A, cardVal)); //suit doesn't matter here for special cards
        }
    }


//    public void setRules(String ruleString) throws OOGAException {
//        //TODO: test if relfection works
//        try {
//            Class<?> clazz = ruleString.getClass();
//            Object o = clazz.getDeclaredConstructor().newInstance();
//            rule = (Rule) o;
//        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
//            throw new OOGAException(myResources.getString("NoSuch"), e);
//            // FIXME: do something with this exception
//            //e.printStackTrace();

//
//    }


    /**
     * Note from Tess: I renamed this "setRule" so it matches for XML requires
     * @param ruleString
     */
    public void setRule(String ruleString) {

        //FIXME: get reflection working to get rid of this
        if (ruleString.equals("ClassicRules")) {
            rule = new ClassicRules();
        } else if (ruleString.equals("DeadlyRules")) {
            rule = new DeadlyRules();

        }
    }

    public Rule getRule () {
        return rule;
    }

    public ArrayList<Card> getSpecialCards () {
        return specialCards;
    }

    public String getTheme () {
        return theme;
    }

    public void setTheme (String themeName){
        theme = themeName;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getSpeed(){
        return speed;
    }
}