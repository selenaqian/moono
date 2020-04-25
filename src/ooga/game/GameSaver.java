package ooga.game;

import ooga.config.GameInfo;

public interface GameSaver {
    GameInfo saveGame();
    void loadGame(GameInfo gameInfo);
}
