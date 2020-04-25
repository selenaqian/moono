package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.cards.Suit;
import ooga.game.UnoActionApplier;
import ooga.game.WildcardObserver;

import java.util.ArrayList;
import java.util.List;

import static ooga.view.SetupView.DEFAULT_SPACING;

public class WildColorSelectorView implements WildcardObserver {
    public static final int SPECIAL_CARD_ACTION_WIDTH = 500;
    public static final int SPECIAL_CARD_ACTION_HEIGHT = 300;

    private UnoActionApplier myActionApplier;
    private String myStylesheet;
    private Stage newStage;
    private Scene colorSelectorScene;
    private List<Rectangle> colorBoxes;

    public WildColorSelectorView(UnoActionApplier actionApplier, String stylesheet){
        myActionApplier = actionApplier;
        myActionApplier.registerWildObserver(this);
        myStylesheet = stylesheet;
        newStage = new Stage();
        colorBoxes = new ArrayList<>();
    }

    @Override
    public void showColorSelector() {
        HBox root = new HBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        int size = Math.min(SPECIAL_CARD_ACTION_WIDTH /5, SPECIAL_CARD_ACTION_HEIGHT *2/3);
        for(Suit suit : Suit.values()) {
            Rectangle colorBox = new Rectangle(size, size);
            root.getChildren().add(colorBox);
            colorBoxes.add(colorBox);
            colorBox.getStyleClass().add(suit.toString());
            colorBox.setOnMouseClicked(e -> selectColorAction(suit));
        }

        colorSelectorScene = new Scene(root, SPECIAL_CARD_ACTION_WIDTH, SPECIAL_CARD_ACTION_HEIGHT);
        colorSelectorScene.getStylesheets().add(myStylesheet);
        newStage.setScene(colorSelectorScene);
        newStage.show();
    }

    private void selectColorAction(Suit suit) {
        myActionApplier.setWildColor(suit.toString());
        newStage.close();
    }

    /**
     * Method used to change the game theme mid-game. Only called within the view package, so method is package-private.
     * @param theme the name of the desired theme.
     */
    void setTheme(String theme) {
        myStylesheet = theme;
    }

    //Methods below used for testing.

    /**
     * Allows test to access the colored rectangles and perform actions on them.
     * @return the list containing the Rectangle objects that represent the different suit colors.
     */
    public List<Rectangle> getColorBoxes() {
        return colorBoxes;
    }
}
