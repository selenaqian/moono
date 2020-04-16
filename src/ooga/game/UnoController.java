package ooga.game;

import javafx.stage.Stage;
import ooga.cards.Card;
import ooga.player.Player;
import ooga.view.EndView;
import ooga.view.GameView;
import ooga.view.SetupView;

public class UnoController implements GameController{
    GameSettings settings; //equivalent to model in MVC
    SetupView setupView;
    GameView gameView;
    Stage mainStage;
    Uno uno;
    UnoTurnManager turnManager;
    UnoScoreTracker scoreTracker;

    public UnoController(Stage stage){
        mainStage = stage;
        this.settings = new GameSettings();
        setupView = new SetupView(this, settings, mainStage); //so that view knows about controller and GameSettings
    }

    @Override
    public void start() {
        uno = new Uno(settings);
        uno.start();
        gameView = new GameView(uno, this, mainStage); //TODO: change to interface
        turnManager = uno.getTurnManager();
        scoreTracker = new UnoScoreTracker();

    }

    @Override
    public void pause() {
        //TODO: add a popup view to adjust game options mid-game
    }

    @Override
    public void endGame() {
        new EndView(mainStage);
    }



    /**
     * Called when a user selects a card from their hand
     * @param card card that was clicked in the view
     */
    public void handleCardClick(Card card){
        if(uno.isUserTurn()){
            if(uno.playCard(card)) {
                gameView.updateHand(uno.getUserHand());
                gameView.updateDiscardPile(uno.getTopDiscardCard());
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {

                }
                checkRoundEnd();

            }
            gameView.updateHand(uno.getUserHand());
            gameView.updateDiscardPile(uno.getTopDiscardCard());
        }
    }

    /**
     * Called when a user clicks on the draw pile
     */
    public void handleDrawPileClick(){
        uno.drawCard();
        gameView.updateHand(uno.getUserHand()); //TODO: delete this and use updatePlayerHand for observer pattern
        //gameView.updatePlayerHand();
    }

    /**
     * Called from view when player presses "next" button
     * Where it is called will be changed in future sprints
     */
    public void handleAIPlay() {
        if (!uno.isUserTurn()){
            if(uno.playCard(gameView)) {
                gameView.updateDiscardPile(uno.getTopDiscardCard());
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {

                }
                checkRoundEnd();
            }
            gameView.updateDiscardPile(uno.getTopDiscardCard());
            //checkGameEnd();
        }

    }

    private void checkRoundEnd(){
        if (turnManager.getCurrentPlayer().hand().getCardCount() == 0){
            endRound();
        }
    }

    /**
     * Called from Uno when a user has no more cards left
     */
    private void endRound(){
        scoreTracker.calculate(turnManager.getAllPlayers());
        for (Player p : turnManager.getAllPlayers()){
            if (scoreTracker.getPlayerScore(p) >= settings.getWinningScore()){
                endGame();
            } else {
                uno.restart(); //play a new round
            }
        }
    }

    /**
     * Called from view when user clicks call Uno for themselves
     * A user should click call uno when it is their turn but before making a play that would leave them with 1 card
     */
    public void callUno(){
        if (uno.isUserTurn()){
            uno.callUno();

            //TODO: something here in the view to give feedback to user when they call uno
        }

    }




}
