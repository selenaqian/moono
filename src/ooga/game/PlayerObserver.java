package ooga.game;

import ooga.cards.Card;

import java.util.List;

public interface PlayerObserver {
    void updatePlayerHand(int playerId, List<Card> cardsLeft);
}
