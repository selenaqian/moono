package ooga.game;

import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class UnoTurnManagerTest {

    private UnoTurnManager manager = new UnoTurnManager();

    @BeforeEach
    void setUp(){
        manager.addPlayer(new Player());
        manager.addPlayer(new Player());
        manager.addPlayer(new Player());
        manager.addPlayer(new Player());
    }

    @org.junit.jupiter.api.Test
    void testAddPlayer() {
        manager.addPlayer(new Player());
    }

//    @org.junit.jupiter.api.Test
//    void getFirstPlayer() {
//
//    }

    @org.junit.jupiter.api.Test
    void nextPlayer() {
    }

    @org.junit.jupiter.api.Test
    void getCurrentPlayer() {
    }

    @org.junit.jupiter.api.Test
    void changeDirection() {
    }
}