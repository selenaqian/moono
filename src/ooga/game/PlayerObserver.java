package ooga.game;

import ooga.piles.Hand;

public interface PlayerObserver {
    void update(Hand hand);
}
