package ooga.game;

import ooga.cards.Card;
import ooga.piles.Hand;
import ooga.player.Player;

/**
 * Controller class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno {

    private View view;

    private UnoTurnManager turnManager;
    private PileManager pileManager;
    private Player currentPlayer;


    public Uno(){
        //initialize connection to view
        this.view = new View();
        turnManager = new UnoTurnManager();
        pileManager = new PileManager();
        currentPlayer = turnManager.getCurrentPlayer();
    }

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     * @param card the card selected by the player in the view
     */
    public void playCard(Card card){
        //get the player to play a valid card from their hand
        currentPlayer.playCard(card);

        //end current player's turn and go to next player
        turnManager.nextPlayer();
    }

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view for human players
     * For AI players, must be called programmatically
     */
    public void drawCard(){
        //take the top card from the discard pile
        Card card = pileManager.removeCard();

        //get player to accept the drawn card into their own hand of cards
        currentPlayer.takeCard(card);

        //end current player's turn and go to next player
        turnManager.nextPlayer();
    }

    /**
     * Handle effect of an action card when it is drawn
     * This method should have access to TurnManager, especially for Reverse cards
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
