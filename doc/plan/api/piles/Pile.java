package ooga.piles;

import ooga.cards.Card;
import java.util.List;

/**
 * A Pile is an organized, mutable group of Cards.
 */
public interface Pile {

    /**
     * Add Card to the Pile.
     * @param card
     */
    void addCard(Card card);

    /**
     * Add multiple Cards to the Pile.
     * @param cards
     */
    void addCards(List<Card> cards);

    /**
     * Get all the Cards in the Pile.
     * @return List<Card>
     */
    List<Card> getAllCards();

    /**
     * Count number of Cards in Pile.
     * @return int
     */
    int getCardCount();

}
