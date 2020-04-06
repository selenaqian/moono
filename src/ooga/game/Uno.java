package ooga.game;

import ooga.piles.Hand;
import ooga.player.Player;

/**
 * Controller class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno {

    private View view;

    private UnoTurnManager turnManager;
    private Player currentPlayer;


    public Uno(){
        //initialize connection to view
        this.view = new View();
        currentPlayer = turnManager.getCurrentPlayer();
    }

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     */
    public void makePlay(){
        currentPlayer.playCard();
        turnManager.nextPlayer();
        //TODO: update view
    }

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view
     */
    public void drawCard(){
        currentPlayer.drawCard();
        turnManager.nextPlayer();
        //TODO: update view
    }

    /**
     * Handle effect of an action card when it is drawn
     * TODO: put action methods in a resource file and use this method to call them
     */
    public void handleAction(){

    }

    /**
     * Allows the human player to declare uno
     * Called from view class when the "uno" button is clicked
     */
    public void callUno(){

    }

    /**
     * Once a player has no cards left, check if they have declared uno
     * If a player has not declared, then they must pick up more cards
     */
    public void checkUno(){

    }

}
