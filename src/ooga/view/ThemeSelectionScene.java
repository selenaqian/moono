/**
 * Helper class that creates the scene for selecting the initial theme. This scene will include options for the classic Uno
 * colors, as well as the Duke theme and the Space theme. TODO: (later) add functionality for these extra themes via stylesheets and add images
 * This class is package-private because only SetupView needs to access it.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

import static ooga.view.SetupView.*;

class ThemeSelectionScene {
    private ResourceBundle myResources;
    private Button themeOkButton;

    ThemeSelectionScene() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
    }

    /**
     * Called from SetupView when need to switch to this scene.
     * @return a scene with GUI for selecting which theme to use.
     */
    Scene makeThemeSelectionScene() {
        VBox root = new VBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        HBox themes = new HBox(DEFAULT_SPACING);
        themes.setAlignment(Pos.CENTER);

        Text themeSceneText = new Text(myResources.getString("themeSceneText"));
        themeSceneText.getStyleClass().add("subtitle");
        themeOkButton = new Button(myResources.getString("okay"));

        root.getChildren().addAll(themeSceneText, themes, themeOkButton);

        String[] allThemes = myResources.getString("themeOptions").split(",");
        for(String theme : allThemes) {
            VBox selection = new VBox(10);
            selection.setAlignment(Pos.CENTER);
            StackPane colorWrapper = new StackPane();
            VBox colors = new VBox();
            colors.setAlignment(Pos.CENTER);
            Rectangle colorsBackground = new Rectangle(DEFAULT_STAGE_WIDTH/4, DEFAULT_STAGE_HEIGHT/2);
            colorsBackground.getStyleClass().add("themeOption");
            colorWrapper.getChildren().addAll(colorsBackground, colors);

            /**for(String color : myResources.getString(theme).split(",")) {
                Rectangle oneColor = new Rectangle(DEFAULT_STAGE_WIDTH/4, DEFAULT_STAGE_HEIGHT/8);
                oneColor.setFill(Paint.valueOf(color));
                colors.getChildren().add(oneColor);
            }*/
            //figure out the on hover thing - use for selection
            Text themeText = new Text(theme);
            selection.getChildren().addAll(colorWrapper,themeText);
            themes.getChildren().add(selection);
        }

        Scene themeSelectionScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        themeSelectionScene.getStylesheets().add(DEFAULT_STYLESHEET);
        return themeSelectionScene;
    }
}
