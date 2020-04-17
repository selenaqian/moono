package ooga.player;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.piles.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class playertest {

    private Hand myhand = new Hand();
    private Player testplayer = new AI_Player();
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
        myhand.addCard(card1);
        myhand.addCard(card2);
        myhand.addCard(card3);
        myhand.addCard(card4);
        myhand.addCard(card5);



        assertEquals(5,testplayer.hand().getCardCount());

    }

/*
tests that a card is removed from the hand after play
 */
    @Test
    void testcardremoval(){
        myhand.addCard(card1);
        myhand.addCard(card2);
        myhand.addCard(card3);
        myhand.addCard(card4);
        myhand.addCard(card5);


        testplayer.removecard(card5);


        assertEquals(false, testplayer.hand().contains(card5));

        assertEquals(4, testplayer.hand().getCardCount());
    }

/*
tests that a card is added to the hand after drawing from the drawpile
 */
    @Test
    void testcardaddition(){
        myhand.addCard(card1);
        myhand.addCard(card2);
        myhand.addCard(card5);


        assertEquals(false,testplayer.hand().contains(card3));

        testplayer.takeCard(card3);


        assertEquals(4,testplayer.hand().getCardCount());

    }



}
