package ooga.view;

import ooga.cards.Card;

import java.util.List;

public class View implements ViewInterface {


    @Override
    public int getNumberPlayers() {
        return 0;
    }

    @Override
    public int getNumberCards() {
        return 0;
    }

    @Override
    public int getPointTotal() {
        return 0;
    }

    @Override
    public List<String> getSelectedRules() {
        return null;
    }

    @Override
    public List<Card> getSelectedCards() {
        return null;
    }

    @Override
    public String getSelectedTheme() {
        return null;
    }
}
