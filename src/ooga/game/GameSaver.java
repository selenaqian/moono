package ooga.game;

import ooga.config.GameInfo;

import java.io.IOException;

public interface GameSaver {
    void saveGame(String fileName) throws IOException;
    void loadGame(GameInfo gameInfo);
}
