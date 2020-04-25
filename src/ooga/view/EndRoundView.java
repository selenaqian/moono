/**
 * This class creates the view at the end of a round. It shows that the round is over and allows the user to advance to the next round.
 * TODO: add something that shows the changes to scores?
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

import java.util.Map;

import static ooga.view.SetupView.*;
import static ooga.view.SetupView.DEFAULT_STYLESHEET;

public class EndRoundView extends EndView {
    private Button nextRoundButton;
    private Map scores;

    public EndRoundView(Stage stage, int playerNumber, Map playerScores) {
        super(stage, playerNumber);
        scores = playerScores;
        showEndScene();
    }

    @Override
    protected void showEndScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text roundComplete = makeText("roundComplete");

        //TODO: make the player stuff for the scores

        Text startNextRound = makeText("startNextRound");

        nextRoundButton = new Button(myResources.getString("nextRoundButton"));
        nextRoundButton.setId("nextRoundButton");
        setButtonActions();

        root.getChildren().addAll(roundComplete, startNextRound, nextRoundButton);
        Scene endScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        endScene.getStylesheets().add(DEFAULT_STYLESHEET);
        mainStage.setScene(endScene);
        mainStage.show();
    }

    @Override
    protected void setButtonActions() {
        //TODO: call actions to proceed to next round
    }
}
