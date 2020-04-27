package ooga.game;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.piles.DrawPile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PileManagerTest {

    Stack<Card> testCards = new Stack<Card>();
    DrawPile drawPile;
    PileManager piles;

    @BeforeEach
    void makePiles(){
        testCards.add(new Card(Suit.A, Value.ONE));
        testCards.add(new Card(Suit.B, Value.TWO));
        testCards.add(new Card(Suit.C, Value.THREE));

        drawPile = new DrawPile(testCards);
        piles = new PileManager(drawPile);

    }
    @Test
    void testFinishDrawPile(){
        piles.discardCard(piles.drawCard());
        assertEquals(2, piles.getDrawPile().getCardCount());

        piles.discardCard(piles.drawCard());
        assertEquals(1, piles.getDrawPile().getCardCount());

        piles.discardCard(piles.drawCard());
        assertEquals(2, piles.getDrawPile().getCardCount());

        piles.discardCard(piles.drawCard());
        assertEquals(1, piles.getDrawPile().getCardCount());

    }


}