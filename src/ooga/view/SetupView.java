/**
 * Class that builds the welcome scenes GUI and gets user input to set up the game.
 *
 * @author Selena Qian
 */
package ooga.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ooga.game.GameSettings;
import ooga.game.UnoController;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

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
    public static final String DEFAULT_STYLESHEET = "stylesheets/default.css";
    public static final String DEFAULT_RESOURCES = "default";

    private GameSettings mySettings;
    private UnoController myController;
    private Stage mainStage;
    private Slider numberPlayersSlider;
    private Slider cardsPerPlayerSlider;
    private Slider scoreToWinSlider;
    private Button welcomeOkButton;
    private Button loadSavedButton;
    private ResourceBundle myResources;
    private RulesAndSpecialCardScene rulesAndSpecialCards;
    private ThemeSelectionScene themeSelection;

    public SetupView(Stage stage) {
        this(new UnoController(stage), new GameSettings(), stage);
    }

    /**
     * Initializes the SetupView object by initializing all of its instance variables.
     * Also displays the first scene.
     */
    public SetupView(UnoController controller, GameSettings settings, Stage stage) {
        myController = controller;
        mySettings = settings;
        mainStage = stage;
        rulesAndSpecialCards = new RulesAndSpecialCardScene();
        themeSelection = new ThemeSelectionScene();

        numberPlayersSlider = new Slider(MIN_PLAYERS, MAX_PLAYERS, DEFAULT_PLAYERS);
        cardsPerPlayerSlider = new Slider(MIN_CARDS, MAX_CARDS, DEFAULT_CARDS);
        scoreToWinSlider = new Slider(MIN_SCORE, MAX_SCORE, DEFAULT_SCORE);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);

        showWelcomeScene(mainStage);
    }

    /**
     * Helper method to create and display the first scene.
     * @param stage this object's stage where the scene will be shown.
     */
    private void showWelcomeScene(Stage stage) {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        Text welcomeText = new Text(myResources.getString("welcomeText")); // TODO: need a properties file for the text and css file for styling
        welcomeText.getStyleClass().add("title");
        welcomeText.setId("welcomeText");

        HBox numberPlayers = makeSlider(numberPlayersSlider, myResources.getString("numberPlayers"), DEFAULT_PLAYERS, INCREMENT_ONE);
        HBox cardsPerPlayer = makeSlider(cardsPerPlayerSlider, myResources.getString("cardsPerPlayer"), DEFAULT_CARDS, INCREMENT_ONE);
        HBox scoreToWin = makeSlider(scoreToWinSlider, myResources.getString("scoreToWin"), DEFAULT_SCORE, INCREMENT_50);

        welcomeOkButton = new Button(myResources.getString("okay"));
        welcomeOkButton.setOnAction(e -> welcomeOkPressed());
        loadSavedButton = new Button(myResources.getString("loadSaved"));
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("data"));
        loadSavedButton.setOnMouseClicked(e -> {
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            //TODO: send info to controller
        });

        root.getChildren().addAll(welcomeText, numberPlayers, cardsPerPlayer, scoreToWin, welcomeOkButton, loadSavedButton);

        Scene welcomeScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        welcomeScene.getStylesheets().add(DEFAULT_STYLESHEET);
        stage.setScene(welcomeScene);
        stage.show();
    }

    /**
     * Helper method to handle actions once the okay! button is pressed on the welcome scene.
     */
    private void welcomeOkPressed() {
        mySettings.setHandSize((int) cardsPerPlayerSlider.getValue());
        mySettings.setNumPlayers((int) numberPlayersSlider.getValue());
        mySettings.setWinningScore((int) scoreToWinSlider.getValue());

        Scene rulesAndSpecialScene = rulesAndSpecialCards.makeSelectionScene();
        mainStage.setScene(rulesAndSpecialScene);

        rulesAndSpecialCards.getRulesAndSpecialCardsOkButton().setOnAction(e -> rulesOkClicked());
    }

    /**
     * Helper method to handle actions once the okay! button is pressed on the rules and special cards selection scene.
     */
    private void rulesOkClicked() {
        List<String> ruleSelections = rulesAndSpecialCards.getRuleSelections();
        for(String s : ruleSelections) {
            //System.out.println(s); //was used for checking before getting reflection working
            mySettings.setRule(s); //TODO: fix reflection
        }

        mySettings.setSpecialCards(rulesAndSpecialCards.getSpecialCardSelections());

        Scene themeScene = themeSelection.makeThemeSelectionScene();
        mainStage.setScene(themeScene);

        themeSelection.getThemeOkButton().setOnAction(e -> themeOkClicked());
    }

    private void themeOkClicked() {
        mySettings.setTheme(themeSelection.getSelectedTheme());
        myController.start();
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
    public GameSettings getMySettings() {
        return mySettings;
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

    /**
     * Used for testing. Allows test to access the okay! button in the rules selection scene.
     * @return the okay! button in rules and special cards selection scene.
     */
    public Button getRulesOkButton() {
        return rulesAndSpecialCards.getRulesAndSpecialCardsOkButton();
    }

    /**
     * Used for testing. Allows test to access the okay! button in the theme selection scene.
     * @return the okay! button in theme selection scene.
     */
    public Button getThemeOkButton() {
        return themeSelection.getThemeOkButton();
    }

    /**
     * Used for testing. Allows test to access the theme selection object and the methods for getting different parts of the scene.
     * @return the ThemeSelection object that can then access other parts of the scene - see the ThemeSelection class for more
     * details on what the test needs to access.
     */
    public ThemeSelectionScene getThemeSelection() {
        return themeSelection;
    }
}
