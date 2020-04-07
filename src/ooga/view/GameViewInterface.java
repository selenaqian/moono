package ooga.view;

import ooga.cards.Card;

import java.util.List;

/**
 * A ViewInterface creates the user interface. It has the options for initial game setup and will be able to pass that
 * information to the backend.
 *
 * These initial options include slider values for the number of players (2-4), number of cards per player, the point
 * total that one player needs to reach before determining the overall winner, any custom rules selections, any special cards
 * selections, and the deck theme.
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
    public void updateHand(int cardsLeft);

    /**
     * Called by the controller. Updates the visual of what card is on top of the discard pile.
     *
     * @param card the new card for the top of the discard pile.
     */
    public void updateDiscardPile(Card card);
}
