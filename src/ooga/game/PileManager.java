package ooga.game;

import ooga.cards.Card;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;

public class PileManager {
    private DiscardPile discPile;
    private DrawPile drawPile;

    public PileManager(){
        this.discPile = new DiscardPile();
        this.drawPile = new DrawPile();
    }

    /**
     * Called when a user makes a play with a card
     * TODO: change showTopCard() to make sure it actually removes the card from the pile
     */
    public Card removeCard(){
        return discPile.showTopCard();
    }


}
