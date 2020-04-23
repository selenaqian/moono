package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static ooga.view.SetupView.*;
import static ooga.view.SetupView.DEFAULT_STYLESHEET;

public class EndRoundView extends EndView {
    private Button nextRoundButton;

    public EndRoundView(Stage stage, int playerNumber) {
        super(stage, playerNumber);
    }

    @Override
    protected void showEndScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text roundComplete = makeText("roundComplete");
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
