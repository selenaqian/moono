package ooga.view;

import ooga.cards.Card;

import java.util.List;

/**
 * A GameViewInterface creates the user interface for the gameplay. These visuals make up the main screen of the game, and
 * this interface includes methods that allow the controller and backend to call updates on the visuals when certain actions occur.
 *
 * @author Selena Qian
 */
public interface GameViewInterface {

    /**
     * Called by the controller. Updates the view of the hand for human players.
     *
     * @param cards the list of cards in the player's hand.
     */
    public void updateHand(List<Card> cards);

    /**
     * Called by the controller. Updates the view of the hand for AI players.
     *
     * @param cardsLeft the number of cards the AI player has left. The view doesn't need to know
     *                  what cards the AI player has, just how many are left.
     */
    public void updateHand(int playerNumber, int cardsLeft);

    /**
     * Called by the controller. Updates the view of the score for the given player.
     *
     * @param playerNumber the player to update the score for.
     * @param score the new score of the player.
     */
    public void updateScore(int playerNumber, int score);

    /**
     * Changes coloring of the text and circle to indicate which player's turn it is.
     * Called in the game package.
     * @param playerNumber the number of the player whose turn it now is.
     */
    public void myTurnColorChange(int playerNumber);

    /**
     * Allows other classes to call the main game view to show.
     * Needed in order to separate instantiation/creation from actually showing the scene.
     */
    public void showGameScene();
}
