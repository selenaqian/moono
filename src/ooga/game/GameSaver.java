package ooga.game;

import ooga.config.GameInfo;

public interface GameSaver {
    void saveGame(GameInfo gameInfo);
    void loadGame(GameInfo gameInfo);
}
