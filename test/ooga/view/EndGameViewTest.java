/**
 * Tests EndGameView class.
 *
 * @author Selena Qian
 */

package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EndGameViewTest extends DukeApplicationTest {
    private EndGameView endView;
    private Button newGameButton;

    @Override
    public void start(Stage stage) {
        endView = new EndGameView(stage, 1);
        newGameButton = endView.getNewGameButton();
    }

    /**
     * Tests that the elements in the scene are being created correctly.
     */
    @Test
    void endGameElementsTest() {
        Text gameCompleteText = lookup("#gameCompleteText").query();
        Text winnerText = lookup("#winnerText").query();
        Text startNewGameText = lookup("#startNewGameText").query();
        assertEquals("game complete.", gameCompleteText.getText());
        assertEquals("congratulations player 1", winnerText.getText());
        assertEquals("start new game?", startNewGameText.getText());
        assertEquals("new game", newGameButton.getText());
    }

    /**
     * Tests that clicking on the new game button does start up a new game - should go back to the welcome screen and can check elements in there.
     */
    @Test
    void startNewGameTest() {
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> clickOn(newGameButton));
        sleep(2, TimeUnit.SECONDS);

        Text welcomeText = lookup("#welcomeText").query();
        assertEquals("welcome to moono", welcomeText.getText());
    }
}