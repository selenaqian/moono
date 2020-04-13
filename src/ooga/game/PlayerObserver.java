package ooga.game;

import ooga.piles.Hand;

public interface PlayerObserver {
    void updatePlayerHand(int playerId, Hand hand);
}
