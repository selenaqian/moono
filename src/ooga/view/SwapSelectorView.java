package ooga.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.game.UnoActionApplier;
import ooga.player.Player;

import java.util.List;

import static ooga.view.SetupView.DEFAULT_SPACING;

public class SwapSelectorView {
    private UnoActionApplier myActionApplier;
    private String myStylesheet;
    private Stage newStage;
    private List<Player> allPlayers; //TODO: need some way to get all the players

    public SwapSelectorView(UnoActionApplier actionApplier, String stylesheet, List players){
        myActionApplier = actionApplier;
//        myActionApplier.registerWildObserver(this);
        myStylesheet = stylesheet;
        newStage = new Stage();
        allPlayers = players;
    }

    public void showSwapSelector(int currentPlayer) { //TODO: is it possible to pass in a player? or playerID? that would be helpful to exclude yourself
        HBox root = new HBox(DEFAULT_SPACING);
        root.setAlignment(Pos.CENTER);

        for(Player p : allPlayers) {
            //make pane
            //make rectangle
            //make text for name
            //make text for # cards
            //put them in the pane
            //set up clicking
        }
        // show all other players
        // show the # of cards in their hand
        // set click action - what does it need to call? and then close
    }
}
