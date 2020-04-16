package ooga.game;

import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class UnoTurnManagerTest {

    private UnoTurnManager manager;
    private List<Player> players;

    @BeforeEach
    void setUp(){
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        manager = new UnoTurnManager(players);
    }

    @org.junit.jupiter.api.Test
    void testAddedPlayers() {
        assertEquals(4, players.size());
    }

    @org.junit.jupiter.api.Test
    void testGetFirstPlayer() {
        assertEquals(players.get(0), manager.getFirstPlayer());
    }

    @org.junit.jupiter.api.Test
    void testNextPlayer() {
        manager.nextPlayer();
        assertEquals(players.get(1), manager.getCurrentPlayer());
    }

    @org.junit.jupiter.api.Test
    void testGetCurrentPlayer() {
        assertEquals(players.get(0), manager.getCurrentPlayer());
        manager.nextPlayer();
        assertEquals(players.get(1), manager.getCurrentPlayer());
    }

}