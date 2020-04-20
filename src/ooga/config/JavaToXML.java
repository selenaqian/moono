package ooga.config;



import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This class encodes a GameInfo instance to an XML file.
 * It is heavily based on the "XML De/Encoding" resource cited in the README.
 * @author Tess Noonan
 */
public class JavaToXML {
    private ResourceBundle myResources = ResourceBundle.getBundle("errors");

    //TODO: transfer these Strings to a properties file instead.
    public static final String FILE_PATH = "data/";
    //public static final String EXCEPTION_TEXT = "Exception! :"; sna19(modified this to use the error properties file so that error messages are not hardcoded

    /**
     * Creates new JavaToXML object.
     */
    public JavaToXML(){

    }

    /**
     * Encodes given GameInfo object into an XML file.
     * @param gameInfo
     * @param fileName
     * @throws IOException
     */
    public void encode(GameInfo gameInfo, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_PATH + fileName);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println(myResources.getString("Exception") + e.toString());
            }
        });
        encoder.writeObject(gameInfo);
        encoder.close();
        fos.close();
    }
}
