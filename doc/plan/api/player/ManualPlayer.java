package player;

import ooga.cards.Card;

import java.util.List;

public class ManualPlayer implements player {
    @Override
    public List<Card> hand() {
        return null;
    }

    @Override
    public Card cardBeingPlayed(List<Card> hand, Card card) {
        return null;
    }


    @Override
    public List playerName() {
        return null;
    }

    @Override
    public Card playcard(List<Card> hand, Card card) {

        return card;
    }


}
