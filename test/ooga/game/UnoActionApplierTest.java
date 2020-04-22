package ooga.game;

import ooga.cards.Value;
import ooga.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the UnoActionApplier class.
 * @author Tess Noonan (tcn6)
 */
class UnoActionApplierTest {

    private Uno uno;
    private UnoActionApplier actionApplier;

    @BeforeEach
    void setUp(){
        uno = new Uno();
        actionApplier = new UnoActionApplier(uno, uno.getTurnManager());
    }

    /**
     * Tests the applied action for Skip.
     * Note: The first player is randomized
     */
    @Test
    void applyActionSkip() {
        int firstPlayer = uno.getTurnManager().getPlayerId(uno.getTurnManager().getCurrentPlayer());
        int nextPlayer = 0;
        if(firstPlayer < uno.getTurnManager().getAllPlayers().size()){
            nextPlayer = firstPlayer++;
        }
        actionApplier.applyAction(Value.SKIP);
        assertEquals(nextPlayer, uno.getTurnManager().getPlayerId(uno.getTurnManager().getCurrentPlayer()));
    }

    /**
     * Tests the applied action for Reverse.
     */
    @Test
    void applyActionReverse() {
        assertEquals(UnoTurnManager.CW, uno.getTurnManager().getDirection());
        actionApplier.applyAction(Value.REVERSE);
        assertEquals(UnoTurnManager.CCW, uno.getTurnManager().getDirection());
    }

    /**
     * Tests the applied action for Draw2.
     */
    @Test
    void applyActionDraw2() {
        assertEquals(7, uno.getTurnManager().getNextPlayer().hand().getCardCount());
        actionApplier.applyAction(Value.DRAW2);
        assertEquals(9, uno.getTurnManager().getNextPlayer().hand().getCardCount());
    }

    //TODO: Hi Tess here. I am not sure how to test the Wild cards with the observer.

    /**
     * Tests the applied action for Wild.
     */
    @Test
    void applyActionWild() {
//        actionApplier.applyAction(Value.WILD);
    }

    /**
     * Tests the applied action for Wild4.
     */
    @Test
    void applyActionWild4() {
//        actionApplier.applyAction(Value.WILD4);
    }

    /**
     * Tests the applied action for a basic card (no change).
     */
    @Test
    void applyActionBasic() {
        assertEquals(UnoTurnManager.CW, uno.getTurnManager().getDirection());
        assertEquals(7, uno.getTurnManager().getNextPlayer().hand().getCardCount());

        actionApplier.applyAction(Value.ONE);
        assertEquals(UnoTurnManager.CW, uno.getTurnManager().getDirection());
        assertEquals(7, uno.getTurnManager().getCurrentPlayer().hand().getCardCount());
    }
}