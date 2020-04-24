package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.cards.Suit;
import ooga.game.SwapCardObserver;
import ooga.game.Uno;
import ooga.game.UnoActionApplier;
import ooga.player.Player;

import static ooga.view.SetupView.DEFAULT_SPACING;

public class SwapCardSelectorView implements SwapCardObserver {
    public final int COLOR_SELECTOR_WIDTH = 500;
    public final int COLOR_SELECTOR_HEIGHT = 300;

    private UnoActionApplier myActionApplier; //call getActionApplier() from Uno
    private String myStylesheet;
    private Stage newStage;


    public SwapCardSelectorView(UnoActionApplier actionApplier, String stylesheet){
        myActionApplier = actionApplier;
        myStylesheet = stylesheet;
        newStage = new Stage();
        myActionApplier.registerSwapObserver(this);
    }


    @Override
    public void showplayerandcardoptions() {
        HBox root = new HBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);
        int size = Math.min(COLOR_SELECTOR_WIDTH/5, COLOR_SELECTOR_HEIGHT*2/3);
        for(Player player: myActionApplier.getplayers()) {
            Rectangle colorBox = new Rectangle(size, size);
            root.getChildren().add(colorBox);
            colorBox.getStyleClass().add(player.toString());
            colorBox.setOnMouseClicked(e -> Action(player));
        }
        Scene Choose_player = new Scene(root, COLOR_SELECTOR_WIDTH, COLOR_SELECTOR_HEIGHT);
        Choose_player.getStylesheets().add(myStylesheet);
        newStage.setScene(Choose_player);
        newStage.show();
    }

    private void Action(Player player) {
        myActionApplier.applySwap();
        newStage.close();
    }
}
