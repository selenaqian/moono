package ooga.player;
<<<<<<< HEAD
import ooga.game.GameSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AI_Player extends Player {
    private ResourceBundle myResources=ResourceBundle.getBundle("default");
    private GameSettings mySettings;
    private ArrayList<Integer> AIIDs= new ArrayList<>();



    @Override
    String manualplayerName() {
        return null;
    }

    @Override
    List AIplayerName(int numaiplayers) {
        List<String> ainames = new ArrayList<>();
        numaiplayers = mySettings.getNumPlayers()-1;

        ainames.add(myResources.getString("AInames"));

        return ainames;
    }

    void setAIIDs(){
        AIIDs.add(2);
        AIIDs.add(3);
        AIIDs.add(4);
    }
=======

public class AI_Player extends Player {
    @Override
    String playerName() {
        return null;
    }
>>>>>>> 494f1fac140a5776e4420d34ac6dc497b0f679f8
}
