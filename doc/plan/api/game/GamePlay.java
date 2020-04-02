package ooga.game;

/**
 * Plays the game of Uno
 * Will likely be implemented as a concrete class
 */

public interface GamePlay{
    /**
     * Controls the flow of the game
     * Calls other classes in the game package
     */
    void play();


    // The following two methods are specific to uno and may be left out of the interface
    // They're helpful to teammates! :)
    /**
     * Once a player has no cards left, check if they have declared uno
     * If a player has not declared, then they must pick up more cards
     */
    void checkUno();

    /**
     * Allows the human player to declare uno
     * Called from view class when the "uno" button is clicked
     */
    void callUno();
}