package ooga.game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        newGame(stage);
    }

    public void newGame(Stage stage){
        stage.setTitle("UNO"); //TODO: read from resource property file
        stage.show();

        //set stage as initial view with game options, where the view returns some sort of root/node
        stage.setScene(View.startingScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
