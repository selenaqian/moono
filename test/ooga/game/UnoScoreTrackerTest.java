package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.player.AI_Player;
import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnoScoreTrackerTest {

    Uno uno = new Uno();
    GameSettings settings = new GameSettings();
    UnoScoreTracker scoreTracker;
    ArrayList<Player> players;

    AI_Player player1;
    AI_Player player2;
    AI_Player player3;



    @BeforeEach
    void setUpPlayers(){
        scoreTracker = new UnoScoreTracker();
        players = new ArrayList<>();
        player1 = new AI_Player();
        player2 = new AI_Player();
        player3 = new AI_Player();

        player1.setID(1);
        player2.setID(2);
        player3.setID(3);

        players.add(player1);
        players.add(player2);
        players.add(player3);


        player1.hand().addCard(new Card(Suit.A, Value.ONE));
        player1.hand().addCard(new Card(Suit.A, Value.TWO));
        player1.hand().addCard(new Card(Suit.A, Value.REVERSE));
        player1.hand().addCard(new Card(Suit.A, Value.WILD));


        player2.hand().addCard(new Card(Suit.A, Value.WILD));
    }

    @Test
    void calcPlayerScore() {
        scoreTracker.calculate(players);
        assertEquals(73, scoreTracker.getPlayerScore(player1));
        assertEquals(50, scoreTracker.getPlayerScore(player2));
        assertEquals(0, scoreTracker.getPlayerScore(player3));
    }

    @Test
    void calcAddedUpdatedScore(){
        scoreTracker.calculate(players);
        scoreTracker.calculate(players);
        assertEquals(146, scoreTracker.getPlayerScore(player1));
        assertEquals(100, scoreTracker.getPlayerScore(player2));
        assertEquals(0, scoreTracker.getPlayerScore(player3));

    }

}