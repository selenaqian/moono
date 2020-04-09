package ooga.game;

import ooga.cards.Card;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.player.Player;
import ooga.rules.Rule;
import ooga.view.GameView;
import ooga.view.GameViewInterface;

import java.util.List;

/**
 * Model class for handling play of Uno
 * Equivalent to GamePlay interface from planning
 */
public class Uno implements GameModel {

    private GameViewInterface view;

    private GameSettings settings;
    private UnoController unoController;
    private UnoTurnManager turnManager;
    private Player currentPlayer;
    private Player user; //the human player

    private DiscardPile discPile;
    private DrawPile drawPile;

    private Rule rule;

    public Uno(){
        this(new GameSettings());
    }

    public Uno(GameSettings settings){
        this.view = new GameView(); //TODO: change to interface
        this.settings = settings;
        turnManager = new UnoTurnManager();
        unoController = new UnoController();
        addPlayers();
    }

    @Override
    public void start() {
        discPile = new DiscardPile();
        drawPile = new DrawPile();
        currentPlayer = turnManager.getCurrentPlayer();
        user = currentPlayer; //TODO: change this so that the human doesn't always start first
        dealCards();
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
        Card card = drawPile.drawCard();

        //get player to accept the drawn card into their own hand of cards
        currentPlayer.takeCard(card);

        endTurn();
    }

    @Override
    public List<Card> getUserHand() {
        return user.hand();
    }

    private void endTurn(){
        //check if a player has no more cards
        if (currentPlayer.hand().size() == 0){
            unoController.endGame();
        } else {
            turnManager.nextPlayer();
        }
    }

    /**
     * Deal cards to all players in the beginning of a game
     */
    private void dealCards(){
        for(int i = 0; i < settings.getNumPlayers(); i ++){
            Player player = turnManager.getFirstPlayer();
            for (int j = 0; j < settings.getHandSize(); j++){
                Card card = drawPile.drawCard();
                player.takeCard(card);
                turnManager.nextPlayer();
            }
        }
    }

    /**
     * Adds players to the game based on number of players selected in SetupView
     * TODO: Refactor this - have another class to manage/initialize players?
     */
    private void addPlayers(){
        for (int i = 0; i < settings.getNumPlayers(); i++){
            turnManager.addPlayer(new Player());
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
