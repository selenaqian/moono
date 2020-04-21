package ooga.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.cards.Card;
import ooga.exceptions.OOGAException;
import ooga.player.Player;
import ooga.view.EndView;
import ooga.view.GameView;
import ooga.view.SetupView;
import java.util.ResourceBundle;


public class UnoController implements GameController {
    public static final double SECOND_DELAY = 1.5;
    private Timeline myAnimation = new Timeline();

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

        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();


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


    private void step(double elapsedTime){
        if(!turnManager.isHumanTurn()){
            handleAIPlay();
        }
    }



    /**
     * Called when a user selects a card from their hand
     * @param card card that was clicked in the view
     */
    public void handleCardClick(Card card){
        if(turnManager.isHumanTurn()){
            uno.checkUno();
            if(uno.playCard(card, turnManager.getCurrentPlayer())) {
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    throw new OOGAException(myResources.getString("NoSuch"),e);
                }
            }
        }
    }

    /**
     * Called when a user clicks on the draw pile
     */
    public void handleDrawPileClick(){
        if(turnManager.isHumanTurn()){
            uno.drawCard(turnManager.getCurrentPlayer());
            endTurn();
        }

    }

    /**
     * Called from view when player presses "next" button
     * Where it is called will be changed in future sprints
     */
    public void handleAIPlay() {
        if (!turnManager.isHumanTurn()){
            uno.AIDeclareUno();
            uno.checkUno();
            if(uno.playCard(turnManager.getCurrentPlayer())) {
                try {
                    endTurn();
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    throw new OOGAException(myResources.getString("NoSuch"),e);
                }

            }
            endTurn();
        }
    }

    private void endTurn(){

        checkRoundEnd();
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
        if (turnManager.isHumanTurn()){
            uno.callUno();

            //TODO: something here in the view to give feedback to user when they call uno
        }

    }



}
