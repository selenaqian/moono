package ooga.game;

import ooga.player.Playeryrdeujhfgk;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UnoTurnManagerTest {

    private UnoTurnManager manager;
    private List<Player> players;

    @BeforeEach
    void setUp(){
<<<<<<< HEAD
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
    }

    @org.junit.jupiter.api.Test
    void testAddPlayer() {
        manager.addPlayer(new Playeryrdeujhfgk());
=======
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
>>>>>>> master
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