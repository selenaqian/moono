package ooga.view;

import ooga.game.Uno;
import ooga.game.UnoActionApplier;
import ooga.game.WildcardObserver;

public class WildColorSelectorView implements WildcardObserver {

    private UnoActionApplier actionApplier; //call getActionApplier() from Uno

    public WildColorSelectorView(UnoActionApplier actionApplier){
        actionApplier.registerWildObserver(this);
    }

    @Override
    public void showColorSelector() {
        //TODO: initialize javafx components here to represent the different colors

        //When color is clicked call actionApplier.setWildColor(stringThatMatchesSuitEnum);


    }
}
