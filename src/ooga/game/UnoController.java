package ooga.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.cards.Card;
import ooga.config.GameInfo;
import ooga.config.GameSaver;
import ooga.config.JavaToXML;
import ooga.config.XMLToJava;
import ooga.exceptions.OOGAException;
import ooga.player.Player;
import ooga.view.EndGameView;
import ooga.view.EndRoundView;
import ooga.view.GameView;
import ooga.view.SetupView;
import ooga.view.SoundPlayer;

import java.io.IOException;
import java.util.ResourceBundle;


public class UnoController implements GameController, GameSaver {
    private Timeline myAnimation = new Timeline();
    private double speed;
    private ResourceBundle myResources = ResourceBundle.getBundle("default");
    private SoundPlayer soundPlayer;

    GameSettings settings; //equivalent to model in MVC
    SetupView setupView;
    GameView gameView;
    Stage mainStage;
    Uno uno;
    UnoTurnManager turnManager;
    UnoScoreTracker scoreTracker;
    Player winner;


    public UnoController(Stage stage){
        mainStage = stage;
        this.settings = new GameSettings();
        soundPlayer = new SoundPlayer();
        scoreTracker = new UnoScoreTracker();
        uno = new Uno(settings);
        uno.start();
        turnManager = uno.getTurnManager();
        speed = settings.getSpeed();
        gameView = new GameView(uno, this, mainStage, settings.getTheme()); //TODO: change to interface
        setupView = new SetupView(this, settings, mainStage); //so that view knows about controller and GameSettings
    }

    @Override
    public void start() {
        gameView.showGameScene();
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
        new EndGameView(mainStage, playerNumber);
    }

    @Override
    public void step(double elapsedTime){
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


    @Override
    public void changeSpeed(double speed){
        this.speed = speed;
        settings.setSpeed(speed);
        setupTimeline();
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
    @Override
    public void endRound(){
        scoreTracker.calculate(turnManager.getAllPlayers());
        for (Player p : turnManager.getAllPlayers()){
            //update scores in the view
            gameView.updateScore(p.getID(), scoreTracker.getPlayerScore(p));

            //check if a game can end
            if (scoreTracker.getPlayerScore(p) >= settings.getWinningScore()){
                winner = p;
                endGame(p.getID());
            } else {
                new EndRoundView(mainStage, p.getID(), scoreTracker.getScores(),this);
            }
        }
    }

    /**
     * Sets up new round of a game after the "next round" button is clicked in EndRoundView
     */
    public void newRound(){
        uno.restart();
        gameView = new GameView(uno, this, mainStage, settings.getTheme());
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


    @Override
    public void saveGame(String fileName) throws IOException {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameSettings(settings);
        gameInfo.setDrawPile(uno.getPileManager().getDrawPile());
        gameInfo.setDiscardPile(uno.getPileManager().getDiscPile());
        gameInfo.setScoreTracker(scoreTracker);
        gameInfo.setTurnManager(turnManager);

        JavaToXML encoder = new JavaToXML();
        encoder.encode(gameInfo, fileName);
    }

    @Override
    public void loadGame(String fileName) throws IOException {
        //TODO: use pile manager directly in GameInfo
        XMLToJava decoder = new XMLToJava();

        GameInfo gameInfo = decoder.decode(fileName);
        PileManager pileManager = new PileManager(gameInfo.getDrawPile(), gameInfo.getDiscardPile());
        uno = new Uno(gameInfo.getGameSettings(), pileManager, (UnoTurnManager) gameInfo.getTurnManager());
        uno.start();
    }
}
