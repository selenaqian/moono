package ooga;


import javafx.application.Application;
import javafx.stage.Stage;
import ooga.game.Uno;
import ooga.game.UnoController;
import ooga.view.SetupView;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new UnoController(primaryStage);
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
