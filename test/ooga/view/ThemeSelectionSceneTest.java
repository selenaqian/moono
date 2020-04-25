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

    /**
     * Test that the default theme works - when don't choose one, automatically set to default.css stylesheet.
     */
    @Test
    void getSelectedThemeTestDefault() {
        goToThemeView();
        clickOn(startView.getThemeOkButton());

        assertEquals("stylesheets/default.css", startView.getMySettings().getTheme());
    }

    /**
     * Test that selecting the duke theme works.
     */
    @Test
    void getSelectedThemeTestDuke() {
        goToThemeView();
        clickOn(colorBoxes.get(4));
        sleep(2, TimeUnit.SECONDS);
        clickOn(startView.getThemeOkButton());

        assertEquals("stylesheets/duke.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(1).getStroke());
    }

    /**
     * Test that selecting the space theme works.
     */
    @Test
    void getSelectedThemeTestSpace() {
        goToThemeView();
        clickOn(colorBoxes.get(8));
        sleep(2, TimeUnit.SECONDS);
        clickOn(startView.getThemeOkButton());

        assertEquals("stylesheets/space.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(2).getStroke());
    }

    /**
     * Test that clicking on one theme and then another works - properly updates view and changes stylesheet.
     */
    @Test
    void getSelectedThemeTestMultipleClicks() {
        goToThemeView();
        clickOn(colorBoxes.get(4));
        sleep(2, TimeUnit.SECONDS);
        clickOn(colorBoxes.get(0));
        sleep(2, TimeUnit.SECONDS);
        clickOn(colorBoxes.get(4));
        sleep(2, TimeUnit.SECONDS);

        clickOn(startView.getThemeOkButton());

        assertEquals("stylesheets/duke.css", startView.getMySettings().getTheme());
        assertEquals(Color.AQUA, backgroundBoxes.get(1).getStroke());
        assertEquals(1, backgroundBoxes.get(0).getStrokeWidth());
    }

    /**
     * Helper method to continue setup for tests. Needs to happen outside of start method so that the scenes advance before
     * trying to set the button - if put in start, throws a NullPointerException.
     */
    private void goToThemeView() {
        Button rulesOk = startView.getRulesOkButton();
        clickOn(rulesOk);
        backgroundBoxes = startView.getThemeSelection().getBackgroundBoxes();
        colorBoxes = startView.getThemeSelection().getColorBoxes();
    }
}