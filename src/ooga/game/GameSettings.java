package ooga.game;

import ooga.cards.Card;
import ooga.cards.Value;
import ooga.rules.Rule;

import java.util.List;

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

    public GameSettings(){
        numPlayers = 4;
        handSize = 7;
        winningScore = 500;
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
        //TODO get values enum from string

    }

    public void setRules(String rule){
        //TODO: implement java reflection
    }

    public Rule getRule(){
        return rule;
    }

    public List<Card> getSpecialCards(){
        return specialCards;
    }

    //TODO: add setters and getters for: Rules, Themes, Special cards
}
