package ooga.game;

import javafx.stage.Stage;
import ooga.cards.Card;
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
            if(uno.playCard(card, gameView)) {
                gameView.updateHand(uno.getUserHand());
                gameView.updateDiscardPile(uno.getTopDiscardCard());
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {

                }
                endGame();
            }
            gameView.updateHand(uno.getUserHand());
            gameView.updateDiscardPile(uno.getTopDiscardCard());
            //checkGameEnd();
        }
    }

    /**
     * Called when a user clicks on the draw pile
     */
    public void handleDrawPileClick(){
        uno.drawCard();
        gameView.updateHand(uno.getUserHand());
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
                endGame();
            }
            gameView.updateDiscardPile(uno.getTopDiscardCard());
            //checkGameEnd();
        }


    }


    private void checkGameEnd(){
        if(uno.getNumCardsInPlayerHand() == 0){
            endGame();
        }
    }


}
