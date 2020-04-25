package ooga.player;

import ooga.cards.Card;

import ooga.piles.Hand;

import java.util.List;


public abstract class Player {
    private Hand playerhand = new Hand();
    private int Id;

    public Player(){
    }

    /**
     * returns a list of the player's hand
     *
     * @return
     */
    public Hand hand() {
        return playerhand;
    }




    /**
     * for manual player, string input
     * @return the player's chosen name
     */
    abstract String manualplayerName();


    public void setID(int Id){
        this.Id = Id;
    }

    public int getID(){
        return Id;
    }

    /*
    *resets the player hand
     */
    public void reset(){
        playerhand.reset();
    }

}

