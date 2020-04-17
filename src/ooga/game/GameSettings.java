package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.rules.Rule;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static ooga.view.SetupView.DEFAULT_STYLESHEET;

/**
 * Functions as a controller class
 * Called in SetupView
 */
public class GameSettings {

    private int numPlayers; //set to default 4 players
    private int handSize; //number of cards per player, set to default 7
    private int winningScore; //default 500 for uno
    private List<Card> specialCards;
    private Rule rule;
    private String theme;

    public GameSettings(){
        numPlayers = 4;
        handSize = 7;
        winningScore = 500;
        specialCards = new ArrayList<>();
        theme = DEFAULT_STYLESHEET;
    }

    public int getNumPlayers(){
        return numPlayers;
    }

    public int getHandSize(){
        return handSize;
    }

    public int getWinningScore(){
        return winningScore;
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
    }

    public void setHandSize(int handSize){
        this.handSize = handSize;
    }

    public void setWinningScore(int winningScore){
        this.winningScore = winningScore;
    }

    public void setSpecialCards(List<String> specialCardValues){
        for (String s : specialCardValues){
            Value cardVal = Value.valueOf(s);
            specialCards.add(new Card(Suit.A, cardVal)); //suit doesn't matter here for special cards
        }
    }

    public void setRules(String ruleString){
        //TODO: test if relfection works
        try {
            Class<?> clazz = ruleString.getClass();
            Object o = clazz.getDeclaredConstructor().newInstance();
            rule = (Rule) o;
        }

        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            // FIXME: do something with this exception
            e.printStackTrace();
        }

    }

    public Rule getRule(){
        return rule;
    }

    public List<Card> getSpecialCards(){
        return specialCards;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String themeName) {
        theme = themeName;
    }
}
