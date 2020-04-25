package ooga.game;

import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnoTurnManagerTest {

    private UnoTurnManager manager;
    private List<Player> players;

    ManualPlayer player1;
    AI_Player player2;
    AI_Player player3;


    @BeforeEach
    void setUp(){
        players = new ArrayList<>();
        player1 = new ManualPlayer();
        player2 = new AI_Player();
        player3 = new AI_Player();

        player1.setID(1);
        player2.setID(2);
        player3.setID(3);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        manager = new UnoTurnManager(players);
        manager.setHumanPlayer(player1);
    }

    @org.junit.jupiter.api.Test
    void testAddedPlayers() {
        assertEquals(3, players.size());
    }

    @org.junit.jupiter.api.Test
    void testNextPlayer() {
        manager.setCurrentPlayer(player1);
        manager.nextPlayer();
        assertEquals(player2, manager.getCurrentPlayer());
        manager.nextPlayer();
        assertEquals(player3, manager.getCurrentPlayer());
        manager.nextPlayer();
        assertEquals(player1, manager.getCurrentPlayer());
    }

    @org.junit.jupiter.api.Test
    void testGetCurrentPlayer() {
        manager.setCurrentPlayer(player1);
        assertEquals(players.get(0), manager.getCurrentPlayer());
    }

    @org.junit.jupiter.api.Test
    void testGetHumanPlayer() {
        manager.setCurrentPlayer(player1);
        assertEquals(player1, manager.getHumanPlayer());

        //make sure AI players are not recognized as manual
        manager.nextPlayer();
        assertFalse(manager.isHumanTurn());
    }


    @org.junit.jupiter.api.Test
    void testChangeDirection(){
        manager.changeDirection();
        assertEquals(-1, manager.getDirection());

        manager.setCurrentPlayer(player1);
        manager.nextPlayer();
        assertEquals(player3, manager.getCurrentPlayer());
        manager.nextPlayer();
        assertEquals(player2, manager.getCurrentPlayer());

    }



}