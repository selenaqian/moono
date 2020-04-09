package ooga.game;

import javafx.stage.Stage;
import ooga.view.EndView;
import ooga.view.GameView;
import ooga.view.GameViewInterface;
import ooga.view.SetupView;

public class UnoController implements GameController{
    GameSettings settings; //equivalent to model in MVC
    SetupView setupView;

    public UnoController(Stage stage){
        this.settings = new GameSettings();
        setupView = new SetupView(this, settings, stage); //so that view knows about controller and GameSettings
    }

    @Override
    public void start() {
        Uno game = new Uno();
        game.start();
        //TODO: possibly update view here?
    }

    @Override
    public void pause() {
        //TODO: add a popup view to adjust game options mid-game
    }

    @Override
    public void endGame() {
        EndView endView = new EndView();
    }
}
