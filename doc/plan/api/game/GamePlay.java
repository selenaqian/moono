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
     * Handle behavior when a user selects a card to play it
     * Called from the view
     */
    void makePlay();

    /**
     * When a user isn't able to play a card and they take a card from the draw pile
     * Called from the view
     */
    void drawCard();

    /**
     * Allows the human player to declare uno
     * Called from view class when the "uno" button is clicked
     */
    void callUno();

    /**
     * Once a player has no cards left, check if they have declared uno
     * If a player has not declared, then they must pick up more cards
     */
    void checkUno();


}