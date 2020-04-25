/**
 * Tests the RulesAndSpecialCardScene class.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.stage.Stage;
import ooga.rules.AscendingRules;
import ooga.rules.ClassicRules;
import ooga.rules.DeadlyRules;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class RulesAndSpecialCardSceneTest extends DukeApplicationTest {
    private SetupView startView;

    @Override
    public void start(Stage stage) {
        startView = new SetupView(stage);
        clickOn(startView.getWelcomeOkButton()); // advances to the rules and special cards selection scene
    }

    /**
     * Tests that classic rules are implemented when no interaction happens.
     */
    @Test
    void defaultRulesSelectionsTest() {
        clickOn(startView.getRulesOkButton());
        assertEquals("ClassicRules", startView.getRulesAndSpecialCards().getRuleSelections().get(0));
        assertTrue(startView.getMySettings().getRule() instanceof ClassicRules);
    }

    /**
     * Tests that the deadly rules are implemented when the user selects them.
     */
    @Test
    void deadlyRulesTest() {
        startView.getRulesAndSpecialCards().ruleSelections.get(1).setSelected(true);
        clickOn(startView.getRulesOkButton());
        assertEquals("DeadlyRules", startView.getRulesAndSpecialCards().getRuleSelections().get(0));
        assertTrue(startView.getMySettings().getRule() instanceof DeadlyRules);
    }

    /**
     * Tests that the ascending rules are implemented when the user selects them.
     */
    @Test
    void ascendingRulesTest() {
        startView.getRulesAndSpecialCards().ruleSelections.get(2).setSelected(true);
        clickOn(startView.getRulesOkButton());
        assertEquals("AscendingRules", startView.getRulesAndSpecialCards().getRuleSelections().get(0));
        assertTrue(startView.getMySettings().getRule() instanceof AscendingRules);
    }

    /**
     * Tests that the default functions as expected - if no special cards checked, none will be passed for creation.
     */
    @Test
    void noSpecialCardsTest() {
        clickOn(startView.getRulesOkButton());
        assertEquals(0, startView.getRulesAndSpecialCards().getSpecialCardSelections().size());
    }

    /**
     * Tests that choosing one special card works.
     */
    @Test
    void oneSpecialCardTest() {
        startView.getRulesAndSpecialCards().specialCardOptions.get(0).setSelected(true);
        clickOn(startView.getRulesOkButton());
        assertEquals(1, startView.getRulesAndSpecialCards().getSpecialCardSelections().size());
        assertEquals("WILD", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(0));
    }

    /**
     * Tests that choosing multiple but not all special cards works.
     */
    @Test
    void multipleSpecialCardsTest() {
        for(int i = 0; i < 4; i++) {
            startView.getRulesAndSpecialCards().specialCardOptions.get(i).setSelected(true);
        }

        clickOn(startView.getRulesOkButton());
        assertEquals(4, startView.getRulesAndSpecialCards().getSpecialCardSelections().size());
        assertEquals("WILD", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(0));
        assertEquals("WILD4", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(1));
        assertEquals("SKIP", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(2));
        assertEquals("REVERSE", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(3));
    }

    /**
     * Tests that choosing all special cards works.
     */
    @Test
    void allSpecialCardsTest() {
        for(int i = 0; i < startView.getRulesAndSpecialCards().specialCardOptions.size(); i++) {
            startView.getRulesAndSpecialCards().specialCardOptions.get(i).setSelected(true);
        }
        clickOn(startView.getRulesOkButton());
        assertEquals(5, startView.getRulesAndSpecialCards().getSpecialCardSelections().size());
        assertEquals("WILD", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(0));
        assertEquals("WILD4", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(1));
        assertEquals("SKIP", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(2));
        assertEquals("REVERSE", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(3));
        assertEquals("DRAW2", startView.getRulesAndSpecialCards().getSpecialCardSelections().get(4));
    }
}