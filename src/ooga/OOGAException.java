package ooga;

/**
 * Represents an exceptional situation specific to this project.
 *
 * @author suomo
 */


public class OOGAException extends RuntimeException {

    /**
     * Create an exception based on a caught exception, with no additional message.
     */

    public OOGAException(Throwable exception){
        super(exception);
    }

    /**
     * Create an exception based on an issue in our code.
     */
    public OOGAException(String message,Throwable exception ) {
        super(String.format(message, exception));
    }


}
