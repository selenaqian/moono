package ooga.view;

import javafx.geometry.Pos;
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
    private Stage myStage;
    private Button slowDownButton;
    private Button speedUpButton;
    private Text speedChangeText;
    private Map<String, Pane> themeOptions;
    private CheckBox darkModeToggle;
    private Button saveCurrentButton;
    private Button newGameButton;
    private Button closeButton;

    public SettingsView() {
        this(DEFAULT_STYLESHEET);
    }

    public SettingsView(String stylesheet) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        myStylesheet = stylesheet;
        myStage = new Stage();
        themeOptions = new HashMap<>();

        VBox root = new VBox(DEFAULT_SPACING); // used to make scene later
        root.setAlignment(Pos.CENTER);
        VBox allSpeedUI = makeSpeedUI();
        VBox allThemeUI = makeThemeUI();
        HBox saveAndNewGameUI = makeSaveAndNewGameUI();
        closeButton = new Button(myResources.getString("closeButton"));
        closeButton.setOnMouseClicked(e -> myStage.close());

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
        speedChangeText = new Text(myResources.getString("speedOriginal"));
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
        for(String theme : allThemes) {
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
        }
        darkModeToggle = new CheckBox(myResources.getString("darkMode"));
        themeUI.getChildren().addAll(themeTitle, themeOptionsToClick, darkModeToggle);
        return themeUI;
    }

    private HBox makeSaveAndNewGameUI() {
        HBox save_newGameUI = new HBox(DEFAULT_SPACING);
        save_newGameUI.setAlignment(Pos.CENTER);
        saveCurrentButton = new Button(myResources.getString("save"));
        newGameButton = new Button(myResources.getString("newGameButton"));
        //TODO: set actions - each pops up its own new window with more stuff

        save_newGameUI.getChildren().addAll(saveCurrentButton, newGameButton);
        return save_newGameUI;
    }

    private void themeChosen(String theme) {
        // remove styling on all rectangles
        // get the correct pane, then get the rectangle (argument 0 of children)
        // add the new border styling
    }

}
