package ooga.config;

import ooga.game.*;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;
import ooga.player.AI_Player;
import ooga.player.ManualPlayer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JavaToXML class.
 * @author Tess Noonan (tcn6)
 */
class JavaToXMLTest {

    public static final String FILE_NAME = "saved_game.xml";

    private DiscardPile discard = new DiscardPile();
    private DrawPile draw = new DrawPile();
    private TurnManager tm = new UnoTurnManager(Arrays.asList(new ManualPlayer(), new AI_Player(), new AI_Player()));
    private GameSettings gs = new GameSettings();
    private ScoreTracker st = new UnoScoreTracker();
    private GameInfo basicGI = new GameInfo(tm, gs, draw, discard, st);

    private JavaToXML toXML = new JavaToXML();

    /**
     * Tests encode().
     * @throws IOException
     * TODO: At the moment this is throwing two exceptions.
     * TODO: An XML file is being saved but it's empty
     */
    @Test
    void encode() throws IOException {
        toXML.encode(basicGI, FILE_NAME);
    }
}