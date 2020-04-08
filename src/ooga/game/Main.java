package ooga.game;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.SetupView;

import java.util.Set;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        newGame();
    }

    public void newGame(){
        SetupView start = new SetupView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
