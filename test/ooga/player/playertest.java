package ooga.player;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class playertest {

    private Player testPlayer = new AI_Player();
    private Card card1 = new Card(Suit.A, Value.ZERO);
    private Card card2 = new Card(Suit.A, Value.ZERO);
    private Card card3 = new Card(Suit.A, Value.ONE);
    private Card card4 = new Card(Suit.B, Value.ZERO);
    private Card card5 = new Card(Suit.B, Value.ONE);








/**
tests that all cards are in the hand
 */
    @Test
    void testhandaccuracy(){
        List<Card> cards= new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        testPlayer.hand().addCards(cards);




        assertEquals(5, testPlayer.hand().getCardCount());

    }

/**
tests that a card is removed from the hand after play
 */
    @Test
    void testcardremoval(){
        List<Card> cards= new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        testPlayer.hand().addCards(cards);


        testPlayer.hand().removeCard(card5);


        assertEquals(false, testPlayer.hand().contains(card5));

        assertEquals(4, testPlayer.hand().getCardCount());
    }

/**
tests that a card is added to the hand after drawing from the drawpile
 */
    @Test
    void testcardaddition(){
        List<Card> cards= new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card5);
        testPlayer.hand().addCards(cards);



        assertEquals(false, testPlayer.hand().contains(card3));

        testPlayer.hand().addCard(card3);


        assertEquals(4, testPlayer.hand().getCardCount());

    }


    /**
     * tests that a hand properly resets
     */
    @Test
    void testRest(){
        List<Card> cards= new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        testPlayer.hand().addCards(cards);

        testPlayer.reset();

        assertEquals(0,testPlayer.hand().getCardCount());
    }




}
