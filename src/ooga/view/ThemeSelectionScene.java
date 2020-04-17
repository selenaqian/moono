/**
 * Helper class that creates the scene for selecting the initial theme. This scene will include options for the classic Uno
 * colors, as well as the Duke theme and the Space theme. TODO: (later) add images
 * This class is package-private because only SetupView needs to access it.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static ooga.view.SetupView.*;

class ThemeSelectionScene {
    private ResourceBundle myResources;
    private Button themeOkButton;
    private List<Rectangle> backgroundBoxes;
    private List<Rectangle> colorBoxes;
    private String[] allThemeFiles;
    private int selectedThemeIndex;

    ThemeSelectionScene() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES);
        backgroundBoxes = new ArrayList<>();
        colorBoxes = new ArrayList<>();
        allThemeFiles = myResources.getString("themeFileNames").split(",");
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
            colorWrapper.getChildren().addAll(colorsBackground, colors);
            backgroundBoxes.add(colorsBackground);

            String[] themeColors = myResources.getString(theme).split(",");
            for(String color : themeColors) {
                Rectangle oneColor = new Rectangle(DEFAULT_STAGE_WIDTH/(allThemes.length+1), DEFAULT_STAGE_HEIGHT/(themeColors.length*2));
                oneColor.setFill(Paint.valueOf(color));
                colors.getChildren().add(oneColor);
                colorBoxes.add(oneColor);
            }
            //figure out the on hover thing - use for selection
            Text themeText = new Text(theme);
            selection.getChildren().addAll(colorWrapper,themeText);
            themes.getChildren().add(selection);
        }

        setClickStyling();
        Scene themeSelectionScene = new Scene(root, DEFAULT_STAGE_WIDTH, DEFAULT_STAGE_HEIGHT);
        themeSelectionScene.getStylesheets().add(DEFAULT_STYLESHEET);
        return themeSelectionScene;
    }

    private void setClickStyling() {
        for(int i=0; i < colorBoxes.size(); i++) {
            int index = i;
            colorBoxes.get(index).setOnMouseClicked(e -> {
                for(Rectangle r : backgroundBoxes) {
                    r.getStyleClass().removeAll(r.getStyleClass());
                }
                selectedThemeIndex = index/4;
                backgroundBoxes.get(selectedThemeIndex).getStyleClass().add("themeOptionSelected");
            });
        }
    }

    /**
     * Called in SetupView. Allows SetupView to access the button and know if it has been clicked, then call the correct
     * interactions from there.
     * @return the okay! button in the theme selection scene.
     */
    Button getThemeOkButton() {
        return themeOkButton;
    }

    /**
     * Allows other parts of view to see the desired changes to the stylesheet.
     * @return the file name for the stylesheet of the selected theme.
     */
    String getSelectedTheme() {
        return allThemeFiles[selectedThemeIndex];
    }

    //Methods below used in testing.

    /**
     * Used in testing. Allows test to check that the color values of these boxes updates properly.
     * @return the list of rectangles associated with the background boxes that add a border when selected.
     */
    public List<Rectangle> getBackgroundBoxes() {
        return backgroundBoxes;
    }

    /**
     * Used in testing. Allows test to find these boxes and click on the nodes, as well as check their color values.
     * @return the list of rectangles associated with the color boxes that display the colors of the theme.
     */
    public List<Rectangle> getColorBoxes() {
        return colorBoxes;
    }
}
