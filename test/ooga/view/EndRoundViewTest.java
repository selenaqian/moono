/**
 * Tests EndRoundView class.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.game.UnoController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EndRoundViewTest extends DukeApplicationTest {
    private Map<Integer, Integer> playerScores;
    private EndRoundView endRoundView;
    private Button continueButton;

    @Override
    public void start(Stage stage) {
        playerScores = new TreeMap<>();
        playerScores.put(1, 10);
        for(int i=2; i <=4; i++) {
            playerScores.put(i, 0);
        }
        UnoController myController = new UnoController(stage);
        endRoundView = new EndRoundView(stage, 1, playerScores, myController);
        continueButton = endRoundView.getNextRoundButton();
    }

    /**
     * Tests that the elements in the scene are being created correctly.
     */
    @Test
    void endRoundElementsTest() {
        sleep(2, TimeUnit.SECONDS);
        Text gameCompleteText = lookup("#roundCompleteText").query();
        Text startNewGameText = lookup("#startNextRoundText").query();

        assertEquals("round complete.", gameCompleteText.getText());
        assertEquals("start next round?", startNewGameText.getText());
        assertEquals("next round", continueButton.getText());

        //Lookup by ID wasn't working for these even though the debugger showed the IDs getting set, so I changed it to lookup by text.
        assertNotNull(lookup("Player 1 score: 10").query());
        assertNotNull(lookup("Player 2 score: 0").query());
        assertNotNull(lookup("Player 3 score: 0").query());
        assertNotNull(lookup("Player 3 score: 0").query());
    }

    /**
     * Tests that score updates on the GameView.
     */
    @Test
    void startNextRoundTest() {
        clickOn(continueButton);

        assertNotNull("score: 10");
    }

}