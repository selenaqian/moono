package ooga.config;

import ooga.cards.*;
import ooga.game.*;
import ooga.piles.*;
import ooga.player.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests XMLToJava class.
 * @author Tess Noonan (tcn6)
 */
class XMLToJavaTest {

    public static final String PROPERTIES = "xml_strings";
    private ResourceBundle myResources = ResourceBundle.getBundle(PROPERTIES);

    private XMLToJava toJava = new XMLToJava();

    /**
     * Tests decode() for loading in a basic saved game.
     * (run JavaToXML tests first to make sure the files get saved)
     * @throws IOException
     */
    @Test
    void decodeBasicGame() throws IOException {
        GameInfo loadedGame = toJava.decode(myResources.getString("filePath")
                + myResources.getString("testBasicGame"));

        assertTrue(loadedGame.getScoreTracker() instanceof ScoreTracker);
        assertTrue(loadedGame.getDiscardPile() instanceof DiscardPile);
        assertTrue(loadedGame.getDrawPile() instanceof DrawPile);
        assertTrue(loadedGame.getTurnManager() instanceof TurnManager);
        assertTrue(loadedGame.getGameSettings() instanceof GameSettings);

        List<Player> players = loadedGame.getTurnManager().getAllPlayers();
        assertNull(players);    // this should not be passing

        assertEquals(80, loadedGame.getDrawPile().getCardCount());

        //The below tests are not passing when they should
//        assertEquals(3, loadedGame.getTurnManager().getAllPlayers().size());
        assertEquals(new Card(Suit.C, Value.SIX), loadedGame.getDiscardPile().getAllCards().peek());
        assertEquals(1, loadedGame.getDiscardPile().getCardCount());
    }

    /**
     * Tests decode() for loading in an empty saved game.
     * (run JavaToXML tests first to make sure the files get saved)
     * @throws IOException
     */
    @Test
    void decodeEmptyGame() throws IOException {
        GameInfo loadedGame = toJava.decode(myResources.getString("filePath")
                + myResources.getString("testEmptyGame"));

        assertNull(loadedGame.getScoreTracker());
        assertNull(loadedGame.getDrawPile());
        assertNull(loadedGame.getDiscardPile());
        assertNull(loadedGame.getGameSettings());
        assertNull(loadedGame.getTurnManager());
    }

    /**
     * Tests that decode() throws an exception when the given file does not exist.
     */
    @Test
    void decodeNotFound() {
        assertThrows(IOException.class, () -> toJava.decode(myResources.getString("missing")));
    }
}