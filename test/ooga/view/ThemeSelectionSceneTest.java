package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ThemeSelectionSceneTest extends DukeApplicationTest {
    private SetupView startView;
    private List<Rectangle> backgroundBoxes;
    private List<Rectangle> colorBoxes;

    @Override
    public void start(Stage stage) {
        startView = new SetupView(stage);
        clickOn(startView.getWelcomeOkButton());
    }

    @Test
    void getSelectedThemeTestDefault() {
        goToThemeView();
        javafxRun(() -> clickOn(startView.getThemeOkButton()));

        assertEquals("default.css", startView.getMySettings().getTheme());
    }

    @Test
    void getSelectedThemeTestDuke() {
        goToThemeView();
        javafxRun(() -> clickOn(colorBoxes.get(4)));
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> clickOn(startView.getThemeOkButton()));

        assertEquals("duke.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(1).getStroke());
    }

    @Test
    void getSelectedThemeTestSpace() {
        goToThemeView();
        javafxRun(() -> clickOn(colorBoxes.get(8)));
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> clickOn(startView.getThemeOkButton()));

        assertEquals("space.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(2).getStroke());
    }

    @Test
    void getSelectedThemeTestMultipleClicks() {
        goToThemeView();
        javafxRun(() -> clickOn(colorBoxes.get(4)));
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> clickOn(colorBoxes.get(0)));
        sleep(2, TimeUnit.SECONDS);
        javafxRun(() -> clickOn(colorBoxes.get(4)));
        sleep(2, TimeUnit.SECONDS);

        javafxRun(() -> clickOn(startView.getThemeOkButton()));

        assertEquals("duke.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(1).getStroke());
    }

    /**
     * Helper method to continue setup for tests. Needs to happen outside of start method so that the scenes advance before
     * trying to set the button - if put in start, throws a NullPointerException.
     */
    private void goToThemeView() {
        Button rulesOk = startView.getRulesOkButton();
        javafxRun(() -> clickOn(rulesOk));
        backgroundBoxes = startView.getThemeSelection().getBackgroundBoxes();
        colorBoxes = startView.getThemeSelection().getColorBoxes();
    }
}