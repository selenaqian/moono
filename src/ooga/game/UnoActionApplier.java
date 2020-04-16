package ooga.game;

import ooga.cards.Value;

/**
 * Helper class to apply action card effects to the game of uno
 *@see UnoTurnManager skipping turns would be done by calling turnManager
 *turnManager also has a method for keeping track of direction and changing the direction
 * @see Uno setWildColor(String color) in Uno
 *
 * @author Mary Jiang (mvj6)
 * @author Tess Noonan (tcn6)
 * @author Suomo Ammah (sna19)
 */
public class UnoActionApplier {

    public static final int FOUR = 4;
    final int TWO = 2;

    private UnoTurnManager turnManager;
    private Uno uno;

    private WildcardObserver observer;

    /**
     * Create new UnoActionApplier
     * @param uno
     * @param turnManager
     */
    public UnoActionApplier(Uno uno, UnoTurnManager turnManager){
        this.turnManager = turnManager;
        this.uno = uno;

    }

    public void registerWildObserver(WildcardObserver o){
        this.observer = o;
    }

    /**
     * Notifies observers to make WildColorSelectorView show up
     */
    public void notifyWildObserver(){
        observer.showColorSelector();
    }

    /**
     * Called from WildColorSelectorView
     * @param selectedColor color that was clicked on by the user in the view
     */
    public void setWildColor(String selectedColor){
        uno.setWildColor(selectedColor);
    }

    /**
     * Applies the action if it's a special Card
     * Does nothing if it's a normal card.
     * @param value
     */
    public void applyAction(Value value){
        switch(value){
            case SKIP:
                applySkip();
                break;
            case REVERSE:
                applyReverse();
                break;
            case DRAW2:
                applyDraw2();
                break;
            case WILD:
                applyWild();
                break;
            case WILD4:
                applyWild4();
                break;
            default:
                //this is a normal card: do nothing
                return;
        }
    }

    /**
     * Skips turn of next player.
     */
    private void applySkip(){
        turnManager.nextPlayer();
        turnManager.nextPlayer();
    }

    /**
     * Reverses direction of the play.
     */
    private void applyReverse(){
        turnManager.changeDirection();

    }

    /**
     * Draws 2 cards to the next player and skips their turn.
     */
    private void applyDraw2(){
        turnManager.nextPlayer();
        for(int i=0;i<TWO;i++){
            uno.drawCard();
        }
        turnManager.nextPlayer();
    }

    /**
     * Changes color of play.
     */
    private void applyWild(){
        notifyWildObserver();
    }

    /**
     * Changes color of play, draws 4 cards to the next player, and skips their turn.
     */
    private void applyWild4(){
        applyWild();
        turnManager.nextPlayer();
        for(int i = 0; i < FOUR; i++){
            uno.drawCard();
        }
    }

}
