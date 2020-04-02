package ooga.view;

import ooga.cards.Card;

/**
 * A GameplayView handles the visuals for the main bulk of the game. This interface (possibly later a class) will likely
 * have helper classes that handle the specific portions (e.g. drawPileView, discardPileView) to reduce the size of the class.
 */
public interface GameplayView {
    /**
     * Update view when a card is played, either by human player or AI. Will need to change the top card of the discard
     * pile and if human player, remove the card from their visible hand interface.
     * @param playedCard is the card that is played. Tells this what to put on top of the discard pile.
     */
    public void updateCardPlayed(Card playedCard);

    //TODO: same question about the action/event listeners - does it have to pass in the buttons and nodes to other classes?
}
