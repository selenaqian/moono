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

    //TODO: This class doesn't work yet, make sure all code is compiling and working properly.

    private Uno uno;
    private UnoActionApplier actionApplier;

    @BeforeEach
    void setUp(){
        uno = new Uno();
        actionApplier = new UnoActionApplier(uno, uno.getTurnManager());
    }

    /**
     * Tests the applied action for Skip.
     */
    @Test
    void applyActionSkip() {
        assertEquals(0, uno.getTurnManager().getPlayerId(uno.getTurnManager().getCurrentPlayer()));
        actionApplier.applyAction(Value.SKIP);
        assertEquals(1, uno.getTurnManager().getPlayerId(uno.getTurnManager().getCurrentPlayer()));
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
    }

    /**
     * Tests the applied action for Wild.
     */
    @Test
    void applyActionWild() {
    }

    /**
     * Tests the applied action for Wild4.
     */
    @Test
    void applyActionWild4() {
    }

    /**
     * Tests the applied action for a basic card (no change).
     */
    @Test
    void applyActionBasic() {

    }
}