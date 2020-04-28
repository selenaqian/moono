/**
 * This class creates the view at the end of a round. It shows that the round is over and allows the user to advance to the next round.
 * It also displays the new scores for each of the players.
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

import java.util.Map;

import static ooga.view.SetupView.*;
import static ooga.view.SetupView.DEFAULT_STYLESHEET;

public class EndRoundView extends EndView {
    private Button nextRoundButton;
    private Map<Integer, Integer> allPlayerScores;
    private UnoController myController;

    public EndRoundView(Stage stage, int playerNumber, Map playerScores, UnoController controller) {
        super(stage, playerNumber);
        allPlayerScores = playerScores;
        myController = controller;
        showEndScene();
    }

    /**
     * Creates and shows the end round scene. Displays text stating that the round is complete, shows the updated player
     * scores, and asks if the user wants to start the next round. Also has a button to start the next round.
     */
    @Override
    protected void showEndScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text roundComplete = makeText("roundComplete");
        VBox playerScoresText = makePlayerScoresText();
        Text startNextRound = makeText("startNextRound");

        nextRoundButton = new Button(myResources.getString("nextRoundButton"));
        nextRoundButton.setId("nextRoundButton");
        setButtonActions();

        root.getChildren().addAll(roundComplete, playerScoresText, startNextRound, nextRoundButton);
        Scene endScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        endScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(endScene);
        mainStage.show();
    }

    /**
     * Creates the text objects showing each player's score.
     * @return a VBox containing all of the players' scores.
     */
    private VBox makePlayerScoresText() {
        VBox playerScoresText = new VBox(DEFAULT_SPACING);
        playerScoresText.setAlignment(Pos.CENTER);
        for(int i : allPlayerScores.keySet()) {
            int score = allPlayerScores.get(i);
            String player = myResources.getString("player");
            String scoreLabel = " " + myResources.getString("score");
            Text currentScore = new Text(player + i + scoreLabel + score);
            currentScore.setId(player + i);
            playerScoresText.getChildren().add(currentScore);
        }
        return playerScoresText;
    }

    /**
     * Sets up the next round button to advance to the next round in the same window.
     */
    @Override
    protected void setButtonActions() {
        nextRoundButton.setOnMouseClicked(e -> myController.newRound());
    }

    /**
     * Used for testing. Allows test to access the button that advances to the next round.
     * @return the button that advances to the next round - goes back to a GameView.
     */
    public Button getNextRoundButton() {
        return nextRoundButton;
    }
}
