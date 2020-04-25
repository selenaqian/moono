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

    AI_Player player1 = new AI_Player();
    AI_Player player2 = new AI_Player();
    AI_Player player3 = new AI_Player();



    @BeforeEach
    void setUpPlayerHands(){
        player1.hand().addCard(new Card(Suit.A, Value.ONE));
        player1.hand().addCard(new Card(Suit.A, Value.TWO));
        player1.hand().addCard(new Card(Suit.A, Value.REVERSE));
        player1.hand().addCard(new Card(Suit.A, Value.WILD));

        player2.hand().addCard(new Card(Suit.A, Value.WILD));
    }

    @Test
    void calcPlayerScore() {
        assertEquals(73, scoreTracker.getPlayerScore(player1));
        assertEquals(50, scoreTracker.getPlayerScore(player2));

    }

    @Test
    void testPlayerHasNoScore(){
        assertEquals(0, scoreTracker.getPlayerScore(player3));
    }
}