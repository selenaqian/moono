package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EndViewTest extends DukeApplicationTest {
    private EndView endView;
    private Button newGameButton;

    @Override
    public void start(Stage stage) {
        endView = new EndView(stage);
        newGameButton = endView.getNewGameButton();
    }

    @Test
    void endViewElementsTest() {
        Text gameCompleteText = lookup("#gameCompleteText").query();
        assertEquals("game complete.\nstart new game?", gameCompleteText.getText());
        assertEquals("start new game", newGameButton.getText());
    }

    @Test
    void endViewStartNewGameTest() {
        sleep(2, TimeUnit.SECONDS);
        clickOn(newGameButton);
        sleep(2, TimeUnit.SECONDS);

        Text welcomeText = lookup("#welcomeText").query();
        assertEquals("welcome to moono", welcomeText.getText());
    }
}