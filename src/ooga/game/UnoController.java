package ooga.game;

import javafx.stage.Stage;
import ooga.OOGAException;
import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.player.Player;
import ooga.view.EndView;
import ooga.view.GameView;
import ooga.view.SetupView;
import java.util.ResourceBundle;
import ooga.game.GameSettings;


public class UnoController implements GameController{
    GameSettings settings; //equivalent to model in MVC
    SetupView setupView;
    GameView gameView;
    Stage mainStage;
    Uno uno;
    UnoTurnManager turnManager;
    UnoScoreTracker scoreTracker;
    Player winner;
    private ResourceBundle myResources = ResourceBundle.getBundle("default");

    public UnoController(Stage stage){
        mainStage = stage;
        this.settings = new GameSettings();
        setupView = new SetupView(this, settings, mainStage); //so that view knows about controller and GameSettings
    }

    @Override
    public void start() {
        uno = new Uno(settings);
        uno.start();
        gameView = new GameView(uno, this, mainStage, settings.getTheme()); //TODO: change to interface
        turnManager = uno.getTurnManager();
        scoreTracker = new UnoScoreTracker();

    }

    @Override
    public void pause() {
        //TODO: add a popup view to adjust game options mid-game
    }

    @Override
    public void endGame() {
        //TODO: pass in the winner info to the view
        new EndView(mainStage);

    }

    void step(){
        if(!turnManager.isHumanTurn()){
            handleAIPlay();
        }
    }



    /**
     * Called when a user selects a card from their hand
     * @param card card that was clicked in the view
     */
    public void handleCardClick(Card card){
        if(uno.isUserTurn()){
            checkRoundEnd();
            if(uno.playCard(card, turnManager.getCurrentPlayer())) {
                //gameView.updateHand(uno.getUserHand());
                //gameView.updateDiscardPile(uno.getTopDiscardCard());
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    throw new OOGAException(myResources.getString("NoSuch"),e);
                }
                checkRoundEnd();

            }
            //gameView.updateHand(uno.getUserHand());
            //gameView.updateDiscardPile(uno.getTopDiscardCard());
        }
    }

    /**
     * Called when a user clicks on the draw pile
     */
    public void handleDrawPileClick(){
        uno.drawCard(turnManager.getCurrentPlayer());
        //gameView.updateHand(uno.getUserHand()); //TODO: delete this and use updatePlayerHand for observer pattern
        //gameView.updatePlayerHand();
    }

    /**
     * Called from view when player presses "next" button
     * Where it is called will be changed in future sprints
     */
    public void handleAIPlay() {
        if (!uno.isUserTurn()){
            checkRoundEnd();
            if(uno.playCard(turnManager.getCurrentPlayer())) {
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    throw new OOGAException(myResources.getString("NoSuch"),e);
                }
            }
            //gameView.updateDiscardPile(uno.getTopDiscardCard());
            //checkGameEnd();
        }

    }

    private void endTurn(){

    }

    private void checkRoundEnd(){
        if (uno.isOver()){
            endRound();
        }
    }

    /**
     * Called from Uno when a user has no more cards left
     */
    private void endRound(){
        scoreTracker.calculate(turnManager.getAllPlayers());
        for (Player p : turnManager.getAllPlayers()){
            //update scores in the view
            gameView.updateScore(p.getID(), scoreTracker.getPlayerScore(p));

            //check if a game can end
            if (scoreTracker.getPlayerScore(p) >= settings.getWinningScore()){
                winner = p;
                endGame();
            } else {
                //play a new round
                uno.restart();
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
