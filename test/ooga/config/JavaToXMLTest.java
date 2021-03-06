package ooga.config;

import ooga.cards.*;
import ooga.game.*;
import ooga.piles.*;
import ooga.player.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JavaToXML class.
 * About XML: it keeps track of all the variables that differ from the default instantiation.
 *
 * @author Tess Noonan (tcn6)
 */
class JavaToXMLTest {

    public static final String PROPERTIES = "xml_strings";
    private ResourceBundle myResources = ResourceBundle.getBundle(PROPERTIES);

    private DiscardPile discard = new DiscardPile();
    private DrawPile draw = new DrawPile();
    private TurnManager tm = new UnoTurnManager();
    private GameSettings gs = new GameSettings();
    private ScoreTracker st = new UnoScoreTracker();

    private JavaToXML toXML = new JavaToXML();

    /**
     * Tests that encode() works for a GameInfo class with instantiated instance variables by checking that no exception
     * is thrown.
     */
    @Test
    void encodeBasic() {
        discard.addCard(new Card(Suit.C, Value.SIX));
        tm.changeDirection();
        gs.setHandSize(8);
        HashMap<Integer, Integer> scores = new HashMap<>();
        scores.put(1, 300);
        st.setScores(scores);

        GameInfo basicGI = new GameInfo(tm, gs, draw, discard, st);
        assertDoesNotThrow(() -> toXML.encode(basicGI, myResources.getString("testBasicGame")));
    }

    /**
     * Tests that encode() works for a GameInfo class without instantiated instance variables by checking that no
     * exception is thrown.
     */
    @Test
    void encodeDefault() throws IOException {
        GameInfo emptyGI = new GameInfo();
        assertDoesNotThrow(() -> toXML.encode(emptyGI, myResources.getString("testEmptyGame")));
    }
}