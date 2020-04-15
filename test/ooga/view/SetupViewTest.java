package ooga.view;

import javafx.stage.Stage;
import ooga.game.UnoController;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class SetupViewTest extends DukeApplicationTest {
    private SetupView startView;

    @Override
    public void start(Stage stage) {
        startView = new SetupView(stage);
    }

    @Test
    void testSetValuesDefault() {
        clickOn(startView.getWelcomeOkButton());

        assertEquals(4, startView.getMySettings().getNumPlayers());
        assertEquals(7, startView.getMySettings().getHandSize());
        assertEquals(500, startView.getMySettings().getWinningScore());
    }

    @Test
    void testSetValuesChanged() {
        setValue(startView.getNumberPlayersSlider(), 3);
        setValue(startView.getCardsPerPlayerSlider(), 10);
        setValue(startView.getScoreToWinSlider(), 300);
        clickOn(startView.getWelcomeOkButton());

        assertEquals(3, startView.getMySettings().getNumPlayers());
        assertEquals(10, startView.getMySettings().getHandSize());
        assertEquals(300, startView.getMySettings().getWinningScore());
    }

}