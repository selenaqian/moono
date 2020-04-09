package ooga.game;

import ooga.player.Playeryrdeujhfgk;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UnoTurnManagerTest {

    private UnoTurnManager manager = new UnoTurnManager();

    @BeforeEach
    void setUp(){
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
        manager.addPlayer(new Playeryrdeujhfgk());
    }

    @org.junit.jupiter.api.Test
    void testAddPlayer() {
        manager.addPlayer(new Playeryrdeujhfgk());
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