package ooga.piles;

import ooga.cards.Card;

import java.util.List;

/**
 * This class instantiates Cards and compiles them into a List.
 */
public interface CardBuilder {

    /**
     * This method instantiates every possible Card combination and returns them as a List.
     * @return full deck of cards
     */
    List<Card> makeFullDeck();
}
