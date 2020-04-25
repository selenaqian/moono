/**
 * Helper class called by SettingsView to make the scenes and handle actions for saving the current game and starting a new
 * game. Created a helper class in order to move these responsibilities into another class.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.UnoController;

import java.io.IOException;
import java.util.ResourceBundle;

import static ooga.view.SetupView.*;
import static ooga.view.WildColorSelectorView.SPECIAL_CARD_ACTION_HEIGHT;
import static ooga.view.WildColorSelectorView.SPECIAL_CARD_ACTION_WIDTH;

public class MidGameSaveNew {
    UnoController myController;
    Stage myGameStage;
    Stage mySettingsStage;
    ResourceBundle myResources;

    TextField saveFileName;
    Button saveButton;
    Stage saveStage;

    Button overwriteYes;
    Button overwriteNo;
    Stage overwriteStage;

    MidGameSaveNew(Stage gameStage, Stage settingsStage, UnoController controller) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        myGameStage = gameStage;
        mySettingsStage = settingsStage;
        myController = controller;
    }

    /**
     * Method is package-private because it is called in the SettingsView class.
     * Creates the pop-up scene when the save button is clicked.
     */
    void showSaveScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        Text savePrompt = new Text(myResources.getString("savePrompt"));
        savePrompt.getStyleClass().add("subtitle2");
        saveFileName = new TextField();
        saveButton = new Button(myResources.getString("save2"));
        saveButton.setOnMouseClicked(e -> {
            try {
                myController.saveGame(saveFileName.getText());
            } catch (IOException ex) {
                // TODO(if time): popup for the error
            }
            saveStage.close();
        });
        root.getChildren().addAll(savePrompt, saveFileName, saveButton);
        Scene saveScene = new Scene(root, SPECIAL_CARD_ACTION_WIDTH, SPECIAL_CARD_ACTION_HEIGHT);
        saveScene.getStylesheets().add(DEFAULT_STYLESHEET);
        saveStage = new Stage();
        saveStage.setScene(saveScene);
        saveStage.show();
    }

    /**
     * Method is package-private because it is called in the SettingsView class.
     * Creates the pop-up scene when the new game button is clicked.
     */
    void showNewGameScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        Text overwrite = new Text(myResources.getString("overwrite"));
        overwrite.getStyleClass().add("subtitle2");

        HBox yesOrNo = new HBox(DEFAULT_SPACING);
        yesOrNo.setAlignment(Pos.CENTER);
        overwriteYes = new Button(myResources.getString("yes"));
        overwriteNo = new Button(myResources.getString("no"));
        yesOrNo.getChildren().addAll(overwriteYes, overwriteNo);
        overwriteYes.setOnMouseClicked(e -> {
            new UnoController(myGameStage);
            overwriteStage.close();
            mySettingsStage.close();
        });
        overwriteNo.setOnMouseClicked(e -> {
            new UnoController(new Stage());
            overwriteStage.close();
        });

        root.getChildren().addAll(overwrite, yesOrNo);
        Scene overwriteScene = new Scene(root, SPECIAL_CARD_ACTION_WIDTH, SPECIAL_CARD_ACTION_HEIGHT);
        overwriteScene.getStylesheets().add(DEFAULT_STYLESHEET);
        overwriteStage = new Stage();
        overwriteStage.setScene(overwriteScene);
        overwriteStage.show();
    }
}
