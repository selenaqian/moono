package ooga.player;

import ooga.cards.Card;

import java.util.List;

public class AI_Player implements Player {
    Card goodcard = null;
    int num_AI_players= 3;
    private List<Card> myCards;


    @Override
    public Card cardBeingPlayed(List<Card> hand, Card card) {
        return playcard(hand,card);
    }

    @Override
    public String playerName() {
        //       List<String> AI_names = new ArrayList<>();
        //       while(num_AI_players>0){
        String name = "Player "+ String.valueOf(num_AI_players);
        //          AI_names.add(name);
        //           num_AI_players-=1;
        //      }
        return name;
    }

    @Override
    public Card playcard(List<Card> hand, Card card) {

        for (Card playable: hand) {
            if((playable.toString().compareTo(card.toString()))>0){
                goodcard = playable;
            }
        }
        return goodcard;
    }


}

