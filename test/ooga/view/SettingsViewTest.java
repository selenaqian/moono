package ooga.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SettingsViewTest extends DukeApplicationTest {
    private SettingsView mySettingsView;
    private Button speedUp;
    private Button slowDown;
    private Map<String, Pane> themeOptions;

    @Override
    public void start(Stage stage) {
        mySettingsView = new SettingsView();
        speedUp = lookup("speed up").queryButton();
        slowDown = lookup("slow down").queryButton();
        themeOptions = mySettingsView.getThemeOptions();
    }

    @Test
    void speedUpTest1() {
        javafxRun(() -> clickOn(speedUp));
        sleep(2, TimeUnit.SECONDS);
        assertNotNull(lookup("1.0 s").query());
    }

    @Test
    void speedUpTest2() {
        javafxRun(() -> clickOn(speedUp));
        javafxRun(() -> clickOn(speedUp));

        assertNotNull(lookup("1.0 s").query());
        assertEquals(1.0, mySettingsView.getSettings().getSpeed());
    }

    @Test
    void slowDownTest1() {
        javafxRun(() -> clickOn(slowDown));
        assertNotNull(lookup("2.0 s").query());
        assertEquals(2.0, mySettingsView.getSettings().getSpeed());
    }

    @Test
    void slowDownTest2() {
        javafxRun(() -> clickOn(slowDown));
        javafxRun(() -> clickOn(slowDown));

        assertNotNull(lookup("2.5 s").query());
        assertEquals(2.5, mySettingsView.getSettings().getSpeed());
    }

    @Test
    void themeChangeDukeTest() {
        Rectangle dukeOption = (Rectangle)themeOptions.get("duke").getChildren().get(0);
        javafxRun(() -> clickOn(dukeOption));
        sleep(2, TimeUnit.SECONDS);
        assertEquals("themeOptionSelected", dukeOption.getStyleClass().get(0));
        assertEquals("stylesheets/duke.css", mySettingsView.getSettings().getTheme());
    }

    @Test
    void themeChangeSpaceTest() {
        Rectangle dukeOption = (Rectangle)themeOptions.get("duke").getChildren().get(0);
        javafxRun(() -> clickOn(dukeOption));
        Rectangle spaceOption = (Rectangle)themeOptions.get("space").getChildren().get(0);
        javafxRun(() -> clickOn(spaceOption));
        assertEquals("themeOptionSelected", spaceOption.getStyleClass().get(0));
        assertEquals("stylesheets/space.css", mySettingsView.getSettings().getTheme());
        assertEquals(0, dukeOption.getStyleClass().size());
    }

    @Test
    void darkModeClassicTest() {

    }

    @Test
    void darkModeDukeTest() {

    }

    @Test
    void saveButtonTest() {

    }

    @Test
    void newGameOverrideTest() {

    }

    @Test
    void newGameAdditionalTest() {

    }
}