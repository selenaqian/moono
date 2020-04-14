package ooga.game;

/**
 * Helper class to apply action card effects to the game of uno
 *@see UnoTurnManager skipping turns would be done by calling turnManager
 *turnManager also has a method for keeping track of direction and changing the direction
 * @see Uno setWildColor(String color) in Uno
 */
public class UnoActionApplier {

    private UnoTurnManager turnManager;
    private Uno uno;

    public UnoActionApplier(Uno uno, UnoTurnManager turnManager){
        this.turnManager = turnManager;
        this.uno = uno;
    }



}
