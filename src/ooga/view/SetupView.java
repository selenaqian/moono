package ooga.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.GameSettings;

import java.util.ArrayList;

public class SetupView {
    public static final int DEFAULT_STAGE_WIDTH = 1000;
    public static final int DEFAULT_STAGE_HEIGHT = 600;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;
    public static final int DEFAULT_PLAYERS = 4;
    public static final int MIN_CARDS = 3;
    public static final int MAX_CARDS = 15;
    public static final int DEFAULT_CARDS = 7;
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 1000;
    public static final int DEFAULT_SCORE = 500;
    public static final int INCREMENT_ONE = 1;
    public static final int INCREMENT_50 = 50;
    public static final int DEFAULT_SPACING = 10;

    private GameSettings settings;
    private Stage mainStage;
    private Slider numberPlayersSlider;
    private Slider cardsPerPlayerSlider;
    private Slider scoreToWinSlider;
    private Button welcomeOkButton;

    /**
     * Initializes the SetupView object by initializing all of its instance variables.
     * Also displays the first scene.
     */
    public SetupView() {
        settings = new GameSettings();
        mainStage = new Stage();

        numberPlayersSlider = new Slider(MIN_PLAYERS, MAX_PLAYERS, DEFAULT_PLAYERS);
        cardsPerPlayerSlider = new Slider(MIN_CARDS, MAX_CARDS, DEFAULT_CARDS);
        scoreToWinSlider = new Slider(MIN_SCORE, MAX_SCORE, DEFAULT_SCORE);

        showWelcomeScene(mainStage);
        //TODO: initialize properties file for text
    }

    /**
     * Helper method to create and display the first scene.
     * @param stage this object's stage where the scene will be shown.
     */
    private void showWelcomeScene(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        Text welcomeText = new Text("welcome to moono"); // TODO: need a properties file for the text and css file for styling

        HBox numberPlayers = makeSlider(numberPlayersSlider, "# of players", DEFAULT_PLAYERS, INCREMENT_ONE);
        HBox cardsPerPlayer = makeSlider(cardsPerPlayerSlider, "# cards per player", DEFAULT_CARDS, INCREMENT_ONE);
        HBox scoreToWin = makeSlider(scoreToWinSlider, "max. score to win", DEFAULT_SCORE, INCREMENT_50);

        welcomeOkButton = new Button("okay!");
        welcomeOkButton.setOnAction(e -> welcomeOkPressed());

        root.getChildren().addAll(welcomeText, numberPlayers, cardsPerPlayer, scoreToWin, welcomeOkButton);

        Scene welcomeScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        stage.setScene(welcomeScene);
        stage.show();
    }

    /**
     * Helper method to handle actions once the okay! button is pressed on the welcome scene.
     */
    private void welcomeOkPressed() {
        settings.setHandSize((int) cardsPerPlayerSlider.getValue());
        settings.setNumPlayers((int) numberPlayersSlider.getValue());
        settings.setWinningScore((int) scoreToWinSlider.getValue());

        new GameView(settings.getNumPlayers(), settings.getHandSize(), new ArrayList<>(), mainStage);
        //TODO: transition to next scene
    }

    /**
     * Helper method to create the HBoxes with the sliders and their descriptions.
     * @param slide the slider being added to the box.
     * @param s the string description of the slider value.
     * @param defaultSelect the default selected slider value.
     * @param increment the increment of allowed movement - 1 for # of players and # of cards, 50 for score.
     * @return an HBox containing the description, the slider, and a display of the current value of the slider as it is moved.
     */
    private HBox makeSlider(Slider slide, String s, int defaultSelect, int increment) {
        HBox box = new HBox(DEFAULT_SPACING);
        Text text = new Text(s);
        Text slideValue = new Text(Integer.toString(defaultSelect));

        slide.setBlockIncrement(increment);
        slide.setMajorTickUnit(increment);
        slide.setMinorTickCount(0);
        slide.setSnapToTicks(true);

        // Code here based on the example in the JavaFX Slider Example (see README for link to resource)
        slide.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                slideValue.setText(Long.toString(Math.round((Double) new_val)));
            }
        });

        box.getChildren().addAll(text, slide, slideValue);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    /**
     * Used for testing. Allows test to access the okay! button in the starting scene.
     * @return the okay! button in welcomeScene.
     */
    public Button getWelcomeOkButton() {
        return welcomeOkButton;
    }

    /**
     * Used for testing. Allows test to access the gameSettings object to check that values have updated appropriately.
     * @return the gameSettings object instance in this.
     */
    public GameSettings getSettings() {
        return settings;
    }

    /**
     * Used for testing. Allows test to access numberPlayersSlider to change its value.
     * @return the slider that controls the number of players in the game.
     */
    public Slider getNumberPlayersSlider() {
        return numberPlayersSlider;
    }

    /**
     * Used for testing. Allows test to access cardsPerPlayerSlider to change its value.
     * @return the slider that controls the number of cards initially dealt to players in the game.
     */
    public Slider getCardsPerPlayerSlider() {
        return cardsPerPlayerSlider;
    }

    /**
     * Used for testing. Allows test to access getScoreToWinSlider to change its value.
     * @return the slider that controls the setting of the score win condition.
     */
    public Slider getScoreToWinSlider() {
        return scoreToWinSlider;
    }
}
