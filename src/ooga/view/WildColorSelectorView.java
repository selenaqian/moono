package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.cards.Suit;
import ooga.game.Uno;
import ooga.game.UnoActionApplier;
import ooga.game.WildcardObserver;

import static ooga.view.SetupView.DEFAULT_SPACING;

public class WildColorSelectorView implements WildcardObserver {
    public final int COLOR_SELECTOR_WIDTH = 500;
    public final int COLOR_SELECTOR_HEIGHT = 300;

    private UnoActionApplier myActionApplier; //call getActionApplier() from Uno
    private String myStylesheet;
    private Stage newStage;

    public WildColorSelectorView(UnoActionApplier actionApplier, String stylesheet){
        myActionApplier = actionApplier;
        myActionApplier.registerWildObserver(this);
        myStylesheet = stylesheet;
        newStage = new Stage();
    }

    @Override
    public void showColorSelector() {
        HBox root = new HBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        int size = Math.min(COLOR_SELECTOR_WIDTH/5, COLOR_SELECTOR_HEIGHT*2/3);
        for(Suit suit : Suit.values()) {
            Rectangle colorBox = new Rectangle(size, size);
            root.getChildren().add(colorBox);
            colorBox.getStyleClass().add(suit.toString());
            colorBox.setOnMouseClicked(e -> selectColorAction(suit));
        }
        //TODO: initialize javafx components here to represent the different colors

        Scene colorSelectorScene = new Scene(root, COLOR_SELECTOR_WIDTH, COLOR_SELECTOR_HEIGHT);
        colorSelectorScene.getStylesheets().add(myStylesheet);
        newStage.setScene(colorSelectorScene);
        newStage.show();
        //When color is clicked call actionApplier.setWildColor(stringThatMatchesSuitEnum);
    }

    private void selectColorAction(Suit suit) {
        myActionApplier.setWildColor(suit.toString());
        newStage.close();
    }
}
