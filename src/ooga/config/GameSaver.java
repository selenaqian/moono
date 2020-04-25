package ooga.config;

import ooga.config.GameInfo;

import java.io.IOException;

/**
 * Used with JavaToXML and XMLToJava to save a game
 * May be implemented by a Controller class
 */
public interface GameSaver {
    void saveGame(String fileName) throws IOException;
    void loadGame(String fileName) throws IOException;
}
