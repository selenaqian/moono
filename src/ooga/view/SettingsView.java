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

import java.util.*;

import static ooga.view.SetupView.*;

public class SettingsView {
    private ResourceBundle myResources;
    private String myStylesheet;
    private GameView myGameView;
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
        this(DEFAULT_STYLESHEET, new GameView());
    }

    public SettingsView(String stylesheet, GameView gameView) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        myStylesheet = stylesheet;
        myGameView = gameView;
        myStage = new Stage();
        themeOptions = new HashMap<>();
        themeFileNames = new HashMap<>();
        saveNew = new MidGameSaveNew(myGameView.getStage(), myStage, myGameView.getController());

        //TODO: get this to work:
        //myStage.setOnCloseRequest(e -> myController.play());

        VBox root = new VBox(DEFAULT_SPACING); // used to make scene later
        root.setAlignment(Pos.CENTER);
        VBox allSpeedUI = makeSpeedUI();
        VBox allThemeUI = makeThemeUI();
        HBox saveAndNewGameUI = makeSaveAndNewGameUI();
        closeButton = new Button(myResources.getString("closeButton"));
        closeButton.setOnMouseClicked(e -> {
            myStage.close();
            //TODO: start timeline again
        });

        root.getChildren().addAll(allSpeedUI, allThemeUI, saveAndNewGameUI, closeButton);
        Scene mainScene = new Scene(root, DEFAULT_STAGE_WIDTH/2, DEFAULT_STAGE_HEIGHT);
        mainScene.getStylesheets().add(myStylesheet);
        myStage.setTitle(myResources.getString("settingsButton"));
        myStage.setScene(mainScene);
        myStage.show();
    }

    private VBox makeSpeedUI() {
        VBox speedUI = new VBox(DEFAULT_SPACING);
        speedUI.setAlignment(Pos.CENTER);
        HBox speedButtons = new HBox(DEFAULT_SPACING);
        speedButtons.setAlignment(Pos.CENTER);
        speedChangeText = new Text(myResources.getString("speedOriginal")); //TODO: change to display # of seconds?
        slowDownButton = new Button(myResources.getString("slowDownButton"));
        slowDownButton.setOnMouseClicked(e -> {
            //TODO: call function to slow down game
            speedChangeText.setText(/* get new speed value, use an instance var if needed + */myResources.getString("speedSuffix"));
        });
        speedUpButton = new Button(myResources.getString("speedUpButton"));
        speedUpButton.setOnMouseClicked(e -> {
            //TODO: call function to speed up game
            speedChangeText.setText(/* get new speed value + */myResources.getString("speedSuffix"));
        });
        speedButtons.getChildren().addAll(slowDownButton, speedUpButton);
        speedUI.getChildren().addAll(speedButtons, speedChangeText);
        return speedUI;
    }

    private VBox makeThemeUI() {
        VBox themeUI = new VBox(DEFAULT_SPACING);
        themeUI.setAlignment(Pos.CENTER);
        Text themeTitle = new Text(myResources.getString("theme"));
        themeTitle.getStyleClass().add("subtitle2");

        HBox themeOptionsToClick = new HBox(DEFAULT_SPACING);
        themeOptionsToClick.setAlignment(Pos.CENTER);
        String[] allThemes = myResources.getString("themeOptions").split(",");
        String[] allThemeFiles = myResources.getString("themeFileNames").split(",");
        for(int i=0; i < allThemes.length; i++) {
            String theme = allThemes[i];
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
            themeOptionsToClick.getChildren().add(currentPane);
            themeOptions.put(theme, currentPane);
            themeFileNames.put(theme, allThemeFiles[i]);
        }
        darkModeToggle = new CheckBox(myResources.getString("darkMode"));
        themeUI.getChildren().addAll(themeTitle, darkModeToggle, themeOptionsToClick);
        return themeUI;
    }

    private HBox makeSaveAndNewGameUI() {
        HBox save_newGameUI = new HBox(DEFAULT_SPACING);
        save_newGameUI.setAlignment(Pos.CENTER);
        saveCurrentButton = new Button(myResources.getString("save"));
        newGameButton = new Button(myResources.getString("newGameButton"));
        saveCurrentButton.setOnMouseClicked(e -> saveNew.showSaveScene());
        newGameButton.setOnMouseClicked(e -> saveNew.showNewGameScene());

        save_newGameUI.getChildren().addAll(saveCurrentButton, newGameButton);
        return save_newGameUI;
    }

    private void themeChosen(String theme) {
        for(String themeName : themeOptions.keySet()) {
            Node currentColorBox = themeOptions.get(themeName).getChildren().get(0);
            currentColorBox.getStyleClass().removeAll(currentColorBox.getStyleClass());
        }
        Node colorBox = themeOptions.get(theme).getChildren().get(0);
        colorBox.getStyleClass().add("themeOptionSelected");
        if(!darkModeToggle.isSelected()) {
            myGameView.setTheme(themeFileNames.get(theme));
        }
        else {
            String themeFileName = themeFileNames.get(theme);
            themeFileName = themeFileName.substring(0, themeFileName.length()-4);
            myGameView.setTheme(themeFileName + "_darkMode.css");
        }
        //TODO: make the css files
    }
}
