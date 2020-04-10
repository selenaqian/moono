package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.UnoController;

import static ooga.view.SetupView.*;

public class EndView {
    private Stage mainStage;
    private Button newGameButton;

    public EndView(Stage stage) {
        mainStage = stage;
        showEndScene();
    }

    private void showEndScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text gameComplete = new Text("game complete.\nstart new game?"); // TODO: need a properties file for the text and css file for styling
        gameComplete.setId("gameCompleteText");
        gameComplete.getStyleClass().add("subtitle");
        //TODO: figure out who won and put that on this screen too

        newGameButton = new Button("start new game");
        newGameButton.setId("newGameButton");
        setNewGameActions(newGameButton);
        //TODO: will later also support multiple rounds so will later need a continue button or something
        // distinct from new game

        root.getChildren().addAll(gameComplete, newGameButton);
        Scene endScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        endScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(endScene);
        mainStage.show();
    }

    private void setNewGameActions(Button newGame) {
        newGame.setOnAction(e -> {
            new UnoController(mainStage);
        });
    }

    public Button getNewGameButton() {
        return newGameButton;
    }
}
