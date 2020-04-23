package ooga.view;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static ooga.view.SetupView.DEFAULT_RESOURCES;

public abstract class EndView {
    protected Stage mainStage;
    protected ResourceBundle myResources;
    protected int winner;

    protected EndView(Stage stage, int playerNumber) {
        mainStage = stage;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        winner = playerNumber;
    }

    protected Text makeText(String resourceKey) {
        Text t = new Text(myResources.getString(resourceKey));
        t.setId(resourceKey + "Text");
        t.getStyleClass().add("subtitle");
        return t;
    }

    protected abstract void showEndScene();
    protected abstract void setButtonActions();
}
