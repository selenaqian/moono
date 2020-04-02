package ooga.view;

/**
 * A SettingsView handles the user interface for settings including saving and resuming a game, starting the game over without
 * saving, dark mode, changing one particular color or image.
 *
 * @author Selena Qian
 */
public interface SettingsView {
    //TODO: so for listeners does there need to be a getter method for the button?

    /**
     * Gets information from a text field when saving a configuration for later.
     * @return the desired filename for the configuration.
     */
    public String getSaveFileName();
}
