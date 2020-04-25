package ooga.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.cards.Card;
import ooga.config.GameInfo;
import ooga.exceptions.OOGAException;
import ooga.player.Player;
import ooga.view.EndGameView;
import ooga.view.EndRoundView;
import ooga.view.GameView;
import ooga.view.SetupView;
import ooga.view.SoundPlayer;

import java.util.ResourceBundle;


public class UnoController implements GameController, GameSaver {
    private Timeline myAnimation = new Timeline();
    private double speed;

    GameSettings settings; //equivalent to model in MVC
    SetupView setupView;
    GameView gameView;
    Stage mainStage;
    Uno uno;
    UnoTurnManager turnManager;
    UnoScoreTracker scoreTracker;
    Player winner;
    private ResourceBundle myResources = ResourceBundle.getBundle("default");
    private SoundPlayer soundPlayer;

    public UnoController(Stage stage){
        mainStage = stage;
        this.settings = new GameSettings();
        setupView = new SetupView(this, settings, mainStage); //so that view knows about controller and GameSettings
        soundPlayer = new SoundPlayer();
        scoreTracker = new UnoScoreTracker();
    }

    @Override
    public void start() {
        uno = new Uno(settings);
        uno.start();
        gameView = new GameView(uno, this, mainStage, settings.getTheme()); //TODO: change to interface
        turnManager = uno.getTurnManager();
        speed = settings.getSpeed();
        setupTimeline();
    }

    @Override
    public void pause() {
        //TODO: pause the timeline - GameView calls this when button is clicked ~from selena
    }

    public void play() {
        //TODO: play the timeline again - but don't want use start(), need this to continue the game ~from selena
    }

    @Override
    public void endGame(int playerNumber) {
        //TODO: pass in the winner info to the view
        new EndGameView(mainStage, playerNumber);

    }


    private void step(double elapsedTime){
        gameView.myTurnColorChange(turnManager.getCurrentPlayer().getID());

        if(uno.isOver()){
            System.out.println(uno.isOver());
            endRound();
        }

        if(!turnManager.isHumanTurn()){
            if(uno.AIDeclareUno()){
                soundPlayer.playSound(String.valueOf(turnManager.getCurrentPlayer().getID()));
            }
            handleAIPlay();
        }
        uno.checkUno();

    }

    private void setupTimeline(){
        KeyFrame frame = new KeyFrame(Duration.seconds(speed), e -> step(speed));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }


    /**
     * Called when a user selects a card from their hand
     * @param card card that was clicked in the view
     */
    public void handleCardClick(Card card){
        if(turnManager.isHumanTurn()){
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
            uno.endTurn();
        }

    }

    /**
     * Called from view when player presses "next" button
     * Where it is called will be changed in future sprints
     */
    public void handleAIPlay() {
        if (!turnManager.isHumanTurn()){
            if(uno.playCard(turnManager.getCurrentPlayer())) {
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
     * Called from Uno when a user has no more cards left
     */
    private void endRound(){
        scoreTracker.calculate(turnManager.getAllPlayers());
        for (Player p : turnManager.getAllPlayers()){
            //update scores in the view
            gameView.updateScore(p.getID(), scoreTracker.getPlayerScore(p));

            new EndRoundView(mainStage, p.getID(), scoreTracker.getScores()); //TODO: show a new round screen in the view

            //check if a game can end
            if (scoreTracker.getPlayerScore(p) >= settings.getWinningScore()){
                winner = p;
                endGame(p.getID());
            } else {
                //play a new round
                uno.restart();
            }
        }
    }

    public void newRound(){
        uno.restart();
    }

    /**
     * Called from view when user clicks call Uno for themselves
     * A user should click call uno when it is their turn but before making a play that would leave them with 1 card
     */
    public void callUno(){
        if (turnManager.isHumanTurn()){
            uno.callUno();
            soundPlayer.playSound(String.valueOf(turnManager.getCurrentPlayer().getID()));
            //TODO: something here in the view to give feedback to user when they call uno
        }

    }


    /**
     * Used to change settings during a game
     * Changing score to play up until, access file saving/loading
     * @param updatedSettings
     */
    private void updateSettings(GameSettings updatedSettings){
        settings = updatedSettings;
    }

    public void changeSpeed(double speed){
        this.speed = speed;
        setupTimeline();

    }


    @Override
    public GameInfo saveGame() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameSettings(settings);
        gameInfo.setDrawPile(uno.getPileManager().getDrawPile());
        gameInfo.setDiscardPile(uno.getPileManager().getDiscPile());
        gameInfo.setScoreTracker(scoreTracker);
        gameInfo.setTurnManager(turnManager);

        return gameInfo;
    }

    @Override
    public void loadGame(GameInfo gameInfo) {
        //TODO: use pile manager directly in GameInfo
        PileManager pileManager = new PileManager(gameInfo.getDrawPile(), gameInfo.getDiscardPile());
        uno = new Uno(gameInfo.getGameSettings(), pileManager, (UnoTurnManager) gameInfo.getTurnManager());
        uno.start();
    }
}
