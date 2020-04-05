package ooga.piles;

import ooga.cards.Action;
import ooga.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Deck that typically starts with a complete set of Cards then shrinks.
 */
public class DrawPile extends Deck {

    /**
     * Creates a new shuffled DrawPile with a full standard Deck.
     */
    public DrawPile(){
        CardBuilder build = new CardBuilder();
        myCards = build.makeFullDeck();
        shuffle();
    }

    /**
     * Creates a new shuffled DrawPile with standard Deck and additional action cards as specified.
     * TODO: This will not be relevant until Sprint 2
     * @param actions
     */
    public DrawPile(List<Action> actions){
        CardBuilder build = new CardBuilder();
        myCards = build.makeDeck(actions);
        shuffle();
    }

    /**
     * Returns the specified number of Cards and removes them from Pile.
     * @param numCards
     * @return List<Card>
     */
    public List<Card> drawCards(int numCards){
        List<Card> out = new ArrayList<>();
        for(int i = 0; i < numCards; i++){
            out.add(myCards.pop());
        }
        return out;
    }
}
