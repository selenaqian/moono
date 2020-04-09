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
    }

    @Override
    public void pause() {
        //TODO: add a popup view to adjust game options mid-game
    }

    @Override
    public void endGame() {
        EndView endView = new EndView(mainStage);
    }

    //when a player selects a card
    public void handleCardClick(Card card){
        uno.playCard(card);
        gameView.updateHand(uno.getUserHand());
    }
}
