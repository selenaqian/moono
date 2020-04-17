package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.player.AI_Player;
import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnoScoreTrackerTest {

    Uno uno = new Uno();
    GameSettings settings = new GameSettings();
    UnoScoreTracker scoreTracker = new UnoScoreTracker();

    AI_Player player = new AI_Player();


    @BeforeEach
    void setUpPlayerHand(){
        player.takecard(new Card(Suit.A, Value.ONE));
        player.takecard(new Card(Suit.A, Value.TWO));
        player.takecard(new Card(Suit.A, Value.REVERSE));
        player.takecard(new Card(Suit.A, Value.WILD));

    }

    @Test
    void calcPlayerScore() {

        assertEquals(73, scoreTracker.getPlayerScore(player));

    }
}