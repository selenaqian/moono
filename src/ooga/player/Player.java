package ooga.player;

import ooga.cards.Card;

import ooga.piles.Hand;

import java.util.List;

public class Player {
    private Hand playerhand = new Hand();


    /**
     * returns a list of the player's hand
     *
     * @return
     */
    public Hand hand() {
        return playerhand;
    }


//    /**
//     * for AI player, computes the best card of play
//     * for manual player, obtains the choice of card from player(ie button click)
//     * @param   hand,card
//     * @return the chosen card of play
//     */
//    Card cardBeingPlayed(List<Card> hand, Card card){
//
//    }


//    /**
//     *for AI player, autogenerated
//     * for manual player, string input
//     * @return the player's chosen name
//     */
//    abstract String playerName(String name);


    /**
     * governs what card a player can play
     *
     * @return
     */
    public void playCard(Card handcard) {
        playerhand.removeCard(handcard);
    }


    public void takeCard(Card card) {
        playerhand.addCard(card);
    }

}

