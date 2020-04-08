package ooga.game;

import ooga.cards.Card;

public interface GameModel {
    void start();
    void playCard(Card selectedCard);
    void drawCard();
}
