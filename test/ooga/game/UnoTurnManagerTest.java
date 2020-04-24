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

    @BeforeEach
    void setUp(){
        players = new ArrayList<>();
        players.add(new ManualPlayer());
        players.add(new AI_Player());
        players.add(new AI_Player());
        players.add(new AI_Player());
        manager = new UnoTurnManager(players);
    }

    @org.junit.jupiter.api.Test
    void testAddedPlayers() {
        assertEquals(4, players.size());
    }

    @org.junit.jupiter.api.Test
    void testNextPlayer() {
        manager.getNextPlayer();
        assertEquals(players.get(1), manager.getCurrentPlayer());
    }

    @org.junit.jupiter.api.Test
    void testGetCurrentPlayer() {
        assertEquals(players.get(0), manager.getCurrentPlayer());
        manager.getNextPlayer();
        assertEquals(players.get(1), manager.getCurrentPlayer());
    }

}