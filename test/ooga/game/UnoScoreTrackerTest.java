package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnoScoreTrackerTest {

    Uno uno = new Uno();
    GameSettings settings = new GameSettings();
    UnoScoreTracker scoreTracker = new UnoScoreTracker();

    Player player = new Player();


    @BeforeEach
    void setUpPlayerHand(){
        player.takeCard(new Card(Suit.A, Value.ONE));
        player.takeCard(new Card(Suit.A, Value.TWO));
        player.takeCard(new Card(Suit.A, Value.REVERSE));
        player.takeCard(new Card(Suit.A, Value.WILD));

    }

    @Test
    void calcPlayerScore() {

        assertEquals(73, scoreTracker.getPlayerScore(player));

    }
}