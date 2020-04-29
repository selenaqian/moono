/**
 * Class that creates the mid-game visual for changing options, including speed and theme, and allowing for saving or
 * starting a new game.
 *
 * I think this feature is well-designed because the separation of the creation of each of the visual sections makes it
 * readable and easy to follow. Objects of this class are instantiated when the settings button in the GameView is pressed,
 * and this class then handles all of the actions related to the visual objects in it. This class demonstrates encapsulation
 * in multiple ways — both by handling everything related to its own visual objects and by telling other classes to do certain
 * functions, such as setting the new game speed when the appropriate buttons are pressed.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.GameSettings;
import ooga.game.UnoController;

import java.util.*;

import static ooga.view.SetupView.*;

public class SettingsView {
    public static final int POSITIVE = 1;
    public static final int NEGATIVE = -1;
    public static final int FASTEST_SPEED = 1;
    public static final double SPEED_INCREMENT = 0.5;

    private ResourceBundle myResources;
    private String myStylesheet;
    private GameView myGameView;
    private GameSettings mySettings;

    private Stage myStage;
    private Button slowDownButton;
    private Button speedUpButton;
    private Text speedChangeText;
    private Map<String, Pane> themeOptions;
    private Map<String, String> themeFileNames;
    private CheckBox darkModeToggle;
    private Button saveCurrentButton;
    private Button newGameButton;
    private Button closeButton;
    private MidGameSaveNew saveNew;

    public SettingsView() {
        this(DEFAULT_STYLESHEET, new GameView(), new GameSettings());
    }

    public SettingsView(String stylesheet, GameView gameView, GameSettings settings) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        myStylesheet = stylesheet;
        myGameView = gameView;
        mySettings = settings;

        myStage = new Stage();
        themeOptions = new HashMap<>();
        themeFileNames = new HashMap<>();
        saveNew = new MidGameSaveNew(myGameView.getStage(), myStage, myGameView.getController());
        showSettingsView();
    }

    /**
     * Helper method to contain and put together all of the elements in this scene.
     */
    private void showSettingsView() {
        myStage.setOnCloseRequest(e -> myGameView.getController().play());

        VBox root = new VBox(DEFAULT_SPACING); // used to make scene later
        root.setAlignment(Pos.CENTER);
        VBox allSpeedUI = makeSpeedUI();
        VBox allThemeUI = makeThemeUI();
        HBox saveAndNewGameUI = makeSaveAndNewGameUI();
        closeButton = new Button(myResources.getString("closeButton"));
        closeButton.setOnAction(e -> {
            myStage.close();
        });

        root.getChildren().addAll(allSpeedUI, allThemeUI, saveAndNewGameUI, closeButton);
        Scene mainScene = new Scene(root, DEFAULT_STAGE_WIDTH/2, DEFAULT_STAGE_HEIGHT);
        mainScene.getStylesheets().add(myStylesheet);
        myStage.setTitle(myResources.getString("settingsButton"));
        myStage.setScene(mainScene);
        myStage.show();
    }

    /**
     * Helper method to create the buttons for slowing down and speeding up, and to set their actions.
     * @return the VBox containing the two buttons and an indicator of the current speed.
     */
    private VBox makeSpeedUI() {
        VBox speedUI = new VBox(DEFAULT_SPACING);
        speedUI.setAlignment(Pos.CENTER);
        HBox speedButtons = new HBox(DEFAULT_SPACING);
        speedButtons.setAlignment(Pos.CENTER);
        speedChangeText = new Text(mySettings.getSpeed() + myResources.getString("speedSuffix")); //TODO: change to display # of seconds?
        slowDownButton = new Button(myResources.getString("slowDownButton"));
        slowDownButton.setOnAction(e -> {
            changeSpeed(POSITIVE);
        });
        speedUpButton = new Button(myResources.getString("speedUpButton"));
        speedUpButton.setOnAction(e -> {
            if(mySettings.getSpeed() > FASTEST_SPEED) {
                changeSpeed(NEGATIVE);
            }
        });
        speedButtons.getChildren().addAll(slowDownButton, speedUpButton);
        speedUI.getChildren().addAll(speedButtons, speedChangeText);
        return speedUI;
    }

    /**
     * Slows down or speeds up the speed of the game.
     * @param sign positive 1 to slow down, negative 1 to speed up.
     */
    private void changeSpeed(int sign) {
        double newSpeed = mySettings.getSpeed() + SPEED_INCREMENT *sign;
        myGameView.getController().changeSpeed(newSpeed);
        mySettings.setSpeed(newSpeed);
        speedChangeText.setText(newSpeed + myResources.getString("speedSuffix"));
    }

    /**
     * Helper method to handle creating the entire theme portion of the scene.
     * @return the VBox containing the section title (theme:), dark mode checkbox, and theme option rectangles.
     */
    private VBox makeThemeUI() {
        VBox themeUI = new VBox(DEFAULT_SPACING);
        themeUI.setAlignment(Pos.CENTER);
        Text themeTitle = new Text(myResources.getString("theme"));
        themeTitle.getStyleClass().add("subtitle2");
        Text themeInstructions = new Text(myResources.getString("themeSub"));

        HBox themeOptionsToClick = new HBox(DEFAULT_SPACING);
        themeOptionsToClick.setAlignment(Pos.CENTER);
        String[] allThemes = myResources.getString("themeOptions").split(",");
        String[] allThemeFiles = myResources.getString("themeFileNames").split(",");
        for(int i=0; i < allThemes.length; i++) {
            String theme = allThemes[i];
            Pane currentPane = makeThemeOption(allThemes, theme);
            themeOptionsToClick.getChildren().add(currentPane);
            themeOptions.put(theme, currentPane);
            themeFileNames.put(theme, allThemeFiles[i]);
        }

        darkModeToggle = new CheckBox(myResources.getString("darkMode"));
        themeUI.getChildren().addAll(themeTitle, themeInstructions, darkModeToggle, themeOptionsToClick);
        return themeUI;
    }

    /**
     * Helper method to create the visual and interactions for one theme option.
     * Creates a square with the theme name on top.
     * @param allThemes the array of theme names.
     * @param theme the theme to create a Pane for.
     * @return the Pane containing the square and name.
     */
    private Pane makeThemeOption(String[] allThemes, String theme) {
        Pane currentPane = new StackPane();
        int sceneWidth = DEFAULT_STAGE_WIDTH/2;
        int rectSize = sceneWidth/(allThemes.length + 1);
        Color currentColor = Color.valueOf(myResources.getString(theme).split(",")[0]);

        Rectangle colorBox = new Rectangle(rectSize, rectSize, currentColor);
        colorBox.setOnMouseClicked(e -> themeChosen(theme));

        Text colorText = new Text(theme);
        colorText.getStyleClass().add("settingsThemeText");
        colorText.setOnMouseClicked(e -> themeChosen(theme));

        currentPane.getChildren().addAll(colorBox, colorText);
        return currentPane;
    }

    /**
     * Helper method to create the buttons for saving the current game and starting up a new game in the middle of the game.
     * @return the HBox containing these two buttons.
     */
    private HBox makeSaveAndNewGameUI() {
        HBox save_newGameUI = new HBox(DEFAULT_SPACING);
        save_newGameUI.setAlignment(Pos.CENTER);
        saveCurrentButton = new Button(myResources.getString("save"));
        newGameButton = new Button(myResources.getString("newGameButton"));
        saveCurrentButton.setOnAction(e -> saveNew.showSaveScene());
        newGameButton.setOnAction(e -> saveNew.showNewGameScene());

        save_newGameUI.getChildren().addAll(saveCurrentButton, newGameButton);
        return save_newGameUI;
    }

    /**
     * Helper method to set the actions that should occur when one of the themes is chosen.
     * Updates the style of the box to show that it has been selected and updates the theme in GameView and GameSettings.
     * @param theme the new desired theme.
     */
    private void themeChosen(String theme) {
        for(String themeName : themeOptions.keySet()) {
            Node currentColorBox = themeOptions.get(themeName).getChildren().get(0);
            currentColorBox.getStyleClass().removeAll(currentColorBox.getStyleClass());
        }
        Node colorBox = themeOptions.get(theme).getChildren().get(0);
        colorBox.getStyleClass().add("themeOptionSelected");

        String themeFileName = themeFileNames.get(theme);
        if(darkModeToggle.isSelected()) {
            themeFileName = themeFileName.substring(0, themeFileName.length()-4) + "_darkMode.css";
        }
        myGameView.setTheme(themeFileName);
        mySettings.setTheme(themeFileName);
    }

    //Methods below used for testing.

    /**
     * Allows test to access the game settings and verify that speed has changed appropriately.
     * @return the GameSettings associated with this SettingsView and the game as a whole.
     */
    public GameSettings getSettings() {
        return mySettings;
    }

    /**
     * Allows test to access the theme options and check that clicking on those properly affects the GameView.
     * @return the map of theme option names to their panes in the scene.
     */
    public Map<String, Pane> getThemeOptions() {
        return themeOptions;
    }

    /**
     * Allows test to access the save and new popups to check that they do actually show up.
     * @return the MidGameSaveNew object that will then allow for checking of elements inside it.
     */
    public MidGameSaveNew getSaveNew() {
        return saveNew;
    }
}
