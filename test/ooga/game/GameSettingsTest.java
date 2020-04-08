package ooga.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSettingsTest {

    GameSettings settings = new GameSettings();

    @Test
    void testDefaultSettings(){
        assertEquals(4, settings.getNumPlayers());
        assertEquals(7, settings.getHandSize());
        assertEquals(500, settings.getWinningScore());
    }

    @Test
    void setNumPlayers() {
        settings.setNumPlayers(3);
        assertEquals(3, settings.getNumPlayers());
    }

    @Test
    void setHandSize() {
        settings.setHandSize(5);
        assertEquals(5, settings.getHandSize());
    }

    @Test
    void setWinningScore() {
        settings.setWinningScore(400);
        assertEquals(400, settings.getWinningScore());
    }
}