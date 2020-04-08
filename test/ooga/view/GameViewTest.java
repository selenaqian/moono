package ooga.view;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest extends DukeApplicationTest {
    private GameView gameView;

    @Override
    public void start(Stage stage) {
        gameView = new GameView();
    }

    @Test
    void testUpdateHandCardsLeft() {
        gameView.updateHand(3, 10);

        assertEquals("10 left", gameView.getAllPlayersCardsLeft().get(3-1).getText());
    }

    /**
     * Tests updating the visual of player 1's hand where cards are actually visible.
     */
    @Test
    void testUpdateHand() {

    }


}