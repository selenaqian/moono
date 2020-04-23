package ooga.game;

import ooga.cards.Card;
import ooga.player.Player;
import ooga.view.GameView;

import java.util.List;

public interface GameModel {
    void start();
    void restart();
    boolean playCard(Card selectedCard, Player player);
    void drawCard(Player player);

}
