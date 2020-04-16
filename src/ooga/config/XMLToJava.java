package ooga.config;

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
        GameInfo decodedInfo = (GameInfo) decoder.readObject();
        decoder.close();
        fis.close();
        return decodedInfo;
    }
}
