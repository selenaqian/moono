package ooga.player;
import ooga.game.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AI_PLayer extends Player {
    private ResourceBundle myResources=ResourceBundle.getBundle("default");
    private GameSettings mySettings;

    //myResources = ResourceBundle.getBundle();


    @Override
    String playerName() {
        return null;
    }

    @Override
    List AIplayerName(int numaiplayers) {
        List<String> ainames = new ArrayList<>();
        numaiplayers = mySettings.getNumPlayers()-1;

        ainames.add(myResources.getString("AInames"));

        return ainames;
    }
}
