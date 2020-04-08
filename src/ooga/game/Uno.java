package ooga.game;

import ooga.cards.Card;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.piles.Hand;
import ooga.player.Player;
import ooga.rules.Rule;
import ooga.view.GameView;
import ooga.view.GameViewInterface;

/**
 * Controller class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno implements GameModel {

    private GameViewInterface view;

    private GameStatus gameStatus;
    private UnoTurnManager turnManager;
    private Player currentPlayer;

    private DiscardPile discPile;
    private DrawPile drawPile;

    private Rule rule;

    public Uno(){
        //initialize connection to view
        this.view = new GameView(); //TODO: change to interface
        turnManager = new UnoTurnManager();
        gameStatus = new GameStatus();
    }

    public void setRules(Rule rule){
        this.rule = rule;
    }

    @Override
    public void start() {
        discPile = new DiscardPile();
        drawPile = new DrawPile();
        currentPlayer = turnManager.getCurrentPlayer();
    }

    /**
     * Handle behavior when a user selects a card to play it
     * Called from the view
     * @param selectedCard the card selected by the player in the view or selected by the AI player
     */
    @Override
    public void playCard(Card selectedCard){
        //check if played card can be played on top of the discard pile top card
        if (rule.isValid(discPile.showTopCard(), selectedCard)) {

            //make sure player updates their hand to remove the card
            //currentPlayer.playCard(selectedCard);

            //update the discard pile to add the card
            discPile.addCard(selectedCard);

            //TODO: make call to handleAction() once special cards are implemented

            //go to next player only when a valid card is played
            endTurn();
        }
    }

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view for human players
     * For AI players, must be called programmatically
     */
    @Override
    public void drawCard(){
        //take the top card from the discard pile, make sure it is removed from the discard pile
        //Card card = discPile.popTopCard();

        //get player to accept the drawn card into their own hand of cards
        //currentPlayer.takeCard(card);

        endTurn();
    }

    private void endTurn(){
        //check if a player has no more cards
        if (currentPlayer.hand().size() == 0){
            gameStatus.endGame();
        } else {
            turnManager.nextPlayer();
        }
    }


//    /**
//     * Handle effect of an action card when it played
//     * This method should have access to TurnManager, especially for Reverse cards
//     * TODO: put action methods in a resource file and use this method to call them
//     */
//    public void handleAction(){
//
//    }
//
//    /**
//     * Allows the human player to declare uno
//     * Called from view class when the "uno" button is clicked
//     */
//    public void callUno(){
//
//    }
//
//    /**
//     * Once a player has no cards left, check if they have declared uno
//     * If a player has not declared, then they must pick up more cards
//     */
//    public void checkUno(){
//
//    }

}
