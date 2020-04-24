package ooga.config;

import ooga.game.*;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests GameInfo class.
 * There is only one test per method because the methods are all basic setters/getters.
 * @author Tess Noonan (tcn6)
 */
class GameInfoTest {

    private DiscardPile discard = new DiscardPile();
    private DrawPile draw = new DrawPile();
    private TurnManager tm = new UnoTurnManager(Arrays.asList(new ManualPlayer(), new AI_Player(), new AI_Player()));
    private GameSettings gs = new GameSettings();
    private ScoreTracker st = new UnoScoreTracker();
    private GameInfo basicGI = new GameInfo(tm, gs, draw, discard, st);
    private GameInfo empty = new GameInfo();

    /**
     * Tests getTurnManager().
     */
    @Test
    void getTurnManager() {
        assertTrue(basicGI.getTurnManager() instanceof TurnManager);
        assertEquals(tm, basicGI.getTurnManager());
    }

    /**
     * Tests getGameSettings().
     */
    @Test
    void getGameSettings() {
        assertTrue(basicGI.getGameSettings() instanceof GameSettings);
        assertEquals(gs, basicGI.getGameSettings());
    }

    /**
     * Tests getDrawPile().
     */
    @Test
    void getDrawPile() {
        assertTrue(basicGI.getDrawPile() instanceof DrawPile);
        assertEquals(draw, basicGI.getDrawPile());
    }

    /**
     * Tests getDiscardPile().
     */
    @Test
    void getDiscardPile() {
        assertTrue(basicGI.getDiscardPile() instanceof DiscardPile);
        assertEquals(discard, basicGI.getDiscardPile());
    }

    /**
     * Tests getScoreTracker().
     */
    @Test
    void getScoreTracker() {
        assertTrue(basicGI.getScoreTracker() instanceof ScoreTracker);
        assertEquals(st, basicGI.getScoreTracker());
    }

    /**
     * Tests setDiscardPile().
     */
    @Test
    void setDiscardPile() {
        empty.setDiscardPile(discard);
        assertEquals(discard, empty.getDiscardPile());
    }

    /**
     * Tests setScoreTracker().
     */
    @Test
    void setScoreTracker() {
        empty.setScoreTracker(st);
        assertEquals(st, empty.getScoreTracker());
    }

    /**
     * Tests setTurnManager().
     */
    @Test
    void setTurnManager() {
        empty.setTurnManager(tm);
        assertEquals(tm, empty.getTurnManager());
    }

    /**
     * Tests setGameSettings().
     */
    @Test
    void setGameSettings() {
        empty.setGameSettings(gs);
        assertEquals(gs, empty.getGameSettings());
    }

    /**
     * Tests setDrawPile().
     */
    @Test
    void setDrawPile() {
        empty.setDrawPile(draw);
        assertEquals(draw, empty.getDrawPile());
    }
}