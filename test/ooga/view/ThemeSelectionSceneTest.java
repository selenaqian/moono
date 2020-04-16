package ooga.view;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ThemeSelectionSceneTest extends DukeApplicationTest {

    @Override
    public void start(Stage stage) {
        SetupView startView = new SetupView(stage);
        clickOn(startView.getWelcomeOkButton());
        clickOn(startView.getRulesOkButton());
    }

    @Test
    void getSelectedTheme() {
        sleep(3, TimeUnit.SECONDS);
    }
}