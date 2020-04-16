package ooga.rules;

import ooga.cards.Card;
import ooga.cards.Suit;
import ooga.cards.Value;
import ooga.piles.Hand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests the DeadlyRules class.
 * @author Tess Noonan (tcn6)
 */
class DeadlyRulesTest {

    private Card card1 = new Card(Suit.A, Value.ZERO);
    private Card card2 = new Card(Suit.A, Value.ONE);
    private Card card3 = new Card(Suit.B, Value.ZERO);
    private Card card4 = new Card(Suit.B, Value.ONE);

    private Rule myRules = new DeadlyRules();

    /**
     * Tests isValid when both the Suit and the Value match.
     */
    @Test
    void isValidTrueBoth() {
        assertTrue(myRules.isValid(card1, card1));
    }

    /**
     * Tests isValid when only the Suit matches.
     */
    @Test
    void isValidTrueSuit() {
        assertTrue(myRules.isValid(card1, card2));
    }

    /**
     * Tests isValid when only the Value matches.
     */
    @Test
    void isValidTrueValue() {
        assertTrue(myRules.isValid(card1, card3));
    }

    /**
     * Tests isValid when only nothing matches.
     */
    @Test
    void isValidFalse() {
        assertFalse(myRules.isValid(card1, card4));
    }

    /**
     * Tests isValid when given a Wild card when Suit/Value does and doesn't match.
     */
    @Test
    void isValidTrueSpecial(){
        assertTrue(myRules.isValid(card1, new Card(Suit.A, Value.DRAW2)));
        assertTrue(myRules.isValid(card1, new Card(Suit.C, Value.WILD4)));
        assertTrue(myRules.isValid(new Card(Suit.D, Value.SKIP), new Card(Suit.C, Value.SKIP)));
    }

    /**
     * Tests isOver when there's only invalid Cards in the Hand.
     */
    @Test
    void isOverTrue() {
        ArrayList<Card> al = new ArrayList<>(Arrays.asList(card4));
        Hand hand = new Hand(al);
        assertTrue(myRules.isOver(card1, hand));
    }

    /**
     * Tests isOver when there's one valid Card in the Hand.
     */
    @Test
    void isOverTrueOneLeft() {
        ArrayList<Card> al = new ArrayList<>(Arrays.asList(card2));
        Hand hand = new Hand(al);
        assertTrue(myRules.isOver(card1, hand));
    }

    /**
     * Tests isOver when there are valid and invalid Cards in the Hand.
     */
    @Test
    void isOverFalseMixed() {
        ArrayList<Card> al = new ArrayList<>(Arrays.asList(card4, card2, card3, new Card(Suit.C, Value.NINE)));
        Hand hand = new Hand(al);
        assertFalse(myRules.isOver(card1, hand));
    }

    /**
     * Tests isOver when there are multiple valid Cards in the Hand.
     */
    @Test
    void isOverFalseAll() {
        ArrayList<Card> al = new ArrayList<>(Arrays.asList(card2, card3));
        Hand hand = new Hand(al);
        assertFalse(myRules.isOver(card1, hand));
    }
}