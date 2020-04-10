package ooga.player;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.piles.Hand;
import ooga.player.*;
import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;

import java.util.*;

import static org.junit.Assert.assertEquals;


class playertest {

    private Hand playerhand = new Hand();
    private Player testplayer = new Player();

    private Card card1 = new Card(Suit.A, Value.ZERO);
    private Card card2 = new Card(Suit.A, Value.ZERO);
    private Card card3 = new Card(Suit.A, Value.ONE);
    private Card card4 = new Card(Suit.B, Value.ZERO);
    private Card card5 = new Card(Suit.B, Value.ONE);







/*
tests that all cards are in the hand
 */
    @Test
    void testhandaccuracy(){
        List<Card> play = new ArrayList<>();
        play.add(card1);
        play.add(card2);
        play.add(card3);
        play.add(card4);
        play.add(card5);


        assertEquals(5,testplayer.hand().getCardCount());

    }

/*
tests that a card is removed from the hand after play
 */
    @Test
    void testcardremoval(){
        List<Card> play = new ArrayList<>();
        play.add(card1);
        play.add(card2);
        play.add(card3);
        play.add(card4);
        play.add(card5);

        testplayer.hand().removeCard(card5);


        assertEquals(false, testplayer.hand().contains(card5));

        assertEquals(4, testplayer.hand().getCardCount());
    }

/*
tests that a card is added to the hand after drawing from the drawpile
 */
    @Test
    void testcardaddition(){
        List<Card> play = new ArrayList<>();
        play.add(card1);
        play.add(card2);
        play.add(card5);


        assertEquals(false,testplayer.hand().contains(card3));

        testplayer.hand().addCard(card3);


        assertEquals(4,testplayer.hand().getCardCount());

    }



}
