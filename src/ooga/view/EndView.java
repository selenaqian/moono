package ooga.view;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import static ooga.view.SetupView.DEFAULT_RESOURCES;

public abstract class EndView {
    protected Stage mainStage;
    protected ResourceBundle myResources;
    protected int winner;

    /**
     * Initializes the EndView instance variables.
     * @param stage the stage to load the EndView on - typically the current stage.
     * @param playerNumber the winner of the game.
     */
    protected EndView(Stage stage, int playerNumber) {
        mainStage = stage;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        winner = playerNumber;
    }

    /**
     * Creates a text object. Useful for all types of end scenes, as they need to create several text objects with subtitle styling.
     * Noticing now that it could be made more flexible by adding a style class parameter instead of setting the style always to subtitle.
     * @param resourceKey the key in the properties file to get the display text from.
     * @return the text object with the corresponding string as the text and "subtitle" class styling.
     */
    protected Text makeText(String resourceKey) {
        Text t = new Text(myResources.getString(resourceKey));
        t.setId(resourceKey + "Text");
        t.getStyleClass().add("subtitle");
        return t;
    }

    /**
     * Creates and shows the scene.
     */
    protected abstract void showEndScene();

    /**
     * Sets up the buttons in the end scene to work properly - advance to a new game or new round.
     */
    protected abstract void setButtonActions();
}
