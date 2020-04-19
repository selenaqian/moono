package ooga.rules;

import ooga.cards.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the AscendingRules class.
 * Doesn't test isOver() method because it inherits the same one as in ClassicRules which is tested in ClassicRulesTest.
 * @author Tess Noonan (tcn6)
 */
class AscendingRulesTest {

    private Card cnine = new Card(Suit.C, Value.NINE);
    private Card aone = new Card(Suit.A, Value.ONE);
    private Card dskip = new Card(Suit.D, Value.SKIP);
    private Card afive = new Card(Suit.A, Value.FIVE);
    private Card bone = new Card(Suit.B, Value.ONE);
    private Card cone = new Card(Suit.C, Value.ONE);
    private Card done = new Card(Suit.D, Value.ONE);

    private Rule myRules = new AscendingRules();

    /**
     * Tests isValid when the top Card of the DiscardPile is Wild.
     */
    @Test
    void isValidTrueDiscardWild() {
        Card wild = new Card(Suit.A, Value.WILD);
        Card wild4 = new Card(Suit.B, Value.WILD4);

        assertTrue(myRules.isValid(wild, wild4));
        assertTrue(myRules.isValid(wild4, cnine));
        assertTrue(myRules.isValid(wild4, aone));
    }

    /**
     * Tests isValid when the played Card is Special.
     */
    @Test
    void isValidTrueSpecial() {
        Card cdraw2 = new Card(Suit.C, Value.DRAW2);

        assertTrue(myRules.isValid(aone, dskip));
        assertTrue(myRules.isValid(cdraw2, dskip));
    }

    /**
     * Tests isValid when the played Card is of greater or equal Value within the same Suit.
     */
    @Test
    void isValidTrueBasicGreaterEqual() {
        assertTrue(myRules.isValid(aone, afive));
        assertTrue(myRules.isValid(aone, aone));
    }

    /**
     * Tests isValid when the played Card is of equal Value within different Suits.
     */
    @Test
    void isValidTrueEqualValueDiffSuit() {
        assertTrue(myRules.isValid(aone, bone));
        assertTrue(myRules.isValid(aone, cone));
        assertTrue(myRules.isValid(aone, done));
    }

    /**
     * Tests isValid when the played Card is of lesser (basic) Value of any Suit.
     */
    @Test
    void isValidFalseLessThan() {
        assertFalse(myRules.isValid(afive, aone));
        assertFalse(myRules.isValid(afive, bone));
        assertFalse(myRules.isValid(dskip, done));
        assertFalse(myRules.isValid(dskip, afive));
    }

    /**
     * Tests isValid when the played Card is of greater (basic) Value in a different Suit.
     */
    @Test
    void isValidFalseGreaterDiffSuit() {
        assertFalse(myRules.isValid(cone, afive));
        assertFalse(myRules.isValid(bone, cnine));
    }
}