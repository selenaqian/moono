package ooga.game;

/**
 * Functions as a controller class
 * Called in SetupView
 */
public class GameSettings {

    private int numPlayers; //set to default 4 players
    private int handSize; //number of cards per player, set to default 7
    private int winningScore; //default 500 for uno

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

    //TODO: add setters and getters for: Rules, Themes, Special cards
}
