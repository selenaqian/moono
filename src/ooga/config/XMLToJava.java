package ooga.config;

import ooga.game.GameSettings;
import ooga.game.ScoreTracker;
import ooga.game.TurnManager;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class decodes an XML file to a GameInfo instance.
 * It is heavily based on the "XML De/Encoding" resource cited in the README.
 * @author Tess Noonan
 */
public class XMLToJava {

    /**
     * Creates new XMLToJava object.
     */
    public XMLToJava() {

    }

    /**
     * Decodes an XML file to a GameInfo object.
     * @return GameInfo
     * @throws IOException
     */
    public GameInfo decode(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        XMLDecoder decoder = new XMLDecoder(fis);
        DiscardPile discard = (DiscardPile) decoder.readObject();
        DrawPile draw = (DrawPile) decoder.readObject();
        TurnManager tm = (TurnManager) decoder.readObject();
        ScoreTracker st = (ScoreTracker) decoder.readObject();
        GameSettings gs = (GameSettings) decoder.readObject();
        decoder.close();
        fis.close();

        GameInfo decodedInfo = new GameInfo(tm, gs, draw, discard, st);
        return decodedInfo;
    }
}
