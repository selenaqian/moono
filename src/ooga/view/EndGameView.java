/**
 * This class creates the view at the end of a game. It shows the player who won and a new game button that allows users
 * to immediately start a new game if they wish.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.UnoController;

import static ooga.view.SetupView.*;

public class EndGameView extends EndView {
    private Button newGameButton;

    public EndGameView(Stage stage, int playerNumber) {
        super(stage, playerNumber);
        showEndScene();
    }

    @Override
    protected void showEndScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text gameComplete = makeText("gameComplete");
        Text whoWon = makeText("winner");
        whoWon.setText(whoWon.getText() + winner);
        Text startNewGame = makeText("startNewGame");

        newGameButton = new Button(myResources.getString("newGameButton"));
        newGameButton.setId("newGameButton");
        setButtonActions();

        root.getChildren().addAll(gameComplete, whoWon, startNewGame, newGameButton);
        Scene endScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        endScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(endScene);
        mainStage.show();
    }

    @Override
    protected void setButtonActions() {
        newGameButton.setOnAction(e -> {
            new UnoController(mainStage);
        });
    }

    /**
     * Used for testing. Allows test to see the button and click on it, triggering further actions that can then also be checked.
     * @return the button that starts a new game.
     */
    public Button getNewGameButton() {
        return newGameButton;
    }
}
