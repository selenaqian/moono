package player;

import ooga.cards.Card;

import java.util.List;

public class ManualPlayer implements player {
    @Override
    public List<Card> hand() {
        return null;
    }

    @Override
    public Card cardBeingPlayed(List<Card> hand) {
        return null;
    }

    @Override
    public String playerName() {
        return null;
    }

    @Override
    public void playcard(List<Card> hand) {

    }
}
